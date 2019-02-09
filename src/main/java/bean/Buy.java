package bean;

import java.util.List;

public class Buy {
	private Integer buyId;
	private Integer userId;
	private double totalPrice;
	private String address;
	private String orderTime;
	private List<BuyItem> buyItems;
	public Integer getBuyId() {
		return buyId;
	}
	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public List<BuyItem> getBuyItems() {
		return buyItems;
	}
	public void setBuyItems(List<BuyItem> buyItems) {
		this.buyItems = buyItems;
	}
}
