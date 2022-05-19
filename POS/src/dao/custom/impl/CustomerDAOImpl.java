package dao.custom.impl;

import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import javafx.scene.control.Alert;
import util.CrudUtil;

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

        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Add Customer").show();
        }
    }
}
