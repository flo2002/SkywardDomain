package fhv.ws22.se.skyward.domain.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Email extends UnicastRemoteObject implements EmailService {
    public Email() throws RemoteException {
        super();
    }
    public void sendEmail(String email, String subject, String message) throws RemoteException {
        System.out.println("Sending email to " + email + " with subject: " + subject + " and message: " + message);
    }
}
