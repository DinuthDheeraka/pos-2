package lk.ijse.pos.dto;

import java.time.LocalDate;

public class CustomerDTO {
    private String custID;
    private String cusTitle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;
    private LocalDate joinedDate;

    public CustomerDTO() {
    }

    public CustomerDTO(String custID, String cusTitle, String custName, String custAddress,
                       String city, String province, String postalCode, LocalDate joinedDate) {
        this.custID = custID;
        this.cusTitle = cusTitle;
        this.custName = custName;
        this.custAddress = custAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.joinedDate = joinedDate;
    }

    public CustomerDTO(String custID, String cusTitle, String custName, String custAddress,
                       String city, String province, String postalCode) {
        this.custID = custID;
        this.cusTitle = cusTitle;
        this.custName = custName;
        this.custAddress = custAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
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
