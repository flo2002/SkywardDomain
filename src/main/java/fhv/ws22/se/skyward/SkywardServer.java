package fhv.ws22.se.skyward;

import fhv.ws22.se.skyward.domain.ServiceProvider;
import fhv.ws22.se.skyward.domain.Session;
import fhv.ws22.se.skyward.domain.SessionFactory;

import java.math.BigInteger;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class SkywardServer {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:./src/main/resources/skyward.policy");

        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println("Registry runs already");
        }

        try {
            ServiceProvider serviceProvider = new ServiceProvider();
            serviceProvider.registerService("session", SessionFactory.getInstance().getSession(new BigInteger("1")));
            Naming.rebind("rmi://localhost/SkywardDomainService", serviceProvider);

            System.out.println("SkywardDomain is open for business");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
