/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author patty
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    private DVDLibraryAuditDao auditDao;
    
    DVDLibraryDao dao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    /**
     *
     * @param dvdId
     * @param dvd
     * @return
     * @throws DVDLibraryPersistenceException
     * @throws DVDLibraryDataValidationException
     */
    @Override
    public void addDVD(String dvdId, DVD dvd) throws DVDLibraryPersistenceException,
            DVDLibraryDataValidationException {
        validateDVDLibraryData(dvd);
        dao.addDVD(dvdId, dvd);
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(String dvdId) throws DVDLibraryPersistenceException {
        return dao.getDVD(dvdId);
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryPersistenceException {
        return dao.removeDVD(dvdId);
    }

    @Override
    public List<DVD> getSearchDVDs(String search) throws DVDLibraryPersistenceException {
        return dao.getSearchDVDs(search);
    }

    @Override
    public String getNewId() throws DVDLibraryPersistenceException {
        return dao.getNewId();
    }

    private void validateDVDLibraryData(DVD dvd) throws
            DVDLibraryDataValidationException {

        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().trim().length() == 0
                || dvd.getRatingMPAA() == null
                || dvd.getRatingMPAA().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0) {

            throw new DVDLibraryDataValidationException(
                    "ERROR: Most fields [Title, Release Date, MPAA Rating, Director's Name, Studio] are required.");
        }
    }

}
