package service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Book;
import dao.BookDao;
import tools.FileTool;
import tools.WebProperties;

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

	public List<Book> getBookById(Integer id) {
		BookDao bookDao = new BookDao();
		return bookDao.getBookById(id);
	}

	public void deleteBookById(Integer id) {
		BookDao bookDao = new BookDao();
		bookDao.deleteBookById(id);
	}

	public void addBook(String categoryName, String bookName, String caption, String introduction, double price,
			HttpServletRequest request) {
		BookDao bookDao = new BookDao();
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

				} else {// 上传文件
					File uploadedFile = new File("");
					String randomFileName;
					do {
						randomFileName = FileTool.getRandomFileNameByCurrentTime(item.getName());
						String full = request.getServletContext().getRealPath(
								WebProperties.config.getString("bookPictureDirDefault") + "\\" + randomFileName);

						uploadedFile = new File(full);
					} while (uploadedFile.exists());// 确保文件未存在

					item.write(uploadedFile);// 将临时文件转存为新文件保存
					item.delete();// 删除临时文件
					imgUrl = "\\" + WebProperties.config.getString("projectName")
							+ WebProperties.config.getString("bookPictureDirDefault") + "\\" + randomFileName;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		bookDao.addBook(categoryName, bookName, imgUrl, caption, introduction, price);
	}

	public void updateBook(Integer id, String categoryName, String bookName, String imgUrl, String caption,
			String introduction, double price) {
		BookDao bookDao = new BookDao();
		bookDao.updateBook(id, categoryName, bookName, imgUrl, caption, introduction, price);
	}
}
