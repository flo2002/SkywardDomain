package fhv.ws22.se.skyward.domain.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceProviderService extends Remote {
    Object getService(Class<? extends Remote> clazz) throws RemoteException;
}
