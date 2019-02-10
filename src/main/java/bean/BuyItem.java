package bean;

public class BuyItem {
	private Integer buyId;
	private Integer bookId;
	private Integer number;
	private Book theBook;
	public Integer getBuyId() {
		return buyId;
	}
	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Book getTheBook() {
		return theBook;
	}
	public void setTheBook(Book theBook) {
		this.theBook = theBook;
	}
}
