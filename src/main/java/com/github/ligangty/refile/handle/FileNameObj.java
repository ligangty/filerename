package com.github.ligangty.refile.handle;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.ligangty.refile.util.FileUtil;

/**
 * This Class is used to represent a File's name, including file path, the
 * original file name, and the file name this file intend to be renamed to.
 * Because this class's objects may be used in the Vector which will be used
 * sortedly, so it implements the java.lang.Comparable.
 *
 * @author ligangty@github.com
 * @date Apr 6, 2009
 */
public class FileNameObj implements Comparable<FileNameObj> {

	/**
	 * The file path not including the file name.
	 */
	private String filePath;
	/**
	 * The original file name, not including the file path
	 */
	private String oldFileName;
	/**
	 * The file name which will be renamed to, not including the file path
	 */
	private String newFileName;
	/**
	 * The file's original postFix, not including dot.
	 */
	private String oldPostFix;
	/**
	 * The file's new postFix which will be renamed to, not including dot.
	 */
	private String newPostFix;

	public FileNameObj(String fullFileName) {
		getFilePathFromFullFileName(fullFileName);
		getOldFileNameFromFullFileName(fullFileName);
		setOldPostFix(FileUtil.getPostFixFromFileName(fullFileName));
		setNewFileName(this.oldFileName);
		setNewPostFix(this.oldPostFix);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public final void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getOldPostFix() {
		return oldPostFix;
	}

	public final void setOldPostFix(String postFix) {
		this.oldPostFix = postFix;
	}

	public String getNewPostFix() {
		return newPostFix;
	}

	public final void setNewPostFix(String newPostFix) {
		this.newPostFix = newPostFix;
	}

	public String getFullOldFileName() {
		return this.oldFileName + "." + this.oldPostFix;
	}

	public final void getFilePathFromFullFileName(String fullFileName) {
		if (fullFileName.contains("\\")) {
			fullFileName = fullFileName.replaceAll("\\\\", "/");
		}
		setFilePath(fullFileName.substring(0, fullFileName.lastIndexOf("/")));
	}

	public final void getOldFileNameFromFullFileName(String fullFileName) {
		if (fullFileName.contains("\\")) {
			fullFileName = fullFileName.replaceAll("\\\\", "/");
		}
		setOldFileName(FileUtil.getFileNameWithoutPostFix(fullFileName.substring(fullFileName.lastIndexOf("/") + 1)));
	}

	/**
	 * rename the file from oldFileName to newFileName in the filePath
	 *
	 * @throws java.io.FileNotFoundException if the filePath + oldFileName can
	 *                                       not be found
	 */
	public void renameFile() throws FileNotFoundException {
		File file = new File(filePath + "/" + oldFileName + "." + getOldPostFix());
		if (!file.exists()) {
			throw new FileNotFoundException("file " + "\"" + filePath + "/" + oldFileName + "\" can not be found");
		}
		file.renameTo(new File(filePath + "/" + newFileName + "." + getNewPostFix()));
		System.out.println(
			"\"" + filePath + "/" + oldFileName + "." + getOldPostFix() + "\" to \"" + filePath + "/" + newFileName + "."
				+ getNewPostFix() + "\"" + " done!");
	}

	/**
	 * compare the two fileNameObjs. When oldFileName and filePath are both
	 * equal, the two fileNameObjs are equal; else compared by the filePath
	 * string or by the oldFileName string(if the filePath also equals)
	 *
	 * @param fileNameObj
	 * @return
	 */
	@Override
	public int compareTo(FileNameObj fileNameObj) {
		if (this.getOldFileName().equals(fileNameObj.getOldFileName()) && this.getFilePath().equals(fileNameObj.getFilePath())) {
			return 0;
		} else if (!this.getFilePath().equals(fileNameObj.getFilePath())) {
			return this.getFilePath().compareTo(fileNameObj.getFilePath());
		} else {
			return this.getOldFileName().compareTo(fileNameObj.getOldFileName());
		}
	}

	/**
	 * When oldFileName and filePath are both equal, the two fileNameObjs are
	 * equal
	 *
	 * @param fileNameObj
	 * @return
	 */
	@Override
	public boolean equals(Object fileNameObj) {
		if (!(fileNameObj instanceof FileNameObj)) {
			return false;
		} else {
			FileNameObj fNameObj = (FileNameObj) fileNameObj;
			return this.getOldFileName().equals(fNameObj.getOldFileName()) && this.getFilePath().equals(fNameObj.getFilePath());
		}
	}
}
