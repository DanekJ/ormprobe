package com.danekja.edu.ormprobe.servlet;
import com.danekja.edu.ormprobe.dao.DaoTester;
import com.danekja.edu.ormprobe.dao.DaoTesterWithCriteriaBuilder;
import com.danekja.edu.ormprobe.dao.DaoTesterWithStringQueries;
import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.SessionPersistUtil;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 *
 * @author Zuzka
 */
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DaoTester tester;
    DaoTester dcb;
    Set<Group> groups = null;
    Set<BigGroup> bgs = null;
    Set<Item> items = null;
    Boolean connect;
    Long bg;
    Long item;
    
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
        AnnotationConfiguration cfg=new AnnotationConfiguration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        tester = new DaoTesterWithStringQueries(factory);
        dcb = new DaoTesterWithCriteriaBuilder(factory);
    }
        /**
         * Servlet vytvoĹ™Ă­ novĂ©ho uĹľivatele s nulovĂ˝mi parametry a id, dĂ­ky kterĂ©mu je anonymĹŻm umoĹľnÄ›no nakupovat
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException 
         */        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");  
        HttpSession session = request.getSession(true);
        
        int type = Integer.parseInt(request.getParameter("type"));
        int method = Integer.parseInt(request.getParameter("method"));
        if(!request.getParameter("bg").equals("")){
            bg = Long.parseLong(request.getParameter("bg"));
        }
        if(!request.getParameter("item").equals("")){
            item = Long.parseLong(request.getParameter("item"));
        }
                        
                        switch(method){
                            case 1:
                                if(type == 1){
                                   groups = tester.listOwnershipCandidates(bg);
                                }
                                else{
                                    groups = dcb.listOwnershipCandidates(bg);
                                }
                                request.setAttribute("result", groups);
                                break;
                            case 2:
                                if(type == 1){
                                    bgs = tester.listItemsBigGroups(item);
                                }
                                else{
                                    bgs = dcb.listItemsBigGroups(item);
                                }
                                request.setAttribute("result", bgs);
                                break;
                            case 3:
                                if(type == 1){
                                    connect = tester.isConnectedToBigGroup(bg, item);
                                }
                                else{
                                    connect = dcb.isConnectedToBigGroup(bg, item);
                                }
                                request.setAttribute("connect", connect);
                                break;
                            case 4:
                                if(type == 1){
                                    items = tester.listBigGroupsItems(bg);
                                }
                                else{
                                    items = dcb.listBigGroupsItems(bg);
                                }
                                request.setAttribute("result", items);
                                break;
                        }
                        
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);

		} catch (Exception e) {
			throw new ServletException("Unable to realize registration", e);
		}
	}

    
}
