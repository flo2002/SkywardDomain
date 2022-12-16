package fhv.ws22.se.skyward.persistence;

import com.google.inject.Inject;
import fhv.ws22.se.skyward.domain.model.*;
import java.time.LocalDateTime;
import java.util.List;

public class DataGenerator {
    @Inject
    public DatabaseFacade dbf;

    public DataGenerator() {
    }

    public void generateData() {
        try {
            RoomModel r0 = new RoomModel(101, "Single", "cleaned");
            RoomModel r1 = new RoomModel(102, "Single", "cleaned");
            RoomModel r2 = new RoomModel(103, "Single", "cleaned");
            RoomModel r3 = new RoomModel(201, "Double", "cleaned");
            RoomModel r4 = new RoomModel(202, "Double", "cleaned");
            RoomModel r5 = new RoomModel(203, "Double", "cleaned");
            RoomModel r6 = new RoomModel(204, "Double", "cleaned");
            RoomModel r7 = new RoomModel(301, "Triple", "cleaned");
            RoomModel r8 = new RoomModel(302, "Triple", "cleaned");
            RoomModel r9 = new RoomModel(401, "Twin", "cleaned");
            RoomModel r10 = new RoomModel(402, "Twin", "cleaned");
            RoomModel r11 = new RoomModel(501, "Queen", "cleaned");
            RoomModel r12 = new RoomModel(502, "Queen", "cleaned");

            AddressModel customerAddress0 = new AddressModel("MainStreet", 43, 1234, "Vienna", "Austria");
            AddressModel customerAddress1 = new AddressModel("SecondStreet", 35, 5678, "Arnherm", "Netherlands");
            AddressModel customerAddress2 = new AddressModel("YellowBoulevard", 5, 3547, "NewYork", "States");
            AddressModel customerAddress3 = new AddressModel("HotelAvenue", 9, 9463, "Madrid", "Spain");

            CustomerModel john = new CustomerModel("John", "Doe", customerAddress0, "Individual");
            CustomerModel jane = new CustomerModel("Jane", "Doe", customerAddress0, "Travel Agency");
            CustomerModel alex = new CustomerModel("Alex", "Kolar", customerAddress2, "Individual");
            CustomerModel mark = new CustomerModel("Mark", "Kessner", customerAddress1, "Group");
            CustomerModel lucas = new CustomerModel("Lucas", "Earth", customerAddress3, "Travel Agency");
            CustomerModel paul = new CustomerModel("Paul", "Simmens", customerAddress3, "Individual");

            BookingModel b = new BookingModel(LocalDateTime.now(), LocalDateTime.now().plusDays(1), true, List.of(jane), List.of(r2));
            BookingModel b1 = new BookingModel(LocalDateTime.now(), LocalDateTime.now().plusDays(1), true, List.of(alex, lucas), List.of(r12, r11));
            BookingModel b2 = new BookingModel(LocalDateTime.now().plusWeeks(2), LocalDateTime.now().plusWeeks(3), false, List.of(john), List.of(r1));
            BookingModel b3 = new BookingModel(LocalDateTime.now().plusWeeks(4), LocalDateTime.now().plusWeeks(5), false, List.of(jane), List.of(r4, r5));
            BookingModel b4 = new BookingModel(LocalDateTime.now().minusWeeks(1), LocalDateTime.now(), true, List.of(john, jane), List.of(r2, r3));
            BookingModel b5 = new BookingModel(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(4), true, List.of(john, jane), List.of(r4, r5));


            dbf.add(r0);
            dbf.add(r1);
            dbf.add(r2);
            dbf.add(r3);
            dbf.add(r4);
            dbf.add(r5);
            dbf.add(r6);
            dbf.add(r7);
            dbf.add(r8);
            dbf.add(r9);
            dbf.add(r10);
            dbf.add(r11);
            dbf.add(r12);
            dbf.add(customerAddress0);
            dbf.add(customerAddress1);
            dbf.add(customerAddress2);
            dbf.add(customerAddress3);
            dbf.add(john);
            dbf.add(jane);
            dbf.add(alex);
            dbf.add(lucas);
            dbf.add(mark);
            dbf.add(paul);
            dbf.add(b);
            dbf.add(b1);
            dbf.add(b2);
            dbf.add(b3);
            dbf.add(b4);
            dbf.add(b5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
