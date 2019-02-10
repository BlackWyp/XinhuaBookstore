package bean;

public class CartItem {
	private Integer cartId;
	private Integer bookId;
	private Integer number;
	private Book theBook;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
