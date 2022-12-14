package fhv.ws22.se.skyward.domain;

import com.google.inject.Singleton;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

@Singleton
public class ServiceProvider extends UnicastRemoteObject implements ServiceProviderService {
    private HashMap<String, Object> services;

    public ServiceProvider() throws RemoteException {
        services = new HashMap<>();
    }

    public void registerService(String serviceName, Object service) throws RemoteException {
        services.put(serviceName, service);
    }

    public Object getService(String serviceName) throws RemoteException {
        return services.get(serviceName);
    }
}
