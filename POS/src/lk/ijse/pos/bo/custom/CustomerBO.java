package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    void insertCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    void deleteCustomer(String custID) throws SQLException, ClassNotFoundException;
    void updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    CustomerDTO getCustomer(String custId) throws SQLException, ClassNotFoundException;
    String getCustomerLastId() throws SQLException, ClassNotFoundException;
    int getCustomerCountByMonth(String month) throws SQLException, ClassNotFoundException;
    int getCustomerCount() throws SQLException, ClassNotFoundException;
}
