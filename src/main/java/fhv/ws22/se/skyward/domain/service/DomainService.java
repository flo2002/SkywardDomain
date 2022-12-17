package fhv.ws22.se.skyward.domain.service;

import fhv.ws22.se.skyward.domain.dtos.AbstractDto;
import fhv.ws22.se.skyward.domain.dtos.RoomDto;
import fhv.ws22.se.skyward.domain.model.AbstractModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DomainService extends Remote {
    <T extends AbstractDto> T get(UUID id, Class<T> clazz) throws RemoteException;
    <T extends AbstractDto> List getAll(Class<T> clazz) throws RemoteException;
    <T extends AbstractDto> void add(T t) throws RemoteException;
    <T extends AbstractDto> UUID addAndReturnId(Class<T> clazz, T t) throws RemoteException;
    <T extends AbstractDto> void update(UUID id, T t) throws RemoteException;
    <T extends AbstractDto> void delete(UUID id, Class<T> clazz) throws RemoteException;
    List<RoomDto> getAvailableRooms(LocalDateTime checkIn, LocalDateTime checkOut) throws RemoteException;

    void handlePayment(String payment) throws RemoteException;
}
