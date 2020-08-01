package com.fh.entity.system;

public class Secretkey {

	
	private String FILE_CONTENT;//秘钥文件内容';
	private String SECRET_PASSWORD;//秘钥密码';
	private String PRIVATEKEY;//私钥';
	private String PUBLICKEY;//公钥';
	private String SECRET_FILE_NAME;//秘钥文件名';
	private String SERIAL;//会员编号';
	private String CURRENCY;//币种';
	private String TRADE_TIME;//最后交易时间';
	private String SECRETKEY_ID;//ID';
	private String ADDRESS;//钱包地址
	private double BALANCE;//钱包余额
	private String HASH;//交易哈希
	
	
	public String getHASH() {
		return HASH;
	}
	public void setHASH(String hASH) {
		HASH = hASH;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public double getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(double bALANCE) {
		BALANCE = bALANCE;
	}
	public String getFILE_CONTENT() {
		return FILE_CONTENT;
	}
	public void setFILE_CONTENT(String fILE_CONTENT) {
		FILE_CONTENT = fILE_CONTENT;
	}
	public String getSECRET_PASSWORD() {
		return SECRET_PASSWORD;
	}
	public void setSECRET_PASSWORD(String sECRET_PASSWORD) {
		SECRET_PASSWORD = sECRET_PASSWORD;
	}
	public String getPRIVATEKEY() {
		return PRIVATEKEY;
	}
	public void setPRIVATEKEY(String pRIVATEKEY) {
		PRIVATEKEY = pRIVATEKEY;
	}
	public String getPUBLICKEY() {
		return PUBLICKEY;
	}
	public void setPUBLICKEY(String pUBLICKEY) {
		PUBLICKEY = pUBLICKEY;
	}
	public String getSECRET_FILE_NAME() {
		return SECRET_FILE_NAME;
	}
	public void setSECRET_FILE_NAME(String sECRET_FILE_NAME) {
		SECRET_FILE_NAME = sECRET_FILE_NAME;
	}
	public String getSERIAL() {
		return SERIAL;
	}
	public void setSERIAL(String sERIAL) {
		SERIAL = sERIAL;
	}
	public String getCURRENCY() {
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}
	public String getTRADE_TIME() {
		return TRADE_TIME;
	}
	public void setTRADE_TIME(String tRADE_TIME) {
		TRADE_TIME = tRADE_TIME;
	}
	public String getSECRETKEY_ID() {
		return SECRETKEY_ID;
	}
	public void setSECRETKEY_ID(String sECRETKEY_ID) {
		SECRETKEY_ID = sECRETKEY_ID;
	}
	@Override
	public String toString() {
		return "Secretkey [FILE_CONTENT=" + FILE_CONTENT + ", SECRET_PASSWORD=" + SECRET_PASSWORD + ", PRIVATEKEY="
				+ PRIVATEKEY + ", PUBLICKEY=" + PUBLICKEY + ", SECRET_FILE_NAME=" + SECRET_FILE_NAME + ", SERIAL="
				+ SERIAL + ", CURRENCY=" + CURRENCY + ", TRADE_TIME=" + TRADE_TIME + ", SECRETKEY_ID=" + SECRETKEY_ID
				+ ", ADDRESS=" + ADDRESS + ", BALANCE=" + BALANCE + ", HASH=" + HASH + "]";
	}
	
	
	
	
	
}
