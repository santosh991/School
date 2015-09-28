/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.school;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * 
 * @author peter
 *
 */
 
public class Logout extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3607520645710832503L;
	private Cache accountsCache, statisticsCache;

    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        CacheManager mgr = CacheManager.getInstance();
        accountsCache = mgr.getCache(CacheVariables.CACHE_ACCOUNTS_BY_USERNAME);
        statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_ACCOUNT);
    }
    

    /**
     *
     * @param request
     * @param response
     * @throws ServletException, IOException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        response.sendRedirect("index.jsp");

        if (session != null) {
            // Remove the statistics of this user from cache
            String username = (String) session.getAttribute(SessionConstants.ACCOUNT_SIGN_IN_KEY);
            SchoolAccount school = new SchoolAccount();
            
            
            Element element;
            if ((element = accountsCache.get(username)) != null) {
            	school = (SchoolAccount) element.getObjectValue();
            }
            statisticsCache.remove(school.getUuid());

            //destroy the session
            session.invalidate();
            
        } // end 'if (session != null) '
        
    }
}
