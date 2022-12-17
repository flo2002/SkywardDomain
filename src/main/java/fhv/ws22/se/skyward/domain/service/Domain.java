package fhv.ws22.se.skyward.domain.service;

import com.google.inject.Inject;
import fhv.ws22.se.skyward.domain.DataService;
import fhv.ws22.se.skyward.domain.DtoMapper;
import fhv.ws22.se.skyward.domain.dtos.*;
import fhv.ws22.se.skyward.domain.model.*;
import fhv.ws22.se.skyward.domain.paymentParser.Payment;
import fhv.ws22.se.skyward.domain.paymentParser.PaymentParser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.*;

public class Domain extends UnicastRemoteObject implements DomainService {
    private final DataService dataService;
    private final DtoMapper dtoMapper;
    private final Map<Class, Class> dtoModelClassMap;

    public Domain(DataService ds) throws RemoteException {
        super();
        dtoMapper = new DtoMapper();
        dataService = ds;

        dtoModelClassMap = new HashMap<>();
        dtoModelClassMap.put(CustomerDto.class, CustomerModel.class);
        dtoModelClassMap.put(RoomDto.class, RoomModel.class);
        dtoModelClassMap.put(BookingDto.class, BookingModel.class);
        dtoModelClassMap.put(InvoiceDto.class, InvoiceModel.class);
        dtoModelClassMap.put(AddressDto.class, AddressModel.class);
        dtoModelClassMap.put(ChargeableItemDto.class, ChargeableItemModel.class);
    }


    @SuppressWarnings("unchecked")
    public <T extends AbstractDto> List getAll(Class<T> clazz) {
        List<? extends AbstractModel> modelList = dataService.getAll(dtoModelClassMap.get(clazz));
        List<T> dtoList = new ArrayList<T>();
        for (AbstractModel model : modelList) {
            dtoList.add((T) model.toDto());
        }
        return dtoList;
    }

    public <T extends AbstractDto> T get(UUID id, Class<T> clazz) {
        return dataService.get(id, dtoModelClassMap.get(clazz)).toDto();
    }

    public <T extends AbstractDto> void add(T t) {
        AbstractModel model = dtoMapper.toModel(t, dtoModelClassMap.get(t.getClass()));
        dataService.add(model);
    }

    public <T extends AbstractDto> UUID addAndReturnId(Class<T> clazz, T t) {
        AbstractModel model = dtoMapper.toModel(t, dtoModelClassMap.get(t.getClass()));
        return dataService.addAndReturnId(dtoModelClassMap.get(clazz), model);
    }

    public <T extends AbstractDto> void update(UUID id, T t) {
        AbstractModel model = dtoMapper.toModel(t, dtoModelClassMap.get(t.getClass()));
        dataService.update(id, model);
    }

    public <T extends AbstractDto> void delete(UUID id, Class<T> clazz) {
        dataService.delete(id, dtoModelClassMap.get(clazz));
    }

    public List<RoomDto> getAvailableRooms(LocalDateTime checkIn, LocalDateTime checkOut) {
        if (checkIn == null || checkOut == null) {
            return null;
        }

        List<RoomModel> allRooms = (List<RoomModel>) dataService.getAll(RoomModel.class);
        List<RoomDto> availableRooms = new ArrayList<RoomDto>();
        for (RoomModel model : allRooms) {
            availableRooms.add(model.toDto());
        }

        List<BookingModel> allBookings = (List<BookingModel>) dataService.getAll(BookingModel.class);
        // check if any booking is in the same time frame to remove it from the available rooms
        for (BookingModel booking : allBookings) {
            if (booking.getCheckInDateTime() != null && booking.getCheckOutDateTime() != null) {
                if (booking.getCheckInDateTime().toLocalDate().isBefore(checkOut.toLocalDate()) && booking.getCheckOutDateTime().toLocalDate().isAfter(checkIn.toLocalDate())) {
                    List<RoomModel> blockedRooms = booking.getRooms();
                    if (blockedRooms != null) {
                        for (RoomModel room : blockedRooms) {
                            availableRooms.removeIf(roomDto -> roomDto.getId().equals(room.getId()));
                        }
                    }
                }
            }
        }

        return availableRooms;
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
