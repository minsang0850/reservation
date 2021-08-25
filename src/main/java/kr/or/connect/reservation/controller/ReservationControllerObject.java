package kr.or.connect.reservation.controller;

import java.sql.Timestamp;
import java.util.List;

class Price{
	private int count;
	private int productPriceId;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
}

class ReservationPostRequestVO{
	private List<Price> prices;
	private int productId;
	private int displayInfoId;
	private String reservationYearMonthDay;
	private int userId;
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}
	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}

class ReservationPostVO{
	private int id;
	private int productId;
	private int cancelFlag;
	private int displayInfoId;
	private int userId;
	private Timestamp reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private List<Price> prices;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
	
}

class ReservationGetVO{
	private int id;
	private int productId;
	private int displayInfoId;
	private int cancelFlag;
	private String productDescription;
	private String productContent;
	private int userId;
	private int sumPrice;
	private Timestamp reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}

class ReservationPutRequestVO{
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
public class ReservationControllerObject {

}
