/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author patty
 */
public class DVDLibraryController {

    DVDLibraryView view;
    private DVDLibraryServiceLayer service;

    private UserIO io = new UserIOConsoleImpl();

    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws DVDLibraryDataValidationException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        searchByTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibraryPersistenceException, DVDLibraryDataValidationException {

        // to generate DVD ID
        // after mark broke my program,
        // created new methods in Dao and DaoFileImpl
        // to get new idvalue based on Integer.valueOf keyset,
        // then changing back to string
        String dvdId = service.getNewId();

//        boolean hasErrors = false;
//        do {
            DVD newDVD = view.getNewDVDInfo(dvdId);
            try {
                service.addDVD(dvdId, newDVD);
                view.displayCreateSuccessBanner();
//                hasErrors = false;
            } catch (DVDLibraryDataValidationException e) {
//                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }

//        } while (hasErrors);
    }

    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = service.getDVD(dvdId);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        service.removeDVD(dvdId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void editDVD() throws DVDLibraryPersistenceException, DVDLibraryDataValidationException {
        view.displayEditDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = service.getDVD(dvdId);
        view.displayPreviousDVDInfo();
        view.displayDVD(dvd);
        view.getEditDVDInfo(dvd);
        service.addDVD(dvdId, dvd);
        view.displayEditDVDSuccessBanner();
    }

    private void searchByTitle() throws DVDLibraryPersistenceException {
        view.displaySearchTitleBanner();
        String search = view.getSearchString();
        List<DVD> results = service.getSearchDVDs(search);
        view.displayDVDList(results);
    }

}
