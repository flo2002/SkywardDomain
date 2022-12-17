package fhv.ws22.se.skyward;

import fhv.ws22.se.skyward.domain.dtos.BookingDto;
import fhv.ws22.se.skyward.domain.service.*;

import java.math.BigInteger;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class SkywardServer {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:./src/main/resources/skyward.policy");

        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println("Registry runs already");
        }

        try {
            SessionService session = SessionFactory.getInstance().getSession(new BigInteger("1"));
            Naming.rebind("rmi://localhost/SkywardDomainService", session);

            System.out.println("SkywardDomain is open for business");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
