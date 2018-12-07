/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author patty
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String dvdId, DVD dvd)
            throws DVDLibraryPersistenceException {
        DVD newDVD = dvds.put(dvdId, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs()
            throws DVDLibraryPersistenceException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }
    
    // look at the list of movies, iterate through them, add
    // dvds matching search string to new list, return it for display
    @Override
    public List<DVD> getSearchDVDs(String search)
            throws DVDLibraryPersistenceException {
        loadLibrary();
        ArrayList<DVD> allDVDs = new ArrayList<>(dvds.values());
        ArrayList<DVD> results = new ArrayList<>();
        allDVDs.stream().filter((d) -> (d.getTitle() != null && d.getTitle().toLowerCase().contains(search.toLowerCase()))).forEachOrdered((d) -> {
            results.add(d);
        });
        return results;
    }

    @Override
    public DVD getDVD(String dvdId)
            throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public DVD removeDVD(String dvdId)
            throws DVDLibraryPersistenceException {
        DVD removedDVD = dvds.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException(
                    "-_- Could not load library data into memory.");
        }

        String currentLine;
        String[] currentTokens;
        String date;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setTitle(currentTokens[1]);
            
//            date = currentTokens[2];
            
            
            currentDVD.setReleaseDate(currentTokens[2]);
            currentDVD.setRatingMPAA(currentTokens[3]);
            currentDVD.setDirectorName(currentTokens[4]);
            currentDVD.setStudio(currentTokens[5]);
            currentDVD.setNote(currentTokens[6]);

            dvds.put(currentDVD.getDVDId(), currentDVD);
        }
        scanner.close();
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save DVD data.", e);
        }

        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getDVDId() + DELIMITER
                    + currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate().toString() + DELIMITER
                    + currentDVD.getRatingMPAA() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getNote());
            out.flush();
        }
        out.close();
    }
    
    
    // to generate DVD ID
        // after mark broke my program,
        // created new methods in Dao and DaoFileImpl
        // to get new idvalue based on Integer.valueOf keyset,
        // then changing back to string
    // called from controller
    @Override
    public String getNewId()throws DVDLibraryPersistenceException{
        
        int intId = 0;
        for (String key : dvds.keySet()){
            if (Integer.valueOf(key) > intId){
                intId = Integer.valueOf(key);
            }
        }
        return String.valueOf(intId + 1);
    }

}
