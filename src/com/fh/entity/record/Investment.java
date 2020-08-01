package com.fh.entity.record;

public class Investment {
	private  String INVESTMENT_ID;// this.get32UUID());	//主键
	private  String SERIAL;// "");	//投资账户
	private  String ENTER_TIME;// "");	//转入时间
	private  String OUT_TIME;// "");	//转出时间
	private  String TOKEN_NAME;// "");	//投资币种
	private  double INVE_NUMBER;// "0");	//投资金额
	public String getINVESTMENT_ID() {
		return INVESTMENT_ID;
	}
	public void setINVESTMENT_ID(String iNVESTMENT_ID) {
		INVESTMENT_ID = iNVESTMENT_ID;
	}
	public String getSERIAL() {
		return SERIAL;
	}
	public void setSERIAL(String sERIAL) {
		SERIAL = sERIAL;
	}
	public String getENTER_TIME() {
		return ENTER_TIME;
	}
	public void setENTER_TIME(String eNTER_TIME) {
		ENTER_TIME = eNTER_TIME;
	}
	public String getOUT_TIME() {
		return OUT_TIME;
	}
	public void setOUT_TIME(String oUT_TIME) {
		OUT_TIME = oUT_TIME;
	}
	public String getTOKEN_NAME() {
		return TOKEN_NAME;
	}
	public void setTOKEN_NAME(String tOKEN_NAME) {
		TOKEN_NAME = tOKEN_NAME;
	}
	public double getINVE_NUMBER() {
		return INVE_NUMBER;
	}
	public void setINVE_NUMBER(double iNVE_NUMBER) {
		INVE_NUMBER = iNVE_NUMBER;
	}
	@Override
	public String toString() {
		return "Investment [INVESTMENT_ID=" + INVESTMENT_ID + ", SERIAL=" + SERIAL + ", ENTER_TIME=" + ENTER_TIME
				+ ", OUT_TIME=" + OUT_TIME + ", TOKEN_NAME=" + TOKEN_NAME + ", INVE_NUMBER=" + INVE_NUMBER + "]";
	}
	
	
	
}
