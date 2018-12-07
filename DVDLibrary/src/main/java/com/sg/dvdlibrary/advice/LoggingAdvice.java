/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author patty
 */
public class LoggingAdvice {

    DVDLibraryAuditDao auditDao;

    public LoggingAdvice(DVDLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg.toString();
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDLibraryPersistenceException e) {
            System.err.println(
                    "ERROR: Couldnot create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryForException(JoinPoint jp, Throwable ex) {
//        Object[] args = jp.getArgs();
        String auditEntry = ex.getMessage();
//        for (Object currentArg : args) {
//            auditEntry += currentArg.toString();
//        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDLibraryPersistenceException e) {
            System.err.println(
                    "ERROR: Couldnot create audit entry in LoggingAdvice.");
        }
    }

}
