package com.danekja.edu.ormprobe.servlets;

import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.JPAPersistUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Karel Zíbar
 */
public class WelcomeServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");

		System.out.println("WELCOME SERVLET");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JET");
		EntityManager em = emf.createEntityManager();
		DataGenerator generator = new DataGenerator( new JPAPersistUtil(emf, em) );

		generator.generateData(false);
		System.out.println("====================================================================================================================");
	}
}
