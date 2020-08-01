package com.fh.entity.system;

public class MemUser {

	 private String PHONE;//手机号';
	 private String LOGIN_PASSWORD;//登录密码';
	 private String TRADE_PASSWORD;//二级密码';
	 private int STATUS;//账号状态';
	 private String REGISTER_TIME;//注册时间';
	 private String SUPERIOR;//上级';
	 private int LEVEL_P;//会员级别';
	 private String MNEMONIC;//助记词';
	 private String MNAME;//姓名';
	 private String ID_CARD;//身份证号';
	 private String SERIAL;//会员编号';
	 private String ETH_PRIVATE_KEY;//eth私钥';
	 private String POSITIVE;//身份证正面照';
	 private String OTHER_SIDE;//身份证反面照';
	 private String HANDHELD;//手持身份证照片';
	 private int DIRECT_PUSH;//直推人数';
	 private int ACTIVATION_STATE;//激活状态';
	 private int ALGEBRA;//代数';
	 private String REMARKS;//备注';
	 private String ETH;//eth钱包地址';
	 private String MEMBERS_ID;//ID';
	 private String BTC;//btc钱包地址';
	 private String SUPERIOR_F;//所有上级
	 private int IDENTITY;//身份证验证状态
	 private String JH_TIME;//激活时间
	 private String YHMC;//用户名称
	 private String PORTRAIT;//头像
	 
	 
	 
	public String getPORTRAIT() {
		return PORTRAIT;
	}
	public void setPORTRAIT(String pORTRAIT) {
		PORTRAIT = pORTRAIT;
	}
	public String getYHMC() {
		return YHMC;
	}
	public void setYHMC(String yHMC) {
		YHMC = yHMC;
	}
	public String getJH_TIME() {
		return JH_TIME;
	}
	public void setJH_TIME(String jH_TIME) {
		JH_TIME = jH_TIME;
	}
	public String getSUPERIOR_F() {
		return SUPERIOR_F;
	}
	public void setSUPERIOR_F(String sUPERIOR_F) {
		SUPERIOR_F = sUPERIOR_F;
	}
	public int getIDENTITY() {
		return IDENTITY;
	}
	public void setIDENTITY(int iDENTITY) {
		IDENTITY = iDENTITY;
	}
	public String getBTC() {
		return BTC;
	}
	public void setBTC(String bTC) {
		BTC = bTC;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getLOGIN_PASSWORD() {
		return LOGIN_PASSWORD;
	}
	public void setLOGIN_PASSWORD(String lOGIN_PASSWORD) {
		LOGIN_PASSWORD = lOGIN_PASSWORD;
	}
	public String getTRADE_PASSWORD() {
		return TRADE_PASSWORD;
	}
	public void setTRADE_PASSWORD(String tRADE_PASSWORD) {
		TRADE_PASSWORD = tRADE_PASSWORD;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}
	public String getREGISTER_TIME() {
		return REGISTER_TIME;
	}
	public void setREGISTER_TIME(String rEGISTER_TIME) {
		REGISTER_TIME = rEGISTER_TIME;
	}
	public String getSUPERIOR() {
		return SUPERIOR;
	}
	public void setSUPERIOR(String sUPERIOR) {
		SUPERIOR = sUPERIOR;
	}
	public int getLEVEL_P() {
		return LEVEL_P;
	}
	public void setLEVEL_P(int lEVEL_P) {
		LEVEL_P = lEVEL_P;
	}
	public String getMNEMONIC() {
		return MNEMONIC;
	}
	public void setMNEMONIC(String mNEMONIC) {
		MNEMONIC = mNEMONIC;
	}
	public String getMNAME() {
		return MNAME;
	}
	public void setMNAME(String mNAME) {
		MNAME = mNAME;
	}
	public String getID_CARD() {
		return ID_CARD;
	}
	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}
	public String getSERIAL() {
		return SERIAL;
	}
	public void setSERIAL(String sERIAL) {
		SERIAL = sERIAL;
	}
	public String getETH_PRIVATE_KEY() {
		return ETH_PRIVATE_KEY;
	}
	public void setETH_PRIVATE_KEY(String eTH_PRIVATE_KEY) {
		ETH_PRIVATE_KEY = eTH_PRIVATE_KEY;
	}
	public String getPOSITIVE() {
		return POSITIVE;
	}
	public void setPOSITIVE(String pOSITIVE) {
		POSITIVE = pOSITIVE;
	}
	public String getOTHER_SIDE() {
		return OTHER_SIDE;
	}
	public void setOTHER_SIDE(String oTHER_SIDE) {
		OTHER_SIDE = oTHER_SIDE;
	}
	public String getHANDHELD() {
		return HANDHELD;
	}
	public void setHANDHELD(String hANDHELD) {
		HANDHELD = hANDHELD;
	}
	public int getDIRECT_PUSH() {
		return DIRECT_PUSH;
	}
	public void setDIRECT_PUSH(int dIRECT_PUSH) {
		DIRECT_PUSH = dIRECT_PUSH;
	}
	public int getACTIVATION_STATE() {
		return ACTIVATION_STATE;
	}
	public void setACTIVATION_STATE(int aCTIVATION_STATE) {
		ACTIVATION_STATE = aCTIVATION_STATE;
	}
	public int getALGEBRA() {
		return ALGEBRA;
	}
	public void setALGEBRA(int aLGEBRA) {
		ALGEBRA = aLGEBRA;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getETH() {
		return ETH;
	}
	public void setETH(String eTH) {
		ETH = eTH;
	}
	public String getMEMBERS_ID() {
		return MEMBERS_ID;
	}
	public void setMEMBERS_ID(String mEMBERS_ID) {
		MEMBERS_ID = mEMBERS_ID;
	}
	@Override
	public String toString() {
		return "MemUser [PHONE=" + PHONE + ", LOGIN_PASSWORD=" + LOGIN_PASSWORD + ", TRADE_PASSWORD=" + TRADE_PASSWORD
				+ ", STATUS=" + STATUS + ", REGISTER_TIME=" + REGISTER_TIME + ", SUPERIOR=" + SUPERIOR + ", LEVEL_P="
				+ LEVEL_P + ", MNEMONIC=" + MNEMONIC + ", MNAME=" + MNAME + ", ID_CARD=" + ID_CARD + ", SERIAL="
				+ SERIAL + ", ETH_PRIVATE_KEY=" + ETH_PRIVATE_KEY + ", POSITIVE=" + POSITIVE + ", OTHER_SIDE="
				+ OTHER_SIDE + ", HANDHELD=" + HANDHELD + ", DIRECT_PUSH=" + DIRECT_PUSH + ", ACTIVATION_STATE="
				+ ACTIVATION_STATE + ", ALGEBRA=" + ALGEBRA + ", REMARKS=" + REMARKS + ", ETH=" + ETH + ", MEMBERS_ID="
				+ MEMBERS_ID + ", BTC=" + BTC + ", SUPERIOR_F=" + SUPERIOR_F + ", IDENTITY=" + IDENTITY + ", JH_TIME="
				+ JH_TIME + ", YHMC=" + YHMC + ", PORTRAIT=" + PORTRAIT + "]";
	}
	 	
}
