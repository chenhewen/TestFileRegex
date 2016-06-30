package com.robust;

public class Result {
	private String mName;
	
	private String mTextContent;

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getTextContent() {
		return mTextContent;
	}

	public void setTextContent(String mTextContent) {
		this.mTextContent = mTextContent;
	}

	public Result(String mName, String mTextContent) {
		this.mName = mName;
		this.mTextContent = mTextContent;
	}
}