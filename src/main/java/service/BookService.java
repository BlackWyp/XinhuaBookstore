package service;

import java.util.List;

import bean.Book;
import dao.BookDao;

public class BookService {
	public List<Book> getAllBooks() {
		BookDao bookDao = new BookDao();
		List<Book> books = bookDao.getAllBooks();
		return books;
	}

	public List<Book> getBooksByName(String name) {
		BookDao bookDao = new BookDao();
		return bookDao.getBooksByName(name);
	}

	public Book getBookById(Integer id) {
		BookDao bookDao = new BookDao();
		return bookDao.getBookById(id);
	}

	public void deleteBookById(Integer id) {
		BookDao bookDao = new BookDao();
		bookDao.deleteBookById(id);
	}

	public void addBook(String categoryName, String bookName, String imgUrl, String caption, String introduction,
			double price) {
		BookDao bookDao = new BookDao();
		bookDao.addBook(categoryName, bookName, imgUrl, caption, introduction, price);
	}

	public void updateBook(Integer id, String categoryName, String bookName, String imgUrl, String caption,
			String introduction, double price) {
		BookDao bookDao = new BookDao();
		bookDao.updateBook(id, categoryName, bookName, imgUrl, caption, introduction, price);
	}
}
