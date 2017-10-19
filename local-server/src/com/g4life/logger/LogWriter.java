/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: LogWriter.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.g4life.common.ValueConstant;

// TODO: Auto-generated Javadoc
/**
 * The Class LogWriter.
 */
public class LogWriter implements Runnable {

	/** The Constant lineseparator. */
	public static final String lineseparator = "\n";

	/** The message log. */
	private LogWriterBuffer messageLog = new LogWriterBuffer();

	/** The thread. */
	private Thread thread = null;
	
	/** The obj wakeup. */
	private static Object objWakeup = new Object();

	/** The log writer. */
	private static LogWriter logWriter;

	/** The max size. */
	public static int maxSize;

	/**
	 * Instantiates a new log writer.
	 */
	private LogWriter() {
		readProperties();
		thread = new Thread(this);

		thread.start();
		try {
			// write log when shutdown
			Runtime.getRuntime().addShutdownHook(new CleanupThread());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Class CleanupThread.
	 */
	class CleanupThread extends Thread {
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			addLog("Shutting down...");
			writeLogs();
		}

		/**
		 * Write logs.
		 */
		private void writeLogs() {
			messageLog.write();

		}
	}

	/**
	 * Gets the log writer.
	 *
	 * @return the log writer
	 */
	public static synchronized LogWriter getLogWriter() {
		if (logWriter == null) {
			logWriter = new LogWriter();
		}
		return logWriter;
	}

	/**
	 * Read properties.
	 */
	private synchronized void readProperties() {
		String propertyName = "/config/LogWriter.properties";
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(new FileInputStream(new File(propertyName)),ValueConstant.DEFAULT_ENCODING));
		} catch (FileNotFoundException e) {
			System.out.println("LogWriter: " + propertyName + " is not found?");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			setupLogWriterBuffer(prop, messageLog);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setup log writer buffer.
	 *
	 * @param prop the prop
	 * @param log the log
	 */
	private void setupLogWriterBuffer(Properties prop, LogWriterBuffer log) {

		// Default is 5M
		int size = Integer.parseInt(prop.getProperty("LogFileSize", "5242880"));

		String fileName = prop.getProperty("LogFileName", "message.log");

		String logDirectory = prop.getProperty("LogFolderName", "/log");

		int maxFile = Integer.parseInt(prop.getProperty("NumberOfFiles", "1"));

		log.setFileSize(size);

		log.setFileName(fileName);

		log.setMaxFile(maxFile);

		LogWriterBuffer.setLogDirectory(logDirectory);

	}

	/**
	 * Gets the package log writer.
	 *
	 * @param pkgName the pkg name
	 * @return the package log writer
	 */
	public static LogWriterManager getPackageLogWriter(String pkgName) {
		getLogWriter();
		return logWriter.getPackageWriter(pkgName);
	}

	/**
	 * Gets the package writer.
	 *
	 * @param pkgName the pkg name
	 * @return the package writer
	 */
	private LogWriterManager getPackageWriter(String pkgName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// Thread.setThreadName("LogWriter");
		thread = Thread.currentThread();
		while (thread == Thread.currentThread()) {
			writeLogs();
		}
	}

	/**
	 * Write logs.
	 */
	private void writeLogs() {
		messageLog.write();

	}

	/**
	 * Adds the log.
	 *
	 * @param str the str
	 */
	public synchronized void addLog(String str) {

		String strOutput = getLongTime() + " " + str + "\n";

		messageLog.addLogBuffer(true, strOutput);

	}

	/**
	 * Adds the log.
	 *
	 * @param ex the ex
	 */
	public synchronized void addLog(Throwable ex) {

		String strOutput = getLongTime() + " " + ex.getMessage() + "\n";

		messageLog.addLogBuffer(true, strOutput, ex);

	}

	/**
	 * Gets the long time.
	 *
	 * @return the long time
	 */
	private String getLongTime() {
		Date crrTime = Calendar.getInstance().getTime();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		return fomat.format(crrTime);

	}

	/**
	 * Stop log.
	 */
	public void stopLog() {
		Thread t = thread;
		thread = null;
		try {
			t.interrupt();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		System.out.println("LogWriter: stopLog Called");
	}

	/**
	 * Adds the package log writer.
	 *
	 * @param trim the trim
	 */
	public static void addPackageLogWriter(String trim) {
		logWriter.addLog(trim);

	}

	/**
	 * Adds the package log writer.
	 *
	 * @param ex the ex
	 */
	public static void addPackageLogWriter(Throwable ex) {
		logWriter.addLog(ex);

	}
}
