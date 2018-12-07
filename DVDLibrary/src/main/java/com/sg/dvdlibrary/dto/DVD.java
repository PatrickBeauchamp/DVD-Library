/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.time.LocalDate;

/**
 *
 * @author patty
 */
public class DVD {

    private String dvdId;
    private String title;
//    private LocalDate releaseDate;
    private String releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String note;

    public DVD(String dvdId) {
        this.dvdId = dvdId;
    }

    public String getDVDId() {
        return dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public LocalDate getReleaseDate(){
//        return releaseDate.toString();
//    }
    public String getReleaseDate() {
        return releaseDate;
    }

//    public void setReleaseDate(LocalDate releaseDate){
//        this.releaseDate = releaseDate;
//    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ID: " + dvdId + " |Title";
    }

}
