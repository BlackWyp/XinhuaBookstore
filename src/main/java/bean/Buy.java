package bean;

import java.util.List;

public class Buy {
	private Integer buyId;
	private Integer userId;
	private String buyTime;
	private String address;
	private String phone;
	private String name;
	private String state;
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
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<BuyItem> getBuyItems() {
		return buyItems;
	}
	public void setBuyItems(List<BuyItem> buyItems) {
		this.buyItems = buyItems;
	}
}
