package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDao {
	public List<User> getAllUsers(DatabaseDao databaseDao) throws SQLException{
		List<User> users=new ArrayList<User>();
		String sql="select * from user";
		databaseDao.query(sql);
		while(databaseDao.next()){
			User user=new User();
			user.setUserId(databaseDao.getInt("user_id"));
			user.setUserName(databaseDao.getString("user_name"));
			user.setEmail(databaseDao.getString("email"));
			user.setPhone(databaseDao.getString("phone"));
			user.setRole(databaseDao.getString("role"));
			user.setPassword(databaseDao.getString("password"));
			user.setSalt(databaseDao.getString("salt"));
			user.setImgUrl(databaseDao.getString("img_url"));
			users.add(user);
		}
		return users;
	}
	
	public User getUserByName(DatabaseDao databaseDao,String userName) throws SQLException{
		User user=new User();
		String sql="select * from user where user_name='"+userName+"'";
		databaseDao.query(sql);
		if(databaseDao.next()){
			user.setUserId(databaseDao.getInt("user_id"));
			user.setUserName(databaseDao.getString("user_name"));
			user.setPassword(databaseDao.getString("password"));
			user.setEmail(databaseDao.getString("email"));
			user.setPhone(databaseDao.getString("phone"));
			user.setRole(databaseDao.getString("role"));
			user.setSalt(databaseDao.getString("salt"));
			user.setImgUrl(databaseDao.getString("img_url"));
			return user;
		}
		return null;
	}
	
	//根据字段名，查是否有字段值为value的记录
	//返回值：1表示有相同值、-1表示没有相同值
	public int hasStringValue(DatabaseDao databaseDao,String fieldName, String value) throws Exception{
		int result = -1;
		String sql="select * from user where "+fieldName+"='"+value+"'";
		databaseDao.query(sql);
		if (databaseDao.next()) {
			result=1;
		}
		return result;
	}

	//根据字段名，查除了某个用户外是否有字段值为value的记录
	//返回值：1表示有相同值、-1表示没有相同值
	public int hasStringValueExceptMe(DatabaseDao databaseDao,String fieldName, String value,Integer userId) throws Exception{
		int result = -1;
		String sql="select * from user where "+fieldName+"='"+value+"' AND user_id!="+userId;
		databaseDao.query(sql);
		if (databaseDao.next()) {
			result=1;
		}
		return result;
	}

    //根据字段名，查是否有字段值为value的记录
    //返回值：1表示有相同值、-1表示没有相同值
    public int hasIntegerValue(DatabaseDao databaseDao,String fieldName, Integer value) throws Exception{
        int result = -1;
        String sql="select * from user where "+fieldName+"="+value;
        databaseDao.query(sql);
        if (databaseDao.next()) {
            result=1;
        }
        return result;
    }

	public boolean emailRegister(DatabaseDao databaseDao,User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean returnValue=false;
		String sql="insert into user(role,user_name,email,password,salt,img_url) values('"+
				user.getRole()+"','"+user.getUserName()+"','"+user.getEmail()+"','"+
				user.getPassword()+"','"+user.getSalt()+"','"+user.getImgUrl()+"')";
		if(databaseDao.update(sql)==1){
			returnValue=true;
		}
		return returnValue;
	}
	public boolean addAUser(DatabaseDao databaseDao,User user) throws SQLException{
		boolean returnValue=false;
		String sql="insert into user(role,user_name,password,salt) values('"+
				user.getRole()+"','"+user.getUserName()+"','"+
				user.getPassword()+"','"+user.getSalt()+"')";
		if(databaseDao.update(sql)==1){
			returnValue=true;
		}
		return returnValue;
	}
	public boolean updateUserById(DatabaseDao databaseDao,User user) throws SQLException{
		boolean returnValue=false;
		String sql="";
		if(user.getImgUrl()==null||"".equals(user.getImgUrl())){
			sql="update user set user_name='"+user.getUserName()+"',password='"+user.getPassword()+"',salt='"+user.getSalt()+"' where user_id="+user.getUserId();
		}
		else{
			sql="update user set user_name='"+user.getUserName()+"',password='"+user.getPassword()+"',salt='"+user.getSalt()+"',img_url='"+user.getImgUrl()+"' where user_id="+user.getUserId();
		}
		if(databaseDao.update(sql)==1){
			returnValue=true;
		}
		return returnValue;
	}
	public boolean deleteUserById(DatabaseDao databaseDao,Integer userId) throws SQLException{
		boolean returnValue=false;
		String sql="delete from user where user_id="+userId;
		if(databaseDao.update(sql)==1){
			returnValue=true;
		}
		return returnValue;
	}
	public User getUserById(DatabaseDao databaseDao,Integer userId)throws SQLException{
        User user=new User();
        String sql="select * from user where user_id="+userId;
        databaseDao.query(sql);
        if(databaseDao.next()){
            user.setUserId(databaseDao.getInt("user_id"));
            user.setUserName(databaseDao.getString("user_name"));
            user.setPassword(databaseDao.getString("password"));
            user.setEmail(databaseDao.getString("email"));
            user.setPhone(databaseDao.getString("phone"));
            user.setRole(databaseDao.getString("role"));
            user.setSalt(databaseDao.getString("salt"));
            user.setImgUrl(databaseDao.getString("img_url"));
            return user;
        }
        return null;
    }
}
