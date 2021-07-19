package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.beans.Card;
import com.beans.ChiTietCard;
import com.beans.Product;
import com.beans.User;
import com.model.DB;

public class Controller extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	ArrayList<Product> list = new ArrayList<>();
	static ArrayList<ChiTietCard> cartlist = new ArrayList<>();
	ArrayList<Integer> SoluongPro= new ArrayList<Integer>();
	ArrayList<User> userList = new ArrayList<>();
	ChiTietCard ctcart = new ChiTietCard();
	ArrayList<ChiTietCard> listCT= new ArrayList<>();
	int soluong = 1;
	HttpSession session;
	private String Now_user="";
	private String Now_page="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		Now_page="all-products";
		if(page == null || page.equals("index")) {
			String indexString = request.getParameter("index");
			if(indexString == null)
				indexString = "1";
			int index= Integer.parseInt(indexString);
			int count=0;
			int endPage=0;
			DB db = new DB();
			 try {
				 list = db.fetchItemAll(index*10-9,10);
					count = db.CountAll();
					int pageSize = 10;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 session = request.getSession();
			 session.setAttribute("cartlist", cartlist);
			 session.setAttribute("list", list);		
			 session.setAttribute("endPage", endPage);			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			doPost(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		
		if(page.equals("login")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		if(page.equals("sign-up")) {
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		
		if(page.equals("sign-up-form")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String address = request.getParameter("address");
			String password_1 = request.getParameter("password_1");
			String password_2 = request.getParameter("password_2");
			
			if(password_1.equals(password_2)) {
				
				User user = new User();
				user.setAddress(address);
				user.setEmail(email);
				user.setName(name);
				user.setUsername(username);
				user.setPassword(password_1);
				
				DB db = new DB();
				try {
					db.addUser(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				request.setAttribute("username", username);
				request.setAttribute("msg", "Account created successfully, Please Login!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}else {
				request.setAttribute("msg", "The two passwords do not match");
				request.setAttribute("name", name);
				request.setAttribute("address", address);
				request.setAttribute("email", email);
				request.setAttribute("username", username);
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			
		}
		if(page.equals("edit_Infor")) {
			DB db= new DB();
			User user = new User();
			String username = Now_user;
			String password_1 = request.getParameter("password_1");
			String password_2 = request.getParameter("password_2");
			if(password_1.equals(password_2)) {
				user.setUsername(username);
				user.setPassword(password_1);
				try {
					db.updateAcc(user);
					request.setAttribute(username, user.getUsername());
					request.setAttribute("check", true);
					request.setAttribute("msg", "Edit successfully!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute(username, user.getUsername());
					request.setAttribute("check", true);
					request.setAttribute("msg", "Edit not successfully!");
					request.getRequestDispatcher("EditAcc.jsp").forward(request, response);
				}
				
			}
		}
		if(page.equals("Check_Infor")) {
			String username = Now_user;
			String password = request.getParameter("password");			
			User user = new User();			
			user.setUsername(username);
			user.setPassword(password);
			DB db = new DB();
			try {
				if(db.checkUser(username, password)) {
					
					request.setAttribute(username, user.getUsername());
					request.setAttribute("check", true);
					request.getRequestDispatcher("EditAcc.jsp").forward(request, response);
					request.setAttribute("msg", "Check successfully!");
					
				}
				else {
					request.setAttribute(username, user.getUsername());
					request.setAttribute("check", false);
					request.getRequestDispatcher("EditAcc.jsp").forward(request, response);
					request.setAttribute("msg", "Check not successfully!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				
			
			
		}
		
		
		if(page.equals("edit-forms")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String username = Now_user;
			String address = request.getParameter("address");
			User user = new User();
			user.setAddress(address);
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			
			DB db = new DB();
			int id=0;
			try {
				id = db.getID(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			user.setId(id);
			
			try {
				db.updateMyAccout(user);
				request.setAttribute("msg", "Edit successfully!");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", "Edit not successfully!");
			}
			try {
				user=db.GetUser(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("name",user.getName());
			request.setAttribute("email",user.getEmail());
			request.setAttribute("address",user.getAddress());
			request.setAttribute(username, user.getUsername());
			request.getRequestDispatcher("MyAccount.jsp").forward(request, response);
				
			
			
		}
		
		if(page.equals("login-form")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Now_user=username;
			DB db = new DB();
			User user = new User();
			boolean status = false;
			try {
				status = db.checkUser(username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status) {
				session = request.getSession();
				session.setAttribute("session", session);
				
				try {
					userList = db.fetchUser();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("address", user.fetchadd(userList,username));
				session.setAttribute("email", user.fetchemail(userList,username));
				session.setAttribute("name", user.fetchname(userList,username));
				session.setAttribute("username", username);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Invalid Crediantials");
				request.setAttribute("username", username);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		
		if(page.equals("logout")) {
			session = request.getSession();
			session.invalidate();
			
			 session = request.getSession();
			 cartlist.clear();
			 session.setAttribute("cartlist", cartlist);
			 session.setAttribute("list", list);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		if(page.equals("NuocGiaiKhat") || page.equals("TraSua") || page.equals("NuocEp") || page.equals("SinhTo") ||page.equals("AnVat") || page.equals("all-products")) {
			String indexString = request.getParameter("index");
			if(indexString == null)
				indexString = "1";
			int index= Integer.parseInt(indexString);
			
			
			if(page.equals("NuocGiaiKhat")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemCategory("NuocGiaiKhat",index*6-5,6);
					count = db.CountItemCategory("NuocGiaiKhat");
					int pageSize = 6;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("NuocGiaiKhat.jsp").forward(request, response);
			}
				
			if(page.equals("TraSua")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemCategory("TraSua",index*6-5,6);
					count = db.CountItemCategory("TraSua");
					int pageSize = 6;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("TraSua.jsp").forward(request, response);
			}
				
			if(page.equals("NuocEp")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemCategory("NuocEp",index*6-5,6);
					count = db.CountItemCategory("NuocEp");
					int pageSize = 6;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("NuocEp.jsp").forward(request, response);
			}
				
			if(page.equals("SinhTo")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemCategory("SinhTo",index*6-5,6);
					count = db.CountItemCategory("SinhTo");
					int pageSize = 6;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("SinhTo.jsp").forward(request, response);
			}
				
			if(page.equals("AnVat")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemCategory("AnVat",index*6-5,6);
					count = db.CountItemCategory("AnVat");
					int pageSize = 6;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("AnVat.jsp").forward(request, response);
			}
				
			if(page.equals("all-products")) {
				Now_page=page;
				int count=0;
				int endPage=0;
				DB db = new DB();
				 try {
					list = db.fetchItemAll(index*10-9,10);
					count = db.CountAll();
					int pageSize = 10;
					endPage = count / pageSize;
					if(count % pageSize !=0)
						endPage = endPage +1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 session = request.getSession();
				 session.setAttribute("list", list);		
				 session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("all-products.jsp").forward(request, response);
			}
				
		}
		
		
		
		if(page.equals("showcart")) {
			DB db= new DB();
			ArrayList<String> ListIDCard= new ArrayList<>();
			
			ArrayList<ChiTietCard> ctcartList= new ArrayList<>();
			ArrayList<Card> CartList = new ArrayList<>();
			int[] idcard= new int[100];
			try {
				ListIDCard=db.ListIDCard(db.getID(Now_user));				
				for(int i=0;i<ListIDCard.size();i++) {
					idcard[i]=Integer.parseInt(ListIDCard.get(i));
					System.out.print(ListIDCard.size());
					Card card = db.GetCard(idcard[i]);	
					CartList.add(card);
					
				}
				ctcartList=db.GetChiTietCard();
			} catch (SQLException e) {
				request.setAttribute("message","Product is already added to Cart");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("ctcartList", ctcartList);
			request.setAttribute("CartList", CartList);			
			request.getRequestDispatcher("ShowCart.jsp").forward(request, response);
		}
		
		if(page.equals("addtocart")) {
			String id = request.getParameter("id");
			String action = request.getParameter("action");
			Product p = new Product();
			boolean check = p.check(cartlist,id);			
					
			if(check) {
				
				request.setAttribute("message", "Product is already added to Cart");
			}
			else {
			ChiTietCard chitiet= new ChiTietCard();
			chitiet.setIdProduct(Integer.parseInt(id));
			chitiet.setSoluong(1);
			
			cartlist.add(chitiet);
			request.setAttribute("message","Product successfully added to Cart");
			}
			if(action.equals("index")) {
				Now_page=action;
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
			if(action.equals("NuocGiaiKhat")) {
				Now_page=action;
				request.getRequestDispatcher("NuocGiaiKhat.jsp").forward(request, response);
			}
				
			if(action.equals("TraSua")) {
				Now_page=action;
				request.getRequestDispatcher("TraSua.jsp").forward(request, response);
			}
				
			if(action.equals("NuocEp")) {
				Now_page=action;
				request.getRequestDispatcher("NuocEp.jsp").forward(request, response);
			}
				
			if(action.equals("SinhTo")) {
				Now_page=action;
				request.getRequestDispatcher("SinhTo.jsp").forward(request, response);
			}
				
			if(action.equals("AnVat")) {
				Now_page=action;
				request.getRequestDispatcher("AnVat.jsp").forward(request, response);
			}
				
			if(action.equals("all-products")) {
				Now_page=action;
				request.getRequestDispatcher("all-products.jsp").forward(request, response);
			}
				
		}
		if(page.equals("MyAccount")) {
			DB db= new DB();
			String username= request.getParameter("username");
			int id=0;
			try {
				id = db.getID(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user= new User();
			try {
				user =db.GetUser(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("address", user.getAddress());
			request.setAttribute("username", user.getUsername());
			request.getRequestDispatcher("MyAccount.jsp").forward(request, response);
		}
		if(page.equals("success")) {
			String body="Cam on quy khach da mua hang, duoi day la cac san pham da mua: \n"
					+ "stt    ten     dongia   soluong  tongtien";
			DB db = new DB();
			int iduser=0;
			try {
				iduser = db.getID(Now_user);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int total= Integer.parseInt(request.getParameter("total"));
			try {
				int idcard=db.CountCart()+1;
				int price=0;
				String name="";
				//int total=0;
				for(int i=0;i<cartlist.size();i++) {
					for(int j=0;j<list.size();j++) {
						if(cartlist.get(i).getIdProduct()==list.get(j).getId()) {
							price=Integer.parseInt(list.get(j).getPrice());
							name=list.get(j).getName();
						}
					}
					
					cartlist.get(i).setIdCard(idcard);
					cartlist.get(i).setDongia(price);
					cartlist.get(i).setThanhTien(cartlist.get(i).getSoluong()*cartlist.get(i).getDongia());
					total=total+cartlist.get(i).getThanhTien();
					db.addChitietCard(idcard, cartlist.get(i).getIdProduct(),price, cartlist.get(i).getSoluong(), price*1);
					ChiTietCard cart = new ChiTietCard();
					cart = db.GetChiTietCardCustom(cartlist.get(i).toString());
					body = body + " " + cart.getIdProduct() + " " + name + " " + price + " " + cart.getSoluong() + " " + price*cart.getSoluong() + " \n";
				}
				db.addCard(idcard,iduser, LocalDate.now().toString(),total ,1);
				User user=db.GetUser(iduser);
				String to = user.getEmail();
				//String to = "18110086@student.hcmute.edu.vn";
				String from = "doanwebute@gmail.com";
				String host = "smtp.gmail.com";
				
				Properties properties = System.getProperties();
				properties.put("mail.transport.protocol", "smtp");
			    properties.put("mail.smtp.host", host);
			    properties.put("mail.smtp.auth", "true");  
			    properties.put("mail.smtp.port", "587");  
			    properties.put("mail.smtp.starttls.enable", "true");
			    Session session = Session.getInstance(properties, new Authenticator() {
			    	@Override
			    	protected PasswordAuthentication getPasswordAuthentication() {
			    		return new PasswordAuthentication("doanwebute@gmail.com", "DoAnWebute");
			    	}
			    });
			    System.out.println("1\n");
			    try {
			         MimeMessage message = new MimeMessage(session);
			         message.setFrom(new InternetAddress(from));
			         message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			         message.setSubject("Xac nhan mua hang");
			         message.setText(body);
			         System.out.println("2\n");
			         Transport.send(message);
			         System.out.println("Sent message successfully....");
			    } catch (MessagingException mex) {
			         mex.printStackTrace();
			    }
			} catch (SQLException e) {
				request.setAttribute("message","Product is Not added to Cart");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
		
		if(page.equals("remove")) {
			String id = request.getParameter("id");
			Product p = new Product();
			cartlist = p.remove(cartlist,id);
			
			session = request.getSession();
			session.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		
		if(page.equals("price-sort")) {
			String price = request.getParameter("sort");
			String action = request.getParameter("action");
			Product p = new Product();
			if(price.equals("low-to-high"))
				list = p.lowtohigh(list);
			else
				list = p.hightolow(list);
			
			session.setAttribute("list", list);
			
			if(action.equals("index")) {
				Now_page=action;
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
			if(action.equals("NuocGiaiKhat")) {
				Now_page=action;
				request.getRequestDispatcher("NuocGiaiKhat.jsp").forward(request, response);
			}
				
			if(action.equals("TraSua")) {
				Now_page=action;
				request.getRequestDispatcher("TraSua.jsp").forward(request, response);
			}
				
			if(action.equals("NuocEp")) {
				Now_page=action;
				request.getRequestDispatcher("NuocEp.jsp").forward(request, response);
			}
				
			if(action.equals("SinhTo")) {
				Now_page=action;
				request.getRequestDispatcher("SinhTo.jsp").forward(request, response);
			}
				
			if(action.equals("AnVat")) {
				Now_page=action;
				request.getRequestDispatcher("AnVat.jsp").forward(request, response);
			}
				
			if(action.equals("all-products")) {
				Now_page=action;
				request.getRequestDispatcher("all-products.jsp").forward(request, response);
			}
			if(action.equals("Search_Products")) {
				request.getRequestDispatcher("Search_Products.jsp").forward(request, response);
			}
				
		}
		if( page.equals("ok")) {
			
			 session = request.getSession();
			 cartlist.clear();
			 session.setAttribute("cartlist", cartlist);
			 session.setAttribute("list", list);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		if( page.equals("search")) {
			String key=request.getParameter("txtsearch");
			DB db = new DB();
			if(Now_page.equals("NuocGiaiKhat") || Now_page.equals("TraSua") || Now_page.equals("NuocEp") || Now_page.equals("SinhTo") ||Now_page.equals("AnVat") ) {
				try {
					list = db.ListSearchPage(key, Now_page);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
					
			}
			else {
				try {
					list = db.ListSearch(key);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						
			}
			request.setAttribute("key",key);
			request.setAttribute("list", list);						 
			request.getRequestDispatcher("Search_Products.jsp").forward(request, response);
		}
		
		if(page.equals("edit-Account")){
			boolean check = false;
			request.setAttribute("check", check);
			request.getRequestDispatcher("EditAcc.jsp").forward(request, response);
		}
		if(page.equals("editsoluong")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			int soluong= Integer.parseInt(request.getParameter("soluong"));	
			for(int i=0;i<cartlist.size();i++) {
				if(cartlist.get(i).getIdProduct()==id) {
					cartlist.get(i).setSoluong(soluong);
				}
			}				
			//JOptionPane.showMessageDialog(null, soluong+"111111", "Info", JOptionPane.INFORMATION_MESSAGE);
			request.setAttribute("cartlist",cartlist );
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		
		
		if(page.equals("cart")) {
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		
		
		
	}

}
