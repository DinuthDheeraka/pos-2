package bo.custom;

import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
