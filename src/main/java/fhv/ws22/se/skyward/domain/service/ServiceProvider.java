package fhv.ws22.se.skyward.domain.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServiceProvider extends UnicastRemoteObject implements ServiceProviderService {
    private Map<Class, Object> services;

    public ServiceProvider() throws RemoteException {
        services = new HashMap<>();
        services.put(DomainService.class, new Domain());
        services.put(TmpDataService.class, new TmpData(this));
    }

    public Object getService(String serviceName) throws RemoteException {
        return services.get(serviceName);
    }
}
