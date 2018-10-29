package com.projectpintas;

public class Result {
	private int linkid;
	private String result1;
	private String result2;
	
	public Result(){}
	 
	public Result(int linkid, String result1, String result2) {
		super();
		this.linkid = linkid;
		this.result1 = result1;
		this.result2 = result2;
	}
	
	//Get and set methods
	
	public void setLinkId(int linkid0) {
		this.linkid = linkid0;
	}
		
	public int getLinkId() {
		return this.linkid;
	}
	
	public void setResult1(String result10) {
		this.result1 = result10;		
	}
		
	public String getResult1() {
		return this.result1;
	}
	
	public void setResult2(String result20) {
		this.result2 = result20;		
	}
		
	public String getResult2() {
		return this.result2;
	}
	
	@Override
	public String toString() {
		return "Link Id: " + linkid + "\t" + result1 + "\t" + result2;
	}
}
