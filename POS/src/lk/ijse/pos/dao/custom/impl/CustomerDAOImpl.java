package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import javafx.scene.control.Alert;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

//// CustID      | varchar(6)  | NO   | PRI | NULL    |       |
//    //| CusTitle    | varchar(5)  | YES  |     | NULL    |       |
//    //| CustName    | varchar(40) | YES  |     | NULL    |       |
//    //| CustAddress | varchar(40) | YES  |     | NULL    |       |
//    //| City        | varchar(20) | YES  |     | NULL    |       |
//    //| Province    | varchar(20) | YES  |     | NULL    |       |
//    //| PostalCode  | varchar(9)  | YES  |     | NULL    |       |
//    //| JoinedDate

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer;");
        while (resultSet.next()){
            customers.add(new Customer(
                    resultSet.getString("CustID"),resultSet.getString("CusTitle"),
                    resultSet.getString("CustName"),resultSet.getString("CustAddress"),
                    resultSet.getString("City"),resultSet.getString("Province"),
                    resultSet.getString("PostalCode"),
                    LocalDate.parse(String.valueOf(resultSet.getDate("JoinedDate")))
            ));
        }
        return customers;
    }

    @Override
    public void insert(Customer customer) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)",
                customer.getCustID(),customer.getCusTitle(),customer.getCustName(),
                customer.getCustAddress(),customer.getCity(),customer.getProvince(),
                customer.getPostalCode(),customer.getJoinedDate())){

            new Alert(Alert.AlertType.CONFIRMATION,"Added Customer").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Add Customer").show();
        }
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("DELETE FROM Customer WHERE CustID = ?",id)){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Delete Customer").show();
        }
    }

    @Override
    public void update(Customer customer) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE Customer SET CusTitle=?,CustName=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID = ?",
                customer.getCusTitle(),customer.getCustName(),customer.getCustAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode(),customer.getCustID())){

            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Update Customer").show();
        }
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> custIds = new ArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT CustId FROM Customer;");
        while (resultSet.next()){
            custIds.add(resultSet.getString("CustId"));
        }
        return  custIds;
    }

    @Override
    public Customer get(String custId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE CustId = ?",custId);
        if(resultSet.next()){
            return new Customer(
                    resultSet.getString("CustId"),resultSet.getString("CusTitle"),
                    resultSet.getString("CustName"),resultSet.getString("CustAddress"),
                    resultSet.getString("City"),resultSet.getString("Province"),
                    resultSet.getString("PostalCode")
            );
        }
        return null;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT CustID FROM Customer ORDER BY CustID DESC LIMIT 1");
        return resultSet.next()? resultSet.getString("CustID") : null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(CustID) FROM Customer;");
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public int getCustomerCountByMonth(String month) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(CustID) FROM customer WHERE JoinedDate LIKE ?",month);
        return resultSet.next()? resultSet.getInt(1) : 0;
    }
}
