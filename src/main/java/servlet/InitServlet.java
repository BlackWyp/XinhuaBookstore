package servlet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.quartz.SchedulerException;

import dao.DatabaseDao;
import tools.FileTool;
import tools.WebProperties;

public class InitServlet extends HttpServlet {
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		ServletContext servletContext=conf.getServletContext();
		FileTool.root=servletContext.getRealPath("\\");		
		
		//读取属性文件
		String fileDir=servletContext.getRealPath("\\WEB-INF\\web.properties");		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties().setFileName(fileDir));
		try
		{
			
			WebProperties.config = builder.getConfiguration();
			WebProperties.config.addProperty("projectRoot", 
					servletContext.getRealPath(WebProperties.config.getString("projectName")));
		}catch(ConfigurationException cex){
			cex.printStackTrace();
		}	
		System.out.println("正在初始化。。");
	}
}
