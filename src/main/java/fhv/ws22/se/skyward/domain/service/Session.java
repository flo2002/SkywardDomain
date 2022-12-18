package fhv.ws22.se.skyward.domain.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fhv.ws22.se.skyward.domain.DataService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Singleton
public class Session extends UnicastRemoteObject implements SessionService {
    private final ServiceProviderService serviceProvider;

    @Inject
    public Session(DataService dataService) throws RemoteException {
        super();
        serviceProvider = new ServiceProvider(dataService);
    }

    public ServiceProviderService getServiceProvider() {
        return serviceProvider;
    }
}
