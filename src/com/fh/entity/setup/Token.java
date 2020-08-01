package com.fh.entity.setup;

public class Token {
	private String TOKEN_NAME;            //代币名称';
	private double LAST;            //最新价';
	private double HIGHESTBID;            //买方最高价';
	private double HIGH24HR;            //24小时最高价';
	private double LOW24HR;            //24小时最低价';
	private double LOWESTASK;            //卖方最低价';
	private double PERCENTCHANGE;            //涨跌百分比';
	private double QUOTEVOLUME;            //兑换货币交易量';
	private String TOKEN_ID;            //ID';
	private String ICON;// 图标
	
	
	public String getICON() {
		return ICON;
	}
	public void setICON(String iCON) {
		ICON = iCON;
	}
	public String getTOKEN_NAME() {
		return TOKEN_NAME;
	}
	public void setTOKEN_NAME(String tOKEN_NAME) {
		TOKEN_NAME = tOKEN_NAME;
	}
	public double getLAST() {
		return LAST;
	}
	public void setLAST(double lAST) {
		LAST = lAST;
	}
	public double getHIGHESTBID() {
		return HIGHESTBID;
	}
	public void setHIGHESTBID(double hIGHESTBID) {
		HIGHESTBID = hIGHESTBID;
	}
	public double getHIGH24HR() {
		return HIGH24HR;
	}
	public void setHIGH24HR(double hIGH24HR) {
		HIGH24HR = hIGH24HR;
	}
	public double getLOW24HR() {
		return LOW24HR;
	}
	public void setLOW24HR(double lOW24HR) {
		LOW24HR = lOW24HR;
	}
	public double getLOWESTASK() {
		return LOWESTASK;
	}
	public void setLOWESTASK(double lOWESTASK) {
		LOWESTASK = lOWESTASK;
	}
	public double getPERCENTCHANGE() {
		return PERCENTCHANGE;
	}
	public void setPERCENTCHANGE(double pERCENTCHANGE) {
		PERCENTCHANGE = pERCENTCHANGE;
	}
	public double getQUOTEVOLUME() {
		return QUOTEVOLUME;
	}
	public void setQUOTEVOLUME(double qUOTEVOLUME) {
		QUOTEVOLUME = qUOTEVOLUME;
	}
	public String getTOKEN_ID() {
		return TOKEN_ID;
	}
	public void setTOKEN_ID(String tOKEN_ID) {
		TOKEN_ID = tOKEN_ID;
	}
	@Override
	public String toString() {
		return "Token [TOKEN_NAME=" + TOKEN_NAME + ", LAST=" + LAST + ", HIGHESTBID=" + HIGHESTBID + ", HIGH24HR="
				+ HIGH24HR + ", LOW24HR=" + LOW24HR + ", LOWESTASK=" + LOWESTASK + ", PERCENTCHANGE=" + PERCENTCHANGE
				+ ", QUOTEVOLUME=" + QUOTEVOLUME + ", TOKEN_ID=" + TOKEN_ID + ", ICON=" + ICON + "]";
	}
	
	

}
