package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.FileManager;
import com.robust.RegexParser;

public class TestRegexParser {

	public static final String XML_FILE = "resources/model/strings.xml";
	
	private static RegexParser mRegexParser;

	@BeforeClass
    public static void init() {
    	System.out.println("-------------------------TestRegexParser------------------------");
    	mRegexParser = new RegexParser();
    }
	
	@Test
	public void testFileExists() {
		Assert.assertTrue(new File(XML_FILE).exists());
	}
	
	@Test
	public void testParseXmlGetStringTagNode() {
		try {
			List<String> stringTagNodeList = mRegexParser.parseXmlGetStringTagNode(new File(XML_FILE));
			mRegexParser.printStringTagNodeList(stringTagNodeList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseXmlGetNameAttribute() {
		try {
			List<String> stringTagNodeList = mRegexParser.parseXmlGetStringTagNode(new File(XML_FILE));
			for (String str : stringTagNodeList) {
				String nameAttribute = mRegexParser.parseXmlGetNameAttribute(str, null);
				mRegexParser.printNameAttribute(nameAttribute);
			}
			System.out.println("total : " + stringTagNodeList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
