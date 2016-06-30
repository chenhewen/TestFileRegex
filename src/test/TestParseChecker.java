package test;

import java.io.File;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.DomParser;
import com.robust.ParseChecker;
import com.robust.RegexParser;

public class TestParseChecker {

	public static final String XML_FILE = "resources/model/strings.xml";
	
	@BeforeClass
	public static void init() {
		System.out.println("-------------------------TestParseChecker------------------------");
	}

	@Test
	public void testFileExists() {
		Assert.assertTrue(new File(XML_FILE).exists());
	}

	@Test
	public void testParsePiece() {
		RegexParser mRegexParser = new RegexParser();
		DomParser mDomParser = new DomParser();
		ParseChecker parseChecker = new ParseChecker(mRegexParser, mDomParser);
		boolean passCheckBefore = parseChecker.passCheckBefore(new File(XML_FILE));
		Assert.assertTrue(passCheckBefore);
	}

	
}
