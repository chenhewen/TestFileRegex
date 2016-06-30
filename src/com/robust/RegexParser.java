package com.robust;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class RegexParser {

	public static final String STRING_TAG_NODE = "<string[\\s\\S]*?>[\\s\\S]*?</string[\\s\\S]*?>";

	public static final String NAME_ATTRIBUTE = "(?<=name=\").*?(?=\")";

	public static final String STRING_TAG_PRE = "<string[\\s\\S]*?>";

	public static final String STRING_TAG_POST = "</string[\\s\\S]*?>";

	public String parse(String stringTagNode, Matcher outsideMatcher, String regext) {
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

	public List<Result> getResultList(File file, ParseChecker checker) {
		List<Result> list = new ArrayList<Result>();
		try {
			Pattern pattern = Pattern.compile(STRING_TAG_NODE);
			FileInputStream fis = new FileInputStream(file);
			String fileStr = IOUtils.toString(fis, StandardCharsets.UTF_8);
			Matcher matcher = pattern.matcher(fileStr);
			int i = 1;
			while (matcher.find()) {
				String matchText = matcher.group();
				String name = parseXmlGetNameAttribute(matchText, null);
				String textContent = parseXmlGetTextContent(matchText);
				Result r = new Result(name, textContent);
				if (checker.passCheck(matchText)) {
					list.add(r);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<String> getNameList(File file, ParseChecker checker) {
		List<Result> resultList = getResultList(file, checker);
		List<String> list = new ArrayList<String>();
		for (Result r : resultList) {
			String name = r.getName();
			list.add(name);
		}
		return list;
	}

	public String parseXmlGetNameAttribute(String stringTagNode, Matcher outsideMatcher) {
		return parse(stringTagNode, outsideMatcher, NAME_ATTRIBUTE);
	}

	public String parseXmlGetTextContent(String stringTagNode) {
		String stringTagPre = parse(stringTagNode, null, STRING_TAG_PRE);
		String stringTagPost = parse(stringTagNode, null, STRING_TAG_POST);

		String leftStr = stringTagNode.split(stringTagPre)[1];
		String result = leftStr.substring(0, leftStr.lastIndexOf(stringTagPost));

		return result;
	}

}
