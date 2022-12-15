package fhv.ws22.se.skyward.domain.service;

import fhv.ws22.se.skyward.domain.dtos.BookingDto;
import fhv.ws22.se.skyward.domain.dtos.InvoiceDto;
import fhv.ws22.se.skyward.domain.dtos.RoomDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface TmpDataService extends Remote {
    List<RoomDto> filterRooms(List<RoomDto> rooms, HashMap<String, Boolean> filterMap) throws RemoteException;
    void setRoomFilterMap(HashMap<String, Boolean> filterMap) throws RemoteException;
    HashMap<String, Boolean> getRoomFilterMap() throws RemoteException;

    BookingDto getTmpBooking() throws RemoteException;
    void resetTmpBooking() throws RemoteException;
    void setTmpBooking(BookingDto booking) throws RemoteException;

    InvoiceDto getTmpInvoice() throws RemoteException;
    void resetTmpInvoice() throws RemoteException;
    void setTmpInvoice(InvoiceDto invoice) throws RemoteException;
}
