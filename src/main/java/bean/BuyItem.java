package bean;

public class BuyItem {
	private Integer buyId;
	private Integer bookId;
	private Integer number;
	private double subtotal;
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
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
