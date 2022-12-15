package fhv.ws22.se.skyward.domain.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Singleton
public class Session extends UnicastRemoteObject implements SessionService {
    private ServiceProviderService serviceProvider;

    public Session() throws RemoteException {
        super();
        serviceProvider = new ServiceProvider();
        serviceProvider.registerService("DomainService", new Domain());
        serviceProvider.registerService("TmpDataService", new TmpData(this));
    }

    public ServiceProviderService getServiceProvider() {
        return serviceProvider;
    }
}
