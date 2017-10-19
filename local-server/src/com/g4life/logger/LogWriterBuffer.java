/* 
 *//***********************************************************************************
 * <summary>
 * Project name: local-server
 * Class name: LogWriterBuffer.java
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import com.g4life.common.ValueConstant;

// TODO: Auto-generated Javadoc
/**
 * The Class LogWriterBuffer.
 */
public class LogWriterBuffer {
	
	/** The log directory. */
	private static String logDirectory = "log/";

	/** The prefix file name. */
	private static String prefixFileName = "";

	/** The file name. */
	private String fileName = "message.log";

	/** The file size. */
	private int fileSize = 5 * 1024 * 1024;

	/** The baos. */
	private ByteArrayOutputStream baos = null;

	/** The out. */
	private PrintStream out = null;

	/** The max file. */
	private int maxFile = 1;

	// private PrintStream systemOut = null;

	/**
	 * Instantiates a new log writer buffer.
	 */
	public LogWriterBuffer() {
		baos = new  ByteArrayOutputStream();
		try {
			out = new PrintStream(baos, false, ValueConstant.DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// systemOut = new PrintStream( baos );
	}

	/**
	 * Instantiates a new log writer buffer.
	 *
	 * @param fileName the file name
	 */
	public LogWriterBuffer(String fileName) {
		this();

		this.fileName = fileName;
	}

	/**
	 * Sets the log directory.
	 *
	 * @param logDirectory the new log directory
	 */
	public static void setLogDirectory(String logDirectory) {
		LogWriterBuffer.logDirectory = logDirectory;
	}

	/**
	 * Gets the log directory.
	 *
	 * @return the log directory
	 */
	public static String getLogDirectory() {
		return logDirectory;
	}

	/**
	 * Sets the prefix file name.
	 *
	 * @param prefixFileName the new prefix file name
	 */
	public static void setPrefixFileName(String prefixFileName) {
		LogWriterBuffer.prefixFileName = prefixFileName;
	}

	/**
	 * Gets the prefix file name.
	 *
	 * @return the prefix file name
	 */
	public static String getPrefixFileName() {
		return prefixFileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * Adds the log buffer.
	 *
	 * @param bOutput the b output
	 * @param str the str
	 */
	public void addLogBuffer(boolean bOutput, String str) {
		out.print(str);
		internalCheck();
	}

	/**
	 * Adds the log buffer.
	 *
	 * @param bOutput the b output
	 * @param indent the indent
	 * @param e the e
	 */
	public void addLogBuffer( boolean bOutput, String indent,
			Throwable e) {
		if (bOutput) {
			out.print(indent + " ");
			e.printStackTrace(out);
		}

		internalCheck();
	}

	/**
	 * Internal check.
	 */
	private void internalCheck() {
		if (baos.size() > Math.min(LogWriter.maxSize, fileSize)) {
		//	LogWriter.wakeup();
		}
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return baos.size();
	}

	/**
	 * Write.
	 */
	public void write() {
		flush();
		if (size() == 0)
			return;
		byte b[] = baos.toByteArray();
		baos.reset();
		File dir = new File(logDirectory);
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
		} catch (SecurityException e) {
		}
		String fName = getFullPathFileName();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(fName, "rw");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			// �t�@�C���̏I�[�܂ňړ�����
			raf.seek(raf.length());

			// �o�b�t�@��S�ď����o��
			raf.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (Exception e) {
			}
		}

		// ���O�t�@�C���̃T�C�Y���傫���Ȃ����ꍇ�A�t�@�C���𐢑��シ��
		File file = new File(fName);
		if (file.exists()) {
			if (file.length() >= fileSize) {
				renameAndGzip(fName);
			}
		}
	}

	/**
	 * Gets the full path file name.
	 *
	 * @return the full path file name
	 */
	private String getFullPathFileName() {
		if (prefixFileName != null && prefixFileName.length() > 0) {
			return logDirectory + "/" + prefixFileName + "_" + fileName;
		} else {
			return logDirectory + "/" + fileName;
		}
	}

	/**
	 * Flush.
	 */
	public void flush() {
		try {
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rename and gzip.
	 *
	 * @param fname the fname
	 */
	private void renameAndGzip(String fname) {
		for (int i = maxFile - 2; i >= 0; i--) {
			File fromFile = new File(fname + "." + i + ".gz");
			File toFile = new File(fname + "." + (i + 1) + ".gz");
			if (toFile.exists()) {
				toFile.delete();
			}
			if (fromFile.exists()) {
				fromFile.renameTo(toFile);
			}

		}
		File file = new File(fname);
		gzip(fname);
		file.delete();

	}

	/**
	 * Gzip.
	 *
	 * @param fname the fname
	 */
	private void gzip(String fname) {
		try {
			InputStream is = new FileInputStream(fname);
			OutputStream os = new GZIPOutputStream(new FileOutputStream(fname
					+ ".0.gz"));
			byte[] buf = new byte[1024];
			int len;

			while ((len = is.read(buf)) != -1) {
				os.write(buf, 0, len);
			}

			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close.
	 */
	public void close() {
		write();
	}

	/**
	 * Gets the max file.
	 *
	 * @return the max file
	 */
	public int getMaxFile() {
		return maxFile;
	}

	/**
	 * Sets the max file.
	 *
	 * @param maxFile the new max file
	 */
	public void setMaxFile(int maxFile) {
		this.maxFile = maxFile;
	}

}
