package com.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.Product;
import com.beans.User;


public class Card {
	private int _idcard;
	private int _iduser;
	private String _date;
	private int _thanhtien;
	private int _thanhtoan;
	public Card() {
		
	}
	public Card( int idcard, int iduser, String date, int thanhtien, int thanhtoan ) {
		_idcard=idcard;
		_iduser=iduser;
		_date=date;
		_thanhtien=thanhtien;
		_thanhtoan=thanhtoan;
	}
	public void setIdCard(int idcard) {
		_idcard=idcard;
	}
	public int getIdCard() {
		return _idcard;
	}
	public void setIdUser(int iduser) {
		_iduser=iduser;
	}
	public int getIdUser() {
		return _iduser;
	}
	public void setDate(String date) {
		_date=date;
	}
	public String getDate() {
		return _date;
	}
	public void setThanhTien(int thanhtien) {
		_thanhtien=thanhtien;
	}
	public int getThanhTien() {
		return _thanhtien;
	}
	public void setThanhToan(int thanhtoan) {
		_thanhtoan=thanhtoan;
	}
	public int getThanhToan() {
		return _thanhtoan;
	}
}
