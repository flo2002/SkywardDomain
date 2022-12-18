package fhv.ws22.se.skyward.domain.service;

import fhv.ws22.se.skyward.domain.DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider extends UnicastRemoteObject implements ServiceProviderService {
    private Map<Class<? extends Remote>, Object> services;

    public ServiceProvider(DataService ds) throws RemoteException {
        services = new HashMap<>();
        services.put(DomainService.class, new Domain(ds));
        services.put(TmpDataService.class, new TmpData(this));
        services.put(EmailService.class, new Email());
    }

    public Object getService(Class<? extends Remote> clazz) throws RemoteException {
        return services.get(clazz);
    }
}
