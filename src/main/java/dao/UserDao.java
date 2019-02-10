package dao;

import java.sql.SQLException;

import bean.User;

public class UserDao {
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

	public boolean emailRegister(DatabaseDao databaseDao,User user) throws SQLException {
		// TODO Auto-generated method stub
		boolean returnValue=false;
		String sql="insert into user(role,user_name,email,password,salt) values('"+
				user.getRole()+"','"+user.getUserName()+"','"+user.getEmail()+"','"+
				user.getPassword()+"','"+user.getSalt()+"')";
		if(databaseDao.update(sql)==1){
			returnValue=true;
		}
		return returnValue;
	}
}
