package entity;

import java.time.LocalDate;

public class Customer {
    private String custID;
    private String cusTitle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;
    private LocalDate joinedDate;

    public Customer() {
    }

    public Customer(String custID, String cusTitle, String custName, String custAddress,
                    String city, String province, String postalCode, LocalDate joinedDate) {
        this.setCustID(custID);
        this.setCusTitle(cusTitle);
        this.setCustName(custName);
        this.setCustAddress(custAddress);
        this.setCity(city);
        this.setProvince(province);
        this.setPostalCode(postalCode);
        this.setJoinedDate(joinedDate);
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
}
