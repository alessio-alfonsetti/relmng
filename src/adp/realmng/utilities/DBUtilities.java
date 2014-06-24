package adp.realmng.utilities;

import javax.servlet.ServletContext;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DBUtilities extends SimpleJdbcDaoSupport{

	/**
     * returns bean DAO
     * 
     * @param pContext
     * @return
     */
    public static SimpleJdbcDaoSupport getSimpleJdbcDaoSupport(String pBeanDAOName, ServletContext pContext)
    {
        WebApplicationContext lWAC = WebApplicationContextUtils.getWebApplicationContext(pContext);
        return (SimpleJdbcDaoSupport) lWAC.getBean(pBeanDAOName);
    }
	
}
