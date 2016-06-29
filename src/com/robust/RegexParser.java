package com.robust;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class RegexParser {

	public static final String STRING_TAG_NODE = "<string.*?>[\\s\\S]*?</string>";
	
	public static final String NAME_ATTRIBUTE = "(?<=name=\").*?(?=\")";
	
	public static final String TEXT_CONTENT_ATRRIBUTE = "";
	
	public List<String> parseXmlGetStringTagNode(File file) throws IOException {
		
		List<String> result = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(STRING_TAG_NODE);
		FileInputStream fis = new FileInputStream(file);
		String fileStr = IOUtils.toString(fis, StandardCharsets.UTF_8);
		Matcher matcher = pattern.matcher(fileStr);
		int i = 1;
		while (matcher.find()) {
			result.add(matcher.group());
		}
		
		return result;
	}
	
	public String parseXmlGetSingleAtrribute(String stringTagNode, Matcher outsideMatcher, String regext) {
		Matcher matcher = null;
		if (outsideMatcher == null) {
			Pattern pattern = Pattern.compile(regext);
			matcher = pattern.matcher(stringTagNode);
		}
		
		while (matcher.find()) {
			return matcher.group();
		}
		
		return null;
	}
	
	public String parseXmlGetNameAttribute(String stringTagNode, Matcher outsideMatcher) {
		return parseXmlGetSingleAtrribute(stringTagNode, outsideMatcher, TEXT_CONTENT_ATRRIBUTE);
	}
	
	public String parseXmlGetTextContent(String stringTagNode, Matcher outsideMatcher) {
		return parseXmlGetSingleAtrribute(stringTagNode, outsideMatcher, TEXT_CONTENT_ATRRIBUTE);
	}
	
	public void printStringTagNodeList(List<String> list) {
		for(String str : list) {
			System.out.println(str);
		}
		
		System.out.println("-------------------total: " + list.size() + "-------------------");
	} 
	
	public void printNameAttribute(String name) {
		System.out.println(name);
	}
}
