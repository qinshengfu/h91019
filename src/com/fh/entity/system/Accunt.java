package com.fh.entity.system;

public class Accunt {
	private String SERIAL;              //会员编号';
	private double INVESTMENT;              //团队总排单';
	private double EARNINGS;              //直推排单';
	private double ETH;              //以太币';
	private double BTC;              //btc';
	private double BALIC;              //平台币';
	private double EVERYDAY;              //每日收益';
	private double TEAM_BENEFIT;       //团队收益';
	private double PERSONAL_BENEFITS;       //个人收益';
	private double LOCK_PRICE;       //锁仓数量';
	private String ACCUNT_ID;       //ID';
	private String SUPERIOR_F;  //所有上级
	private int DAILY_STATUS;//每日收益领取状态';
	private double MONTHLY;//静态收益';
	private int MONTHLY_STATUS;//静态收益领取状态';
	private String REGISTER_TIME;//注册时间
	
	
	public String getREGISTER_TIME() {
		return REGISTER_TIME;
	}
	public void setREGISTER_TIME(String rEGISTER_TIME) {
		REGISTER_TIME = rEGISTER_TIME;
	}
	public int getDAILY_STATUS() {
		return DAILY_STATUS;
	}
	public void setDAILY_STATUS(int dAILY_STATUS) {
		DAILY_STATUS = dAILY_STATUS;
	}
	public double getMONTHLY() {
		return MONTHLY;
	}
	public void setMONTHLY(double mONTHLY) {
		MONTHLY = mONTHLY;
	}
	public int getMONTHLY_STATUS() {
		return MONTHLY_STATUS;
	}
	public void setMONTHLY_STATUS(int mONTHLY_STATUS) {
		MONTHLY_STATUS = mONTHLY_STATUS;
	}
	public String getSERIAL() {
		return SERIAL;
	}
	public void setSERIAL(String sERIAL) {
		SERIAL = sERIAL;
	}
	public double getINVESTMENT() {
		return INVESTMENT;
	}
	public void setINVESTMENT(double iNVESTMENT) {
		INVESTMENT = iNVESTMENT;
	}
	public double getEARNINGS() {
		return EARNINGS;
	}
	public void setEARNINGS(double eARNINGS) {
		EARNINGS = eARNINGS;
	}
	public double getETH() {
		return ETH;
	}
	public void setETH(double eTH) {
		ETH = eTH;
	}
	public double getBTC() {
		return BTC;
	}
	public void setBTC(double bTC) {
		BTC = bTC;
	}
	public double getBALIC() {
		return BALIC;
	}
	public void setBALIC(double bALIC) {
		BALIC = bALIC;
	}
	public double getEVERYDAY() {
		return EVERYDAY;
	}
	public void setEVERYDAY(double eVERYDAY) {
		EVERYDAY = eVERYDAY;
	}
	public double getTEAM_BENEFIT() {
		return TEAM_BENEFIT;
	}
	public void setTEAM_BENEFIT(double tEAM_BENEFIT) {
		TEAM_BENEFIT = tEAM_BENEFIT;
	}
	public double getPERSONAL_BENEFITS() {
		return PERSONAL_BENEFITS;
	}
	public void setPERSONAL_BENEFITS(double pERSONAL_BENEFITS) {
		PERSONAL_BENEFITS = pERSONAL_BENEFITS;
	}
	public double getLOCK_PRICE() {
		return LOCK_PRICE;
	}
	public void setLOCK_PRICE(double lOCK_PRICE) {
		LOCK_PRICE = lOCK_PRICE;
	}
	public String getACCUNT_ID() {
		return ACCUNT_ID;
	}
	public void setACCUNT_ID(String aCCUNT_ID) {
		ACCUNT_ID = aCCUNT_ID;
	}
	public String getSUPERIOR_F() {
		return SUPERIOR_F;
	}
	public void setSUPERIOR_F(String sUPERIOR_F) {
		SUPERIOR_F = sUPERIOR_F;
	}
	@Override
	public String toString() {
		return "Accunt [SERIAL=" + SERIAL + ", INVESTMENT=" + INVESTMENT + ", EARNINGS=" + EARNINGS + ", ETH=" + ETH
				+ ", BTC=" + BTC + ", BALIC=" + BALIC + ", EVERYDAY=" + EVERYDAY + ", TEAM_BENEFIT=" + TEAM_BENEFIT
				+ ", PERSONAL_BENEFITS=" + PERSONAL_BENEFITS + ", LOCK_PRICE=" + LOCK_PRICE + ", ACCUNT_ID=" + ACCUNT_ID
				+ ", SUPERIOR_F=" + SUPERIOR_F + ", DAILY_STATUS=" + DAILY_STATUS + ", MONTHLY=" + MONTHLY
				+ ", MONTHLY_STATUS=" + MONTHLY_STATUS + ", REGISTER_TIME=" + REGISTER_TIME + "]";
	}
	
	

	
}
