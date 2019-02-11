package service;

import java.util.List;

import dao.CategoryDao;

public class CategoryService {
	public List<String> getAllCategory() {
		CategoryDao categoryDao=new CategoryDao();
		return categoryDao.getAllCategory();
	}
	public void deleteCategory(String name) {
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.deleteCategory(name);
	}
	public void updateCategory(String oldName,String newName) {
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.updateCategory(oldName, newName);
	}
	public void addCategory(String name) {
		CategoryDao categoryDao=new CategoryDao();
		categoryDao.addCategory(name);
	}
	public List<String> searchCategory(String name) {
		CategoryDao categoryDao=new CategoryDao();
		return categoryDao.searchCategory(name);
	}
	
}
