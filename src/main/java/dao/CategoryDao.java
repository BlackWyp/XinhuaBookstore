package dao;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
	public List<String> getAllCategory() {
		List<String> categories = new ArrayList<String>();
		String sql = "select * from category";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.query(sql);
			while (dao.next()) {
				categories.add(dao.getString("category_name"));
			}
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	public void deleteCategory(String name) {
		String sql = "delete from category where category_name='"+name+"'";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCategory(String oldName,String newName) {
		String sql = "update category set category_name='"+newName+"' where category_name='"+oldName+"'";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCategory(String name) {
		String sql = "insert into category values('"+name+"')";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> searchCategory(String name) {
		List<String> categories = new ArrayList<String>();
		String sql = "select * from category where category_name like '%"+name+"%'";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.query(sql);
			while (dao.next()) {
				categories.add(dao.getString("category_name"));
			}
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
}
