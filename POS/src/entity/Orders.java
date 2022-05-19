package entity;

import java.time.LocalDate;

public class Orders {
    String orderId;
    LocalDate date;
    String custID;

    Orders(){}

    public Orders(String orderId, LocalDate date, String custID) {
        this.orderId = orderId;
        this.date = date;
        this.custID = custID;
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
