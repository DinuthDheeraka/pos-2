package entity;

public class OrderDetail {
    String orderId;
    String itemCode;
    int orderQTY;
    double unitPrice;
    double discount;

    OrderDetail(){}

    public OrderDetail(String orderId, String itemCode, int orderQTY, double unitPrice, double discount) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQTY = orderQTY;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
