package lk.ijse.pos.entity;

import java.time.LocalDate;

public class Orders {
    private String orderId;
    private LocalDate date;
    private String custID;

    public Orders(){}

    public Orders(String orderId, LocalDate date, String custID) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustID(custID);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }
}
