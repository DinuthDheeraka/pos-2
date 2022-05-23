package dto;

import java.time.LocalDate;

public class CustomDTO {
    private String custID;
    private String cusTitle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;
    private LocalDate joinedDate;

    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private double maxDiscount;
    private int qoh;
    private LocalDate addedDate;

    private String orderDetailorderId;
    private String orderDetailitemCode;
    private int orderDetailorderQTY;
    private double orderDetailunitPrice;
    private double orderDetaildiscount;

    private String ordersOrderId;
    private LocalDate ordersDate;
    private String ordersCustID;

    public CustomDTO( String orderDetailitemCode,String description, String packSize, double orderDetailunitPrice,int orderDetailorderQTY,double orderDetaildiscount) {
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setOrderDetailitemCode(orderDetailitemCode);
        this.setOrderDetailorderQTY(orderDetailorderQTY);
        this.setOrderDetailunitPrice(orderDetailunitPrice);
        this.setOrderDetaildiscount(orderDetaildiscount);
    }

    public CustomDTO(String custName, String ordersOrderId, LocalDate ordersDate, String ordersCustID) {
        this.custName = custName;
        this.ordersOrderId = ordersOrderId;
        this.ordersDate = ordersDate;
        this.ordersCustID = ordersCustID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCusTitle() {
        return cusTitle;
    }

    public void setCusTitle(String cusTitle) {
        this.cusTitle = cusTitle;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public String getOrderDetailorderId() {
        return orderDetailorderId;
    }

    public void setOrderDetailorderId(String orderDetailorderId) {
        this.orderDetailorderId = orderDetailorderId;
    }

    public String getOrderDetailitemCode() {
        return orderDetailitemCode;
    }

    public void setOrderDetailitemCode(String orderDetailitemCode) {
        this.orderDetailitemCode = orderDetailitemCode;
    }

    public int getOrderDetailorderQTY() {
        return orderDetailorderQTY;
    }

    public void setOrderDetailorderQTY(int orderDetailorderQTY) {
        this.orderDetailorderQTY = orderDetailorderQTY;
    }

    public double getOrderDetailunitPrice() {
        return orderDetailunitPrice;
    }

    public void setOrderDetailunitPrice(double orderDetailunitPrice) {
        this.orderDetailunitPrice = orderDetailunitPrice;
    }

    public double getOrderDetaildiscount() {
        return orderDetaildiscount;
    }

    public void setOrderDetaildiscount(double orderDetaildiscount) {
        this.orderDetaildiscount = orderDetaildiscount;
    }

    public String getOrdersOrderId() {
        return ordersOrderId;
    }

    public void setOrdersOrderId(String ordersOrderId) {
        this.ordersOrderId = ordersOrderId;
    }

    public LocalDate getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(LocalDate ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getOrdersCustID() {
        return ordersCustID;
    }

    public void setOrdersCustID(String ordersCustID) {
        this.ordersCustID = ordersCustID;
    }
}
