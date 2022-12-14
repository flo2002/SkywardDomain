package fhv.ws22.se.skyward.domain;

import fhv.ws22.se.skyward.domain.dtos.AbstractDto;
import fhv.ws22.se.skyward.domain.dtos.RoomDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SessionService extends Remote {
    <T extends AbstractDto> List getAll(Class<T> clazz) throws RemoteException;
    <T extends AbstractDto> void add(T t) throws RemoteException;
    <T extends AbstractDto> void update(UUID id, T t) throws RemoteException;
    <T extends AbstractDto> void delete(UUID id, Class<T> clazz) throws RemoteException;
    List<RoomDto> getAvailableRooms(LocalDateTime checkIn, LocalDateTime checkOut) throws RemoteException;
}
