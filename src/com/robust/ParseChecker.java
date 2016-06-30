package com.robust;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ParseChecker {

	RegexParser mRegexParser;
	DomParser mDomParser;

	public ParseChecker(RegexParser mRegexParser, DomParser mDomParser) {
		this.mRegexParser = mRegexParser;
		this.mDomParser = mDomParser;
	}

	public boolean passCheckBefore(File file) {
		return passCheckBefore1(file) && passCheckBefore2(file);
	}

	public boolean passCheckBefore1(File file) {

		boolean result = true;

		System.out.println("check: passCheckBefore1");
		List<Result> resultList = mDomParser.getResultList(file);
		for (Result parse : resultList) {
			String textContent = parse.getTextContent();
			if (textContent == null || textContent.isEmpty()) {
				result = false;
				System.out.println(String.format("\t warning: empty string for name=%s", parse.getName()));
			}
		}

		return result;
	}

	public boolean passCheckBefore2(File file) {

		boolean result = false;

		System.out.println("check: passCheckBefore2");
		List<String> regexNameList = mRegexParser.getNameList(file, this);
		List<String> domNameList = mDomParser.getNameList(file);

		int regexCount = regexNameList.size();
		int domCount = domNameList.size();

		List<String> copyRegexNameList = new ArrayList<String>();
		List<String> copyDomNameList = new ArrayList<String>();

		copyRegexNameList.addAll(regexNameList);
		copyRegexNameList.removeAll(domNameList);
		
		copyDomNameList.addAll(domNameList);
		copyDomNameList.removeAll(regexNameList);
		
		if (copyRegexNameList.size() != 0) {
			System.out.println(String.format("\t domCount = %s, regexCount = %s", domCount, regexCount));
		}
		for (String str : copyRegexNameList) {
			System.out.println(String.format("\t warning(regex): please check <string name=\"%s\">", str));
		}
		
		if (copyDomNameList.size() != 0) {
			System.out.println(String.format("\t domCount = %s, regexCount = %s", domCount, regexCount));
		}
		for (String str : copyDomNameList) {
			System.out.println(String.format("\t warning(dom): please check <string name=\"%s\">", str));
		}

		if (copyRegexNameList.size() == 0 && copyDomNameList.size() == 0) {
			result = true;
		}

		return result;
	}

	public boolean passCheck(String str) {
		return passCheck1(str) && passCheck2(str);
	}

	public boolean passCheck1(String str) {
		return !str.contains("CDATA");
	}

	public boolean passCheck2(String str) {
		return StringUtils.countMatches(str, "<string") == StringUtils.countMatches(str, "</string");
	}
}
