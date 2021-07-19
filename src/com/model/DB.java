package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.beans.Card;
import com.beans.ChiTietCard;
import com.beans.Product;
import com.beans.User;


public class DB {
	
	private String username = "root";
	private String password = "12345";
	private String dbName = "myproject";
	private String url = "jdbc:mysql://localhost:3306/" + dbName;
	private String driver = "com.mysql.jdbc.Driver";
	
	ArrayList<Product> list = new ArrayList<>();
	ArrayList<User> userList = new ArrayList<>();
	
	private Connection con;
	private void dbConnect() {
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public int getID(String username)throws SQLException 
	{
		int id=0;
		dbConnect();
		String sql = "Select id from myproject.user where username=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,username);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			id=rs.getInt("id");
		}
		return id;
	}
	public void addUser(User user) throws SQLException {
		dbConnect();
		String sql = "Insert into user(name,email,username,address,password) values(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, user.getName());
		st.setString(2, user.getEmail());
		st.setString(3, user.getUsername());
		st.setString(4, user.getAddress());
		st.setString(5, user.getPassword());
		
		
		st.executeUpdate();
		dbClose();
	}
	
	public User GetUser(int id)throws SQLException{
		dbConnect();
		User user= new User();
		String sql = "Select * from user where id = ? ";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			user.setName(rs.getString("name"));
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setUsername(rs.getString("username"));
		}
		return user;
	}
	public Card GetCard(int idcard)throws SQLException{
		dbConnect();
		Card card = new Card();
		String sql = "Select * from Card where idCard = ? ";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setInt(1, idcard);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			
			card.setIdCard(rs.getInt("idCard"));
			card.setIdUser(rs.getInt("idUser"));
			card.setDate(rs.getString("Date"));
			card.setThanhTien(rs.getInt("ThanhTien"));
			card.setThanhToan(rs.getInt("Thanhtoan"));
			
		}
		return card;
	}
	public ArrayList<ChiTietCard> GetChiTietCard()throws SQLException{
		dbConnect();
		
		String sql = "Select * from chitietcard";
		PreparedStatement st = con.prepareStatement(sql);		
		ArrayList<ChiTietCard> arr= new ArrayList<>();
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			ChiTietCard card = new ChiTietCard();
			card.setIdCard(rs.getInt("idCard"));
			card.setIdProduct(rs.getInt("idProduct"));
			card.setDongia(rs.getInt("Dongia"));
			card.setSoluong(rs.getInt("Soluong"));
			card.setThanhTien(rs.getInt("ThanhTien"));
			arr.add(card);
			card=null;
		}
		return arr;
	}
	public boolean checkUser(String username, String password) throws SQLException {
		dbConnect();
		int count = 0;
		String sql = "Select * from user where username = ? and password = ?";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setString(1, username);
		st.setString(2, password);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			count = 1;
		}
		
		dbClose();
		if(count == 0)
			return false;
		
		return true;
	}

	public ArrayList<Product> fetch()  throws SQLException {
		dbConnect();
		String sql = "Select * from myproject.product";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name= rs.getString("name");
			String category= rs.getString("category");
			String price= rs.getString("price");
			String featured= rs.getString("featured");
			String image= rs.getString("image");
			
			Product p = new Product();
			p.setCategory(category);
			p.setFeatured(featured);
			p.setId(id);
			p.setImage(image);
			p.setName(name);
			p.setPrice(price);
			list.add(p);
			p=null;
			
		}
		
		dbClose();
		return list;
	}

	public ArrayList<User> fetchUser() throws SQLException {
		dbConnect();
		String sql = "Select * from user";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			String address = rs.getString("address");
			String user = rs.getString("username");
			String email = rs.getString("email");
			String name = rs.getString("name");
			int id = rs.getInt("id");
			String password = rs.getString("password");
			
			User u = new User();
			u.setAddress(address);
			u.setEmail(email);
			u.setId(id);
			u.setName(name);
			u.setUsername(user);
			u.setPassword(password);
			userList.add(u);
			u=null;
				
		}
		
		dbClose();
		return userList;
	}

	public void deleteProduct(String id) throws SQLException {
		
		dbConnect();
		String sql = "Delete from product where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.executeUpdate();
		dbClose();
		
	}

	public Product fetchProduct(String id) throws SQLException {
		dbConnect();
		String sql = "select * from product where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rst = pstmt.executeQuery();
		Product p = new Product();
		while(rst.next()){
			
			p.setId(rst.getInt("id"));
			p.setName(rst.getString("name"));
			p.setPrice(rst.getString("price"));
			p.setCategory(rst.getString("category"));
			p.setFeatured(rst.getString("featured"));
			p.setImage(rst.getString("image"));
		}
		dbClose();
		return p;
	}
	
	
	
	public void updateProduct(Product p) throws SQLException {
		dbConnect();
		String sql = "update product set name=?,price=?,category=?,featured=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, p.getName());
		st.setString(2, p.getPrice());
		st.setString(3, p.getCategory());
		st.setString(4, p.getFeatured());
		st.setInt(5, p.getId());
		st.executeUpdate();
		dbClose();
	}
	public void updateAcc(User u) throws SQLException {
		dbConnect();
		String sql = "update myproject.user set password=? where username=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, u.getPassword());
		st.setString(2, u.getUsername());
		
		st.executeUpdate();
		dbClose();
	}
	
	public void updateMyAccout(User u) throws SQLException {
		dbConnect();
		String sql = "update myproject.user set name=?,address=?,email=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, u.getName());
		st.setString(2, u.getAddress());
		st.setString(3, u.getEmail());		
		st.setInt(4, u.getId());
		st.executeUpdate();
		dbClose();
	}
	public void addProduct(Product p) throws SQLException {
		dbConnect();
		String sql = "Insert into product(name,price,category,featured,image) values(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, p.getName());
		st.setString(2, p.getPrice());
		st.setString(3, p.getCategory());
		st.setString(4, p.getFeatured());
		st.setString(5, p.getImage());
		st.executeUpdate();
		dbClose();
	}
	public int CountCart() throws SQLException {
		int count=0;
		dbConnect();
		String sql="select count(idCard) as countID from myproject.card";		
		PreparedStatement st = con.prepareStatement(sql);		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			count=rs.getInt("countID");
		}
		return count;
	}
	public void addCard(int idcart ,int iduser,String date, int thanhtien, int thanhtoan) throws SQLException {
		dbConnect();
		String sql = "Insert into card(idCard,idUser,Date,ThanhTien,ThanhToan) values(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);	
		st.setInt(1, idcart);
		st.setInt(2, iduser);
		st.setString(3, date);
		st.setInt(4, thanhtien);
		st.setInt(5, thanhtoan);		
		st.executeUpdate();
		dbClose();
	}
	public void addChitietCard(int idcard, int idproduct, int dongia, int soluong, int thanhtien) throws SQLException {
		dbConnect();
		String sql = "Insert into chitietcard(idCard,idProduct,dongia,soluong,thanhtien) values(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setInt(1, idcard);
		st.setInt(2, idproduct);
		st.setInt(3, dongia);
		st.setInt(4, soluong);		
		st.setInt(5, thanhtien);
		st.executeUpdate();
		dbClose();
	}
	public ArrayList<Product> ListSearch (String key) throws SQLException{
		dbConnect();
		String sql="select * from myproject.product where name like '%"+key+"%'";		
		PreparedStatement st = con.prepareStatement(sql);		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name= rs.getString("name");
			String category= rs.getString("category");
			String price= rs.getString("price");
			String featured= rs.getString("featured");
			String image= rs.getString("image");
			
			Product p = new Product();
			p.setCategory(category);
			p.setFeatured(featured);
			p.setId(id);
			p.setImage(image);
			p.setName(name);
			p.setPrice(price);
			list.add(p);
			p=null;
			
		}
		
		dbClose();
		return list;
	}
	public ArrayList<String> ListIDCard(int iduser) throws SQLException{
		ArrayList<String> newList = new ArrayList<>();
		dbConnect();
		String sql="select * from myproject.card where iduser=?"	;	
		PreparedStatement st = con.prepareStatement(sql);	
		st.setInt(1, iduser);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			newList.add(rs.getString("idCard"));
		}
		return newList;
	}
	public ArrayList<String> ListIDChiTiet() throws SQLException{
		ArrayList<String> newList = new ArrayList<>();
		dbConnect();
		String sql="select * from myproject.chitietcard"	;	
		PreparedStatement st = con.prepareStatement(sql);	
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			newList.add(rs.getString("idProduct"));
		}
		return newList;
	}
	public ArrayList<Product> ListSearchPage (String key, String page) throws SQLException{
		dbConnect();
		String sql="select * from myproject.product where name like '%"+key+"%' and category=?";		
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, page);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name= rs.getString("name");
			String category= rs.getString("category");
			String price= rs.getString("price");
			String featured= rs.getString("featured");
			String image= rs.getString("image");
			
			Product p = new Product();
			p.setCategory(category);
			p.setFeatured(featured);
			p.setId(id);
			p.setImage(image);
			p.setName(name);
			p.setPrice(price);
			list.add(p);
			p=null;
			
		}
		
		dbClose();
		return list;
	}

	public int CountAll() throws SQLException{
		int count=0;
		dbConnect();
		String sql="select count(*) as countID from myproject.product";		
		PreparedStatement st = con.prepareStatement(sql);		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			count=rs.getInt("countID");
		}
		return count;
	}
	public int CountItemCategory(String category) throws SQLException{
		int count=0;
		dbConnect();
		String sql="select count(*) as countID from myproject.product where category = ?";		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, category);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			count=rs.getInt("countID");
		}
		return count;
	}
	public ArrayList<Product> fetchItemCategory(String categori, int begin, int size)  throws SQLException {
		int end = 0;
		end =begin + size -1;
		dbConnect();
		String sql = "select * from (select ROW_NUMBER() over(order by id) as stt, id, name, price, category, featured, image from myproject.product where category = ?) as b where stt between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, categori);
		st.setInt(2, begin);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name= rs.getString("name");
			String category= rs.getString("category");
			String price= rs.getString("price");
			String featured= rs.getString("featured");
			String image= rs.getString("image");
			
			Product p = new Product();
			p.setCategory(category);
			p.setFeatured(featured);
			p.setId(id);
			p.setImage(image);
			p.setName(name);
			p.setPrice(price);
			list.add(p);
			p=null;
			
		}
		
		dbClose();
		return list;
	}
	public ArrayList<Product> fetchItemAll(int begin, int size)  throws SQLException {
		int end = 0;
		end =begin + size -1;
		dbConnect();
		String sql = "select * from (select ROW_NUMBER() over(order by id) as stt, id, name, price, category, featured, image from myproject.product) as b where stt between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, begin);
		st.setInt(2, end);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name= rs.getString("name");
			String category= rs.getString("category");
			String price= rs.getString("price");
			String featured= rs.getString("featured");
			String image= rs.getString("image");
			
			Product p = new Product();
			p.setCategory(category);
			p.setFeatured(featured);
			p.setId(id);
			p.setImage(image);
			p.setName(name);
			p.setPrice(price);
			list.add(p);
			p=null;
			
		}
		
		dbClose();
		return list;
	}

	public ChiTietCard GetChiTietCardCustom(String id)throws SQLException{
		dbConnect();
		
		String sql = "Select * from chitietcard where idCard =?";
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, id);
		ChiTietCard card = new ChiTietCard();
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			card.setIdCard(rs.getInt("idCard"));
			card.setIdProduct(rs.getInt("idProduct"));
			card.setDongia(rs.getInt("Dongia"));
			card.setSoluong(rs.getInt("Soluong"));
			card.setThanhTien(rs.getInt("ThanhTien"));
		}
		return card;
	}
}
