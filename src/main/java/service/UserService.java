package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tools.EMailTool;
import tools.Encryption;
import tools.FileTool;
import tools.WebProperties;


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
				if(Encryption.checkPassword(user, user1.getPassword())){
					session.setAttribute("user", user);
					result=1;
				}
				else{
					result=-1;
				}
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
			if(UserDao.hasStringValue(databaseDao,"user_name", user.getUserName())==1){
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
			Encryption.encryptPasswd(user);	//加密
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
	//返回0表示数据库操作错误，1表示成功创建用户，-1表示有重名用户
	public Integer addAUser(HttpServletRequest request){
		User user=new User();
		int result=0;//数据库操作失败
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			if(userDao.hasStringValue(databaseDao,"user_name", user.getUserName())==1){
				//有同名用户，不可以注册
				result=-1;
			}
			if(result<0){
				//用户名已被注册或email被注册过
				return result;
			}
			Encryption.encryptPasswd(user);	//加密
			if(userDao.addAUser(databaseDao,user)){
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
	//返回-1表示有同名用户，0表示数据库操作失败，1表示成功更新用户
	public Integer updateUserById(HttpServletRequest request){
		User user=new User();
		int result=0;//数据库操作失败
		//获取值
		List<String> pList = new ArrayList<String>();
		String imgUrl = "";
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Configure a repository (to ensure a secure temp location is used)
		try {
			String fullPath = request.getServletContext().getRealPath(WebProperties.config.getString("tempDir"));// 获取相对路径的绝对路径
			File repository = new File(fullPath);
			factory.setRepository(repository);// 设置临时文件存放的文件夹
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析request，将其中各表单元素和上传文件提取出来
			List<FileItem> items = upload.parseRequest(request);// items存放各表单元素
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {// 遍历表单元素
				FileItem item = iter.next();
				if (item.isFormField()) {// 非上传文件表单元素
					String value = item.getString("UTF-8");
					pList.add(value);// 将非文件的其他参数放到一个list中，后面可以顺序的去取到
					continue;
				} else {// 上传文件
					File uploadedFile = new File("");
					String randomFileName;
					//判断是否修改了图片
					String pName=item.getName();
					if("".equals(pName)||pName==null) {
						imgUrl = "";
						continue;
					}
					do {
						randomFileName = FileTool.getRandomFileNameByCurrentTime(item.getName());
						String full = request.getServletContext().getRealPath(WebProperties.config.getString("headIconDir") + "\\" + randomFileName);
						uploadedFile = new File(full);
					} while (uploadedFile.exists());// 确保文件未存在

					item.write(uploadedFile);// 将临时文件转存为新文件保存
					item.delete();// 删除临时文件
					imgUrl = "\\\\" + WebProperties.config.getString("projectName") + WebProperties.config.getString("headIconDir") + "\\\\" + randomFileName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setUserId(Integer.parseInt(pList.get(0)));
		user.setUserName(pList.get(1));
		user.setPassword(pList.get(2));
		user.setSalt(pList.get(3));
		user.setImgUrl(imgUrl );

		UserDao userDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			if(userDao.hasStringValueExceptMe(databaseDao,"user_name", user.getUserName(),user.getUserId())==1){
				//有同名用户，不可以用这个用户名
				result=-1;
			}
			if(userDao.hasIntegerValue(databaseDao,"user_id", user.getUserId())==-1){
				//用户id不存在
				result+=-50;
			}
			if(result<0){
				//用户名已被注册或...
				return result;
			}
			//如果盐为空，则密码被改过，需要重新加密
			if(user.getSalt()==null||"".equals(user.getSalt())){
				Encryption.encryptPasswd(user);	//加密
			}
			if(userDao.updateUserById(databaseDao,user)){
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
	//返回0表示数据库操作错误，1表示删除用户，-1表示用户不存在
	public Integer deleteUserById(Integer userId){
		int result=0;//数据库操作失败	
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			if(userDao.hasIntegerValue(databaseDao,"user_id", userId)==-1){
				//用户不存在
				result=-1;
			}
			if(result<0){
				//用户不存在
				return result;
			}
			if(userDao.deleteUserById(databaseDao,userId)){
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
	public List<User> getAllUsers(){
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao = null;
		try {
			databaseDao = new DatabaseDao();
			return userDao.getAllUsers(databaseDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(databaseDao!=null){
				databaseDao.close();
			}
		}
		return null;
	}
	public User getUserById(Integer userId){
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao=null;
		try{
			databaseDao=new DatabaseDao();
			return userDao.getUserById(databaseDao,userId);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(databaseDao!=null){
				databaseDao.close();
			}
		}
		return null;
	}
}
