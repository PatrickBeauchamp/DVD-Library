/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author patty
 */
public interface DVDLibraryDao {

    DVD addDVD(String dvdId, DVD dvd)
            throws DVDLibraryPersistenceException;

    List<DVD> getAllDVDs()
            throws DVDLibraryPersistenceException;

    DVD getDVD(String dvdId)
            throws DVDLibraryPersistenceException;

    DVD removeDVD(String dvdId)
            throws DVDLibraryPersistenceException;

    List<DVD> getSearchDVDs(String search)
            throws DVDLibraryPersistenceException;

    String getNewId() throws DVDLibraryPersistenceException;

}
