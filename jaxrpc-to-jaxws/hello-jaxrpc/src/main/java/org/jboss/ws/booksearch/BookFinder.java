package org.jboss.ws.booksearch;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookFinder extends Remote  {
    public SearchResult search(SearchCriteria criteria) throws RemoteException;;
}
