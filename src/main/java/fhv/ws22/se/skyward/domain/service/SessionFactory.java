package fhv.ws22.se.skyward.domain.service;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import fhv.ws22.se.skyward.AppConfig;
import fhv.ws22.se.skyward.domain.DataService;
import fhv.ws22.se.skyward.persistence.DataGenerator;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.HashMap;

public class SessionFactory {
    private static SessionFactory instance;
    private final HashMap<BigInteger, Session> sessions;
    private final Injector injector;
    @Inject
    private DataService dataService;

    private SessionFactory() {
        sessions = new HashMap<>();

        DataGenerator dataGenerator = new DataGenerator();
        injector = Guice.createInjector(new AppConfig());
        injector.injectMembers(dataGenerator);
        injector.injectMembers(this);
        dataGenerator.generateData();
    }

    public static SessionFactory getInstance() {
        if (instance == null) {
            instance = new SessionFactory();
        }
        return instance;
    }

    public Session getSession(BigInteger id) throws RemoteException {
        if (sessions.get(id) == null) {
            Session session = new Session(dataService);
            sessions.put(id, session);
        }
        return sessions.get(id);
    }
}
