package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Book;

public class BookDao {

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			DatabaseDao dao = new DatabaseDao();
			String sql = "select * from book";
			dao.query(sql);
			while (dao.next()) {
				Book book = new Book();
				book.setBookId(dao.getInt("book_id"));
				book.setBookName(dao.getString("book_name"));
				book.setPrice(dao.getFloat("price"));
				book.setCaption(dao.getString("caption"));
				book.setCategoryName(dao.getString("category_name"));
				book.setIntroduction(dao.getString("introduction"));
				book.setImgUrl(dao.getString("img_url"));
				books.add(book);
			}
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<Book> getBooksByName(String name) {
		List<Book> books = new ArrayList<Book>();
		try {
			DatabaseDao dao = new DatabaseDao();
			String sql = "select * from book where book_name like '%" + name + "%'";
			dao.query(sql);
			while (dao.next()) {
				Book book = new Book();
				book.setBookId(dao.getInt("book_id"));
				book.setBookName(dao.getString("book_name"));
				book.setPrice(dao.getFloat("price"));
				book.setCaption(dao.getString("caption"));
				book.setCategoryName(dao.getString("category_name"));
				book.setIntroduction(dao.getString("introduction"));
				book.setImgUrl(dao.getString("img_url"));
				books.add(book);//试试
			}
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
		
	}

	public Book getBookById(Integer id) {
		Book book = null;
		try {
			DatabaseDao dao = new DatabaseDao();
			String sql = "select * from book where book_id=" + id.toString();
			dao.query(sql);
			while (dao.next()) {
				book = new Book();
				book.setBookId(dao.getInt("book_id"));
				book.setBookName(dao.getString("book_name"));
				book.setPrice(dao.getFloat("price"));
				book.setCaption(dao.getString("caption"));
				book.setCategoryName(dao.getString("category_name"));
				book.setIntroduction(dao.getString("introduction"));
				book.setImgUrl(dao.getString("img_url"));
			}
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public void deleteBookById(Integer id) {
		String sql = "delete from book where book_id=" + id.toString();
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBook(String categoryName, String bookName, String imgUrl, String caption, String introduction,
			double price) {
		String sql = "insert into book(book_name,price,category_name,caption,img_url,introduction) values('" + bookName
				+ "','" + price + "','" + categoryName + "','" + caption + "','" + imgUrl + "','" + introduction + "')";
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBook(Integer id, String categoryName, String bookName, String imgUrl, String caption,
			String introduction, double price) {
		String sql = "update book set book_name='" + bookName + "',price='" + price + "',category_name='" + categoryName
				+ "',caption='" + caption + "',img_url='" + imgUrl + "',introduction='" + introduction
				+ "' where book_id=" + id.toString();
		try {
			DatabaseDao dao = new DatabaseDao();
			dao.update(sql);
			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
