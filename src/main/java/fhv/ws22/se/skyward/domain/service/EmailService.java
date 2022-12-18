package fhv.ws22.se.skyward.domain.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EmailService extends Remote {
    void sendEmail(String email, String subject, String message) throws RemoteException;
}
