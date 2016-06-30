package com.robust;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomParser {
	
	public List<Result> getResultList(File file) {
		
		List<Result> result = new ArrayList<Result>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder buidler = null;
		Document document = null;
		NodeList resourcesList = null;
		Element root = null;
		try {
			buidler = factory.newDocumentBuilder();
			document = buidler.parse(file);
			root = document.getDocumentElement();
			resourcesList = root.getElementsByTagName("string");
			for (int i = 0; i < resourcesList.getLength(); i++) {
				Element element = (Element) resourcesList.item(i);
				String nameValue = element.getAttribute("name");
				String textContent = element.getTextContent();
				result.add(new Result(nameValue, textContent));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
public List<String> getNameList(File file) {
		
		List<String> result = new ArrayList<String>();
		List<Result> resultList = getResultList(file);
		for (Result r : resultList) {
			result.add(r.getName());
		}
		
		return result;
	}
}
