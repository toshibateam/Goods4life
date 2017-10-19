/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: LogWriterManager.java
 * </summary>
 * <history>
 * VERSION	DATE	BY		CHANGE/COMMENT
 *--------------------------------------------------------------------------------------------------------------
 * V1.00	24-03-2017	(TSDV) 	Initialize source code
 * -------------------------------------------------------------------------------------------------------------
 * </history>
 * <Copyright>
 * (C) Copyright TOSHIBA Corporation 2016-2017. All rights reserved.                                             
 * </Copyright>                                                                                                                                  
 *******************************************************************************
 */

package com.g4life.logger;

// TODO: Auto-generated Javadoc
/**
 * The Class LogWriterManager.
 */
public class LogWriterManager {

    /** The package name. */
    private String packageName = "";
    
    /** The package log level. */
    private int packageLogLevel = 3;

    /**
     * Gets the package log writer.
     *
     * @param pkg the pkg
     * @return the package log writer
     */
    public static LogWriterManager getPackageLogWriter(String pkg) {
        LogWriterManager plw = null;
        plw = LogWriter.getPackageLogWriter(pkg);
        if (plw == null) {
            plw = new LogWriterManager(pkg);
        }
        return plw;
    }
    
    /**
     * Instantiates a new log writer manager.
     *
     * @param name the name
     */
    private LogWriterManager(String name) {
        if (name != null) {
            packageName = (name + "         ").substring(0, 8) + " ";
        } else {
            packageName = "         ";
        }
    }
    
    /**
     * Sets the package log level.
     *
     * @param level the new package log level
     */
    void setPackageLogLevel(int level) {
        packageLogLevel = level;
    }
    
    /**
     * Gets the package log level.
     *
     * @return the package log level
     */
    public int getPackageLogLevel() {
        return packageLogLevel;
    }
    
    /**
     * Adds the log.
     *
     * @param str the str
     */
    public void addLog(String str) {
      LogWriter.addPackageLogWriter(packageName + str);
    }
    
    /**
     * Adds the log.
     *
     * @param ex the ex
     */
    public void addLog(Throwable ex) {
        LogWriter.addPackageLogWriter(ex);
    }
}
