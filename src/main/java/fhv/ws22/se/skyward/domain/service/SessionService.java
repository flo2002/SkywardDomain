package fhv.ws22.se.skyward.domain.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionService extends Remote {
    ServiceProviderService getServiceProvider() throws RemoteException;
}
