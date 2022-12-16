package fhv.ws22.se.skyward.domain.service;

import fhv.ws22.se.skyward.domain.dtos.*;
import fhv.ws22.se.skyward.domain.paymentParser.Payment;
import fhv.ws22.se.skyward.domain.paymentParser.PaymentParser;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TmpData extends UnicastRemoteObject implements TmpDataService {
    private final DomainService domainService;
    private UUID tmpBookingId;
    private UUID tmpInvoiceId;
    private HashMap<String, Boolean> filterMap;

    public TmpData(SessionService session) throws RemoteException {
        super();
        ServiceProviderService serviceProvider = session.getServiceProvider();
        domainService = (DomainService) serviceProvider.getService("DomainService");
        filterMap = new HashMap<>();
    }

    public List<RoomDto> filterRooms(List<RoomDto> rooms, HashMap<String, Boolean> filterMap) {
        List<RoomDto> availableRooms = new ArrayList<RoomDto>();
        for (RoomDto room : rooms) {
            if (room.getRoomTypeName().equals("Single") && filterMap.get("Single")) {
                availableRooms.add(room);
            } else if (room.getRoomTypeName().equals("Double") && filterMap.get("Double")) {
                availableRooms.add(room);
            } else if (room.getRoomTypeName().equals("Triple") && filterMap.get("Triple")) {
                availableRooms.add(room);
            } else if (room.getRoomTypeName().equals("Twin") && filterMap.get("Twin")) {
                availableRooms.add(room);
            } else if (room.getRoomTypeName().equals("Queen") && filterMap.get("Queen")) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public BookingDto getTmpBooking() throws RemoteException {
        if (tmpBookingId == null) {
            BookingDto booking = new BookingDto();
            booking.setCheckInDateTime(LocalDateTime.now());
            booking.setIsCheckedIn(false);
            tmpBookingId = domainService.addAndReturnId(BookingDto.class, booking);
        }
        return domainService.get(tmpBookingId, BookingDto.class);
    }
    public void resetTmpBooking() {
        tmpBookingId = null;
    }
    public void setTmpBooking(BookingDto booking) throws RemoteException {
        BookingDto tmpBid = domainService.get(booking.getId(), BookingDto.class);
        if (tmpBid == null) {
            throw new IllegalArgumentException("Booking could not be added");
        }
        tmpBookingId = tmpBid.getId();
    }

    public InvoiceDto getTmpInvoice() throws RemoteException {
        if (tmpInvoiceId == null) {
            BookingDto booking = getTmpBooking();

            List<InvoiceDto> invoices = domainService.getAll(InvoiceDto.class);
            invoices.removeIf(invoice -> !invoice.getBooking().getId().equals(booking.getId()));

            if (invoices.isEmpty()) {
                AddressDto customerAddress = new AddressDto("MainStreet", 43, 1234, "Vienna", "Austria");
                domainService.add(customerAddress);
                InvoiceDto invoice = new InvoiceDto(LocalDateTime.now(), false, customerAddress, booking);
                invoice.setBilledCustomer(booking.getCustomers().get(0));
                tmpInvoiceId = domainService.addAndReturnId(InvoiceDto.class, invoice);

                List<ChargeableItemDto> chargeableItemDtos = new ArrayList<>();
                for (RoomDto room : booking.getRooms()) {
                    Integer quantity = (int) Duration.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime()).toDays() + 1;
                    BigDecimal price = getPrice(room.getRoomTypeName());
                    ChargeableItemDto chargeableItem = new ChargeableItemDto(room.getRoomTypeName() + " Room: " + room.getRoomNumber(), price, quantity, booking);
                    chargeableItemDtos.add(chargeableItem);
                    domainService.add(chargeableItem);
                }
            } else {
                tmpInvoiceId = invoices.get(0).getId();
            }
        }

        return domainService.get(tmpInvoiceId, InvoiceDto.class);
    }

    private BigDecimal getPrice(String roomTypeName) {
        switch (roomTypeName) {
            case "Single":
                return new BigDecimal(100);
            case "Double":
                return new BigDecimal(150);
            case "Triple":
                return new BigDecimal(200);
            case "Twin":
                return new BigDecimal(150);
            case "Queen":
                return new BigDecimal(300);
            default:
                return new BigDecimal(0);
        }
    }

    public void resetTmpInvoice() {
        tmpInvoiceId = null;
    }
    public void setTmpInvoice(InvoiceDto invoice) throws RemoteException {
        InvoiceDto tmpIid = domainService.get(invoice.getId(), InvoiceDto.class);
        if (tmpIid == null) {
            throw new IllegalArgumentException("Invoice could not be added");
        }
        tmpInvoiceId = tmpIid.getId();
    }

    public void setRoomFilterMap(HashMap<String, Boolean> filterMap) {
        this.filterMap = filterMap;
    }
    public HashMap<String, Boolean> getRoomFilterMap() {
        return filterMap;
    }

    public void handlePayment(String payment) {
        System.out.println("Payment received: " + payment);
        List<Payment> payments = null;
        try {
            payments = PaymentParser.parse(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Payment p : payments) {
            System.out.println(p);
        }
    }
}
