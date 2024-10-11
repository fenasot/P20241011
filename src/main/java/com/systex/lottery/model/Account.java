package com.systex.lottery.model;

import com.systex.lottery.exception.InputParameterException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

	/**
	 * 資料庫id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*
	 * account 帳號，長度限制20，不限制字元
	 */
	@Column(unique = true, nullable = false) 
	private String acc;
	
	/*
	 * password 密碼，長度限制20，不限制字元
	 */
	@Column(nullable = false)
	private String pwd;
	
	public String getAcc() {
		return acc;
	}
	
	public void setAcc(String acc) {
		this.acc = acc;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * 驗證帳號是否符合規格
	 * @param acc 帳號 長度限制 6~20，字元不限制
	 * @throws InputParameterException 自定義錯誤拋出
	 */
	public void checkAcc(String acc) throws InputParameterException {
		checkIsNullOrEmpty(acc);
		checkIsInRange(acc);
	}
	
	/**
	 * 驗證密碼是否符合規格
	 * @param pwd 密碼 長度限制 6~20，字元至少要有一個數字與英文字母
	 * @throws InputParameterException 自定義錯誤拋出
	 */
	public void checkPwd(String pwd) throws InputParameterException {
		checkIsNullOrEmpty(pwd);
		checkIsInRange(pwd);
		checkContainsLetterAndDigit(pwd);
	}
	
	/**
	 * 檢查是否為空或Null
	 * @param string 字串
	 * @throws InputParameterException 自定義錯誤拋出
	 */
	private void checkIsNullOrEmpty(String string) throws InputParameterException {
		if(string == null || string.equals("")) {
			throw new InputParameterException("帳密不得為空");
		}
	}
	
	/**
	 * 檢查是否在長度範圍內
	 * @param string 字串
	 * @throws InputParameterException 自定義錯誤拋出
	 */
	private void checkIsInRange(String string) throws InputParameterException {
		if(string.length() < 6 || string.length() > 20) {
			throw new InputParameterException("帳密長度必須為6~20個字元");
		}
	}
	
	/**
	 * 檢查是否包含至少一個數字和一個英文字母
	 * @param string 字串
	 * @throws InputParameterException 自定義錯誤拋出
	 */
	private void checkContainsLetterAndDigit(String string) throws InputParameterException {
	    if (!string.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
	        throw new InputParameterException("密碼必須包含至少一個數字和一個字母");
	    }
	}
	
	
	
}
