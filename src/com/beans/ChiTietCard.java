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


public class ChiTietCard {
	private int _idcard;
	private int _idproduct;
	private int _dongia;
	private int _soluong;
	private int _thanhtien;
	public ChiTietCard() {
		
	}
	public ChiTietCard( int idcard, int idproduct,int dongia ,int soluong, int thanhtien ) {
		_idcard=idcard;
		_idproduct=idproduct;
		_dongia=dongia;
		_soluong=soluong;
		_thanhtien=thanhtien;
		
	}
	public void setIdCard(int idcard) {
		_idcard=idcard;
	}
	public int getIdCard() {
		return _idcard;
	}
	public void setIdProduct(int idproduct) {
		_idproduct=idproduct;
	}
	public int getIdProduct() {
		return _idproduct;
	}
	public void setDongia(int dongia) {
		_dongia=dongia;
	}
	public int getDongia() {
		return _dongia;
	}
	public void setSoluong(int soluong) {
		_soluong=soluong;
	}
	public int getSoluong() {
		return _soluong;
	}
	
	public void setThanhTien(int thanhtien) {
		_thanhtien=thanhtien;
	}
	public int getThanhTien() {
		return _thanhtien;
	}
	
}
