package com.ray.servleast.listener;

import com.ray.servleast.dao.User;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Ray on 2017/12/31.
 */
@WebListener
public class ContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            File sqlFile = new File(getClass().getClassLoader().getResource("sql.properties").getFile());
            Properties properties = new Properties();
            properties.load(new FileInputStream(sqlFile));
            servletContextEvent.getServletContext().setAttribute("SQLStatements", properties);
            User.setSQL(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
