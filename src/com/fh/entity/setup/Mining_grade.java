package com.fh.entity.setup;

public class Mining_grade {
	private double GRADE_PRICE;           //价格';
	private String GRADE_LEVEL;           //级别名称';
	private String TOKEN_NAME;           //代币名称';
	private double GRADE_RELEASE;           //每日释放';
	private String MINING_GRADE_ID;           //ID';
	public double getGRADE_PRICE() {
		return GRADE_PRICE;
	}
	public void setGRADE_PRICE(double gRADE_PRICE) {
		GRADE_PRICE = gRADE_PRICE;
	}
	public String getGRADE_LEVEL() {
		return GRADE_LEVEL;
	}
	public void setGRADE_LEVEL(String gRADE_LEVEL) {
		GRADE_LEVEL = gRADE_LEVEL;
	}
	public String getTOKEN_NAME() {
		return TOKEN_NAME;
	}
	public void setTOKEN_NAME(String tOKEN_NAME) {
		TOKEN_NAME = tOKEN_NAME;
	}
	public double getGRADE_RELEASE() {
		return GRADE_RELEASE;
	}
	public void setGRADE_RELEASE(double gRADE_RELEASE) {
		GRADE_RELEASE = gRADE_RELEASE;
	}
	public String getMINING_GRADE_ID() {
		return MINING_GRADE_ID;
	}
	public void setMINING_GRADE_ID(String mINING_GRADE_ID) {
		MINING_GRADE_ID = mINING_GRADE_ID;
	}
	@Override
	public String toString() {
		return "Mining_grade [GRADE_PRICE=" + GRADE_PRICE + ", GRADE_LEVEL=" + GRADE_LEVEL + ", TOKEN_NAME="
				+ TOKEN_NAME + ", GRADE_RELEASE=" + GRADE_RELEASE + ", MINING_GRADE_ID=" + MINING_GRADE_ID + "]";
	}

	
	
	
	

}
