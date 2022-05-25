package lk.ijse.pos.entity;

public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int orderQTY;
    private double unitPrice;
    private double discount;

    public OrderDetail(){}

    public OrderDetail(String orderId, String itemCode, int orderQTY, double unitPrice, double discount) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setOrderQTY(orderQTY);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
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
