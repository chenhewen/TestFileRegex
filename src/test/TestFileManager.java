package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.FileManager;

public class TestFileManager {

	public static final String[] INNER_LANGUAGE = new String[] {
			"values-zh-rCN",
			"values-zh",
			"values-th",
			"values-in",
			"values-ar",
			"values-fa",
			"values-tr",
			"values-vi-rVN",
			"values-ru",
			"values"
	};
	
	public static final String[] OUTER_LANGUAGE = new String[] {
			"strings_iw.xml",
			"strings_de.xml",
			"strings_it_IT.xml",
			"strings_da.xml",
			"strings_ja.xml",
			"strings_hr.xml",
			"strings_pt_PT.xml",
			"strings_fr.xml",
			"strings_sl.xml",
			"strings_pt.xml",
			"strings_es.xml",
			"strings_pl_rPL.xml",
			"strings_ko.xml",
			"strings_cs.xml",
			"strings_nb.xml",
			"strings_nl.xml",
			"strings_lt.xml",
			"strings_hu.xml"
	};
	
	public static final String ANDROID_RES = "resources/android_res";
	
	public static final String FLAT_RES = "resources/flat_res";
	
	public static final String COMMON_FOLDER = "resources/test_get_spec_file_folder";

	private static FileManager mFileManager;
	
	@BeforeClass
    public static void init() {
    	System.out.println("-------------------------TestGetSpecFile------------------------");
    	mFileManager = new FileManager();
    }
	
	@Test
	public void testFileExists() {
		Assert.assertTrue(new File(ANDROID_RES).exists());
		Assert.assertTrue(new File(FLAT_RES).exists());
		Assert.assertTrue(new File(COMMON_FOLDER).exists());
	}
	
	@Test
	public void  testGetAndroidStringFile() {
		List<File> list = new ArrayList<File>();
		mFileManager.getFile(new File(ANDROID_RES), Integer.MAX_VALUE, "strings.xml", list);
		String[] innerLanguageFolderNameArray = mFileManager.getInnerLanguageFolderNameArray(list);
		Assert.assertArrayEquals(innerLanguageFolderNameArray, INNER_LANGUAGE);
	}
	
	@Test
	public void testGetFlatStringFile() {
		List<File> list = new ArrayList<File>();
		mFileManager.getFile(new File(FLAT_RES), Integer.MAX_VALUE, "strings_.*.xml", list);
		String[] outerLanguageFolderNameArray = mFileManager.getOuterLanguageFolderNameArray(list);
		Assert.assertArrayEquals(outerLanguageFolderNameArray, OUTER_LANGUAGE);
	}
	
	public void testGetSpecFile() {
		// test get spec files with deep and regex limitation
		List<File> list = new ArrayList<File>();
		mFileManager.getFile(new File(COMMON_FOLDER), 1, ".*", list);
		//fileManager.printFileList(list);
	}
}
