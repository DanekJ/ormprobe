package com.danekja.edu.ormprobe.servlets;

import com.danekja.edu.ormprobe.dao.DaoTester;
import com.danekja.edu.ormprobe.dao.DaoTesterWithCriteriaQueries;
import com.danekja.edu.ormprobe.dao.DaoTesterWithHQL;

import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Karel Zíbar
 */
public class DaoSwitcherServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");

		String testerType = request.getParameter("testerType");
		String method = request.getParameter("method");
		long itemId = Long.parseLong(request.getParameter("itemId"));
		long bigGroupId = Long.parseLong(request.getParameter("bigGroupId"));
		DaoTester tester = null;

		if (testerType.equals("HQL")){
			tester = new DaoTesterWithHQL(Persistence.createEntityManagerFactory("JET"));
		}
		else if (testerType.equals("Criteria")){
			tester = new DaoTesterWithCriteriaQueries(Persistence.createEntityManagerFactory("JET"));
		}

		if(tester == null) return;


		if (method.equals("listOwnershipCandidates")){
			tester.listOwnershipCandidates(bigGroupId);
		}
		else if (method.equals("listItemsBigGroups")){
			tester.listItemsBigGroups(itemId);
		}
		else if (method.equals("isConnectedToBigGroup")){
			tester.isConnectedToBigGroup(bigGroupId, itemId);
		}
		else if (method.equals("listBigGroupsItems")){
			tester.listBigGroupsItems(bigGroupId);
		}

		response.sendRedirect(request.getContextPath() + "/menu");
	}
}
