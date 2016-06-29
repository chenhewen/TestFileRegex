package com.robust;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Pattern;

public class FileManager {

	/**
	 * retrieve files match regex and level not larger than stopDeepLevel
	 * recursion
	 * 
	 * @param rootDir
	 * @param stopDeepLevel
	 * @param regex
	 * @param outside
	 */
	public void getFile(File rootDir, int stopDeepLevel, String fileRegex, List<File> outside) {
		if (stopDeepLevel < -1) {
			return;
		}

		Pattern filePattern = Pattern.compile(fileRegex);

		// if it is not a dir at all, let's test if it matches the regex.
		if (!rootDir.isDirectory()) {
			File singleFile = rootDir;
			// matches, add the file
			if (filePattern.matcher(singleFile.getName()).matches()) {
				outside.add(singleFile);
			}
			return;
		} else {
			// it is a dir, treated the dir way
			File[] listFiles = rootDir.listFiles();
			stopDeepLevel--;
			for (File file : listFiles) {
				// recursion
				getFile(file, stopDeepLevel, fileRegex, outside);
			}
		}
	}

	/**
	 * copy single file from srcFile to destFile
	 * 
	 * @param srcFile
	 * @param destFile
	 * @return true for success 
	 */
	public boolean copySingleFile(File srcFile, File destFile) {
		if (!srcFile.exists()) {
			return false;
		}

		if (!destFile.exists()) {
			File parent = destFile.getParentFile();
			boolean b = parent.mkdirs();

			try {
				destFile.createNewFile();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(srcFile);
			output = new FileOutputStream(destFile);
			byte[] data = new byte[4 * 1024]; // 4k
			while (true) {
				int len = input.read(data);
				if (len <= 0) {
					break;
				}
				output.write(data, 0, len);
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (Exception e2) {
				}
			}
			if (null != output) {
				try {
					output.flush();
					output.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return true;
	}

	public void printParentFileList(List<File> list) {
		for (File file : list) {
			System.out.println("\"" + file.getParentFile().getName() + "\",");
		}
	}
	
	public void printFileList(List<File> list) {
		for (File file : list) {
			System.out.println("\"" + file.getName() + "\",");
		}
	}
	
	public String[] getInnerLanguageFolderNameArray(List<File> list) {
		int size = list.size();
		String[] result = new String[size];
		for (int i = 0; i< size; i++) {
			result[i] = list.get(i).getParentFile().getName();
		}
		
		return result;
	}
	
	public String[] getOuterLanguageFolderNameArray(List<File> list) {
		int size = list.size();
		String[] result = new String[size];
		for (int i = 0; i< size; i++) {
			result[i] = list.get(i).getName();
		}
		
		return result;
	}
}
