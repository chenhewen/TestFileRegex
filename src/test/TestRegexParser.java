package test;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.RegexParser;

public class TestRegexParser {

	public static final String XML_FILE = "resources/model/strings_test.xml";
	
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

	/*public void testParsePiece() {
		try {
			Result parseResult = mRegexParser.parsePiece(new File(XML_FILE));
			printSuccessAndIgnore(parseResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	public void testParseXmlGetNameAttribute() {
		try {
			List<String> stringTagNodeList = mRegexParser.parsePiece(new File(XML_FILE));
			int lineNum = 1;
			for (String str : stringTagNodeList) {
				String nameAttribute = mRegexParser.parseXmlGetNameAttribute(str, null);
				System.out.println(String.format("No: %s\t%s", lineNum++, nameAttribute));
			}
			System.out.println("total : " + stringTagNodeList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseXmlGetTextContent() {
		try {
			List<String> stringTagNodeList = mRegexParser.parsePiece(new File(XML_FILE));
			int lineNum = 1;
			for (String str : stringTagNodeList) {
				String contentText = mRegexParser.parseXmlGetTextContent(str);
				System.out.println(String.format("No: %s\t%s", lineNum++, contentText));
			}
			System.out.println("total : " + stringTagNodeList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	/*private void printSuccessAndIgnore(Result parseResult) {
		List<String> successList = parseResult.getSuccessList();
		List<String> ignoreList = parseResult.getIgnoreList();
		for(String str : successList) {
			System.out.println(str);
		}
		System.out.println(String.format("-----------success: %s ------------", successList.size()));
		
		for(String str : ignoreList) {
			System.out.println(str);
		}
		System.out.println(String.format("-----------ignore: %s ------------", ignoreList.size()));
		System.out.println(String.format("=======total: %s =======", (successList.size() + ignoreList.size())));
	}
	
	public void printStringTagNodeList(List<String> list) {
		for(String str : list) {
			System.out.println(str);
		}
		
		System.out.println("-------------------total: " + list.size() + "-------------------");
	} 
	
	public void print(String name) {
		System.out.println(name);
	}*/
}
