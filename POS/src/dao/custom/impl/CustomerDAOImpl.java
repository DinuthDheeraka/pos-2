package dao.custom.impl;

import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
}
