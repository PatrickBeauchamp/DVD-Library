/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patty
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("DVD Library Main Menu");
        io.print("1) List All DVDs");
        io.print("2) Add a new DVD");
        io.print("3) View a DVD");
        io.print("4) Remove a DVD");
        io.print("5) Edit a DVD");
        io.print("6) Search for a DVD by Title");
        io.print("7) Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getNewDVDInfo(String dvdId) {

        // in case user does not provide info, or just hits enter
        String substituteInfo = "No data on file";

        String title = io.readString("Please enter title:");
        String releaseDate = io.readString("Please enter release date (as yyyy-mm-dd):");
        String ratingMPAA = io.readString("Please enter the MPAA rating:");
        String directorName = io.readString("Please enter director's name:");
        String studio = io.readString("Please enter studio name:");
        String note = io.readString("Please enter any notes:");
        DVD currentDVD = new DVD(dvdId);
        
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRatingMPAA(ratingMPAA);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        if (!note.equals("")) {
            currentDVD.setNote(note);
        } else {
            currentDVD.setNote(substituteInfo);
        }

        // check to see if any info was entered
        // otherwise indicate that the field was not provided by the user
//        if (!title.equals("")) {
//            currentDVD.setTitle(title);
//        } else {
//            currentDVD.setTitle(substituteInfo);
//        }
//
////        if (!releaseDate.equals("")) {
////            
////        }
//
//        if (!releaseDate.equals("")) {
//            currentDVD.setReleaseDate(releaseDate);
//        } else {
//            currentDVD.setReleaseDate(substituteInfo);
//        }
//        if (!ratingMPAA.equals("")) {
//            currentDVD.setRatingMPAA(ratingMPAA);
//        } else {
//            currentDVD.setRatingMPAA(substituteInfo);
//        }
//        if (!directorName.equals("")) {
//            currentDVD.setDirectorName(directorName);
//        } else {
//            currentDVD.setDirectorName(substituteInfo);
//        }
//        if (!studio.equals("")) {
//            currentDVD.setStudio(studio);
//        } else {
//            currentDVD.setStudio(substituteInfo);
//        }
//        if (!note.equals("")) {
//            currentDVD.setNote(note);
//        } else {
//            currentDVD.setNote(substituteInfo);
//        }

        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue.");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getDVDId() + ": "
                    + currentDVD.getTitle());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDVDIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("         DVD ID: " + dvd.getDVDId());
            io.print("          Title: " + dvd.getTitle());
            io.print("   Release Date: " + dvd.getReleaseDate().toString());
            io.print("    MPAA Rating: " + dvd.getRatingMPAA());
            io.print("Director's Name: " + dvd.getDirectorName());
            io.print("         Studio: " + dvd.getStudio());
            io.print("    Movie Notes: " + dvd.getNote());
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.print("If such a DVD existed, it is now gone. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command...");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displaySearchTitleBanner() {
        io.print("=== Search for DVD by Title ===");
    }

    public String getSearchString() {
        String search = io.readString("Enter a title or part of one to search for a DVD:");
        return search;

    }

    public DVD getEditDVDInfo(DVD dvd) {

        String title = io.readString("Please enter NEW title, otherwise hit enter:");
        String releaseDate = io.readString("Please enter NEW release date, otherwise hit enter:");
        String ratingMPAA = io.readString("Please enter the NEW MPAA rating, otherwise hit enter:");
        String directorName = io.readString("Please enter NEW director's name, otherwise hit enter:");
        String studio = io.readString("Please enter NEW studio name, otherwise hit enter:");
        String note = io.readString("Please enter NEW notes, otherwise hit enter:");

        // checks to see if any info was entered, if not, previous info left unchanged
        if (!title.equals("")) {
            dvd.setTitle(title);
        }
        if (!releaseDate.equals("")) {
            dvd.setReleaseDate(releaseDate);
        }
        if (!ratingMPAA.equals("")) {
            dvd.setRatingMPAA(ratingMPAA);
        }
        if (!directorName.equals("")) {
            dvd.setDirectorName(directorName);
        }
        if (!studio.equals("")) {
            dvd.setStudio(studio);
        }
        if (!note.equals("")) {
            dvd.setNote(note);
        }
        return dvd;
    }

    public void displayPreviousDVDInfo() {
        io.print("=== EXISTING DVD INFO ===");
    }

    public void displayEditDVDSuccessBanner() {
        io.print("=== DVD Successfully Edited ===");
    }

}
