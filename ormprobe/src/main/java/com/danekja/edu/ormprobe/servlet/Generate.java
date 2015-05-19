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
public class Generate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DataGenerator generator;
    
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
        AnnotationConfiguration cfg=new AnnotationConfiguration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        generator = new DataGenerator( new SessionPersistUtil(factory) );
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
        generator.generateData(true);
        response.sendRedirect("index.jsp");

	} catch (Exception e) {
			throw new ServletException("Unable to realize registration", e);
	}
	}

    
}
