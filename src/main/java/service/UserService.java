package service;

import javax.servlet.http.HttpSession;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.EMailTool;
import tools.Encryption;

public class UserService {
	//0表示不存在该用户/电子邮箱/手机号,1表示成功，-1表示密码错误,-100表示出错
	//注意分清楚这个函数的变量user和user1
	public Integer login(User user,HttpSession session){
		int result=-100;
		DatabaseDao databaseDao = null;
		UserDao userDao=new UserDao();
		User user1=null;
		try {
			databaseDao = new DatabaseDao();
			userDao=new UserDao();
			if(userDao.hasStringValue(databaseDao, "user_name", user.getUserName())==1){
				user1=userDao.getUserByName(databaseDao, user.getUserName());
				user.setSalt(user1.getSalt());
			}
			else{
				result=0;
			}
			if(Encryption.checkPassword(user, user1.getPassword())){
				session.setAttribute("user", user);
				result=1;
			}
			else{
				result=-1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(databaseDao!=null){
				databaseDao.close();
			}
		}
		return result;
	}
	//返回-1代表邮箱已被注册，返回1代表发送成功，返回0代表发送失败,-100表示出错
	public int emailForRegister(User user,Integer rand){
		int result=-100;
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			if(userDao.hasStringValue(databaseDao,"email", user.getEmail())==1){
				result=-1;
			}
			if(EMailTool.emailForRegister(user.getEmail(), rand)==1){
				result=1;
			}
			else{
				result=0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(databaseDao!=null){
				databaseDao.close();
			}
		}
		return result;
	}
	//返回0表示数据库操作错误，1表示成功创建用户，-1表示有重名用户，-10表示emai已被注册，-11表示重名用户且email被注册
	public Integer emailRegister(User user){
		int result=0;//数据库操作失败	
		UserDao UserDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			if(UserDao.hasStringValue(databaseDao,"name", user.getUserName())==1){
				//有同名用户，不可以注册
				result=-1;
			}
			if(UserDao.hasStringValue(databaseDao,"email", user.getEmail())==1){
				//有同名用户，不可以注册
				result+=-10;
			}
			if(result<0){
				//用户名已被注册或email被注册过
				return result;
			}
			Encryption.encryptPasswd(user);
			if(UserDao.emailRegister(databaseDao,user)){
				return 1;	//成功
			}
			else{
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(databaseDao!=null){
				databaseDao.close();
			}
		}
		return result;
	}
}
