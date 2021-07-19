package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.beans.Product;
import com.model.DB;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String username="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String role = request.getParameter("role");
		if(page == null) {
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}else {
			doPost(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String role = request.getParameter("role");
		String indexString = request.getParameter("index");
		if(indexString == null)
			indexString = "1";
		int index= Integer.parseInt(indexString);
		int index2 = index*10;
		index = index*10 -9;
		
		if(page.equals("admin-login-form")) {
			username = request.getParameter("username");
			String password = request.getParameter("password");
			
			
			if(username.equals("admin") && password.equals("admin@1234")) {
				DB db = new DB();
				int count=0;
				int endPage = 0;
				try {
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
				session.setAttribute("session", session);
				session.setAttribute("username", username);
				session.setAttribute("index", index);
				session.setAttribute("index2", index2);
				session.setAttribute("endPage", endPage);
				request.getRequestDispatcher("admin/indexadmin.jsp").forward(request, response);

			}
			else {
				request.setAttribute("msg", "Invalid Crediantials");
				request.setAttribute("username", username);
				request.getRequestDispatcher("admin/login.jsp").forward(request, response);

			}
		}
		
		if(page.equals("delete") && role.equals("admin")) {
			String id = request.getParameter("id");
			DB db = new DB();
			try {
				db.deleteProduct(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "Product deleted successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
			request.getRequestDispatcher("admin/indexadmin.jsp").forward(request, response);

		}
		
		if(page.equals("index") && role.equals("admin")) {
			System.out.print(index);
			System.out.print(index2);
			session = request.getSession();
			session.setAttribute("session", session);
			session.setAttribute("index", index);
			session.setAttribute("index2", index2);
			request.getRequestDispatcher("admin/indexadmin.jsp").forward(request, response);
		}
		
		if(page.equals("addproduct") && role.equals("admin")) {
			request.getRequestDispatcher("admin/addProduct.jsp").forward(request, response);
		}
		
		if(page.equals("edit") && role.equals("admin")) {
			String id = request.getParameter("id");
			DB account = new DB();
			Product p = null;
			try {
				 p = account.fetchProduct(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("p", p);
			request.getRequestDispatcher("admin/editProduct.jsp").forward(request, response);
		}
		
		if(page.equals("edit_product")){
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String category = request.getParameter("category");
			String featured = request.getParameter("featured");
			Product p = new Product();
			p.setId(Integer.parseInt(id));
			p.setName(name);
			p.setPrice(price);
			p.setCategory(category);
			p.setFeatured(featured);
			
			DB account = new DB();
			try {
				account.updateProduct(p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Product details updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
			request.getRequestDispatcher("admin/indexadmin.jsp").forward(request, response);
		}
		
		if(page.equals("add_product")){
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String category = request.getParameter("category");
			String featured = request.getParameter("featured");
			String image = request.getParameter("image");
			if(category.equals("AnVat") || category.equals("TraSua") || category.equals("NuocEp") || category.equals("SinhTo")||category.equals("NuocGiaiKhat")) {
				Product p = new Product();
				p.setName(name);
				p.setPrice(price);
				p.setCategory(category);
				p.setFeatured(featured);
				p.setImage("img/"+image);
				
				DB account = new DB();
				try {
					account.addProduct(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Product added Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
				request.getRequestDispatcher("admin/indexadmin.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "Invalid Category");
				request.setAttribute("name", name);
				request.setAttribute("price",  price);
				request.setAttribute("featured", featured);
				request.setAttribute("image", image);
				JOptionPane.showMessageDialog(null, "Invalid Category", "Warning", JOptionPane.INFORMATION_MESSAGE);
				request.getRequestDispatcher("admin/addProduct.jsp").forward(request, response);
			}
			
			
		}
	}

}
