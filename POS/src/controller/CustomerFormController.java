package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.NavigateUI;
import view.tdm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public TableView<CustomerTM> customerTbl;
    public TableColumn colCustId;
    public TableColumn colCustTitle;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustCity;
    public TableColumn colCustProvince;
    public TableColumn colCustPostalCode;
    public TableColumn colCustJoinedDate;
    public TextField txtCustSearchBar;
    public Label lblCustCount;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory("cusTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory("custName"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory("custAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colCustPostalCode.setCellValueFactory(new PropertyValueFactory("postalCode"));
        colCustJoinedDate.setCellValueFactory(new PropertyValueFactory("joinedDate"));

        loadAllCustomers();
    }

    private void loadAllCustomers() {
        try {
            ArrayList<CustomerDTO> customerDTOS = customerBO.getAllCustomers();
            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
            for(CustomerDTO customerDTO : customerDTOS){
                customerTMS.add(new CustomerTM(
                        customerDTO.getCustID(),customerDTO.getCusTitle(),
                        customerDTO.getCustName(),customerDTO.getCustAddress(),
                        customerDTO.getCity(),customerDTO.getProvince(),
                        customerDTO.getPostalCode(),customerDTO.getJoinedDate()
                ));
            }
            customerTbl.setItems(customerTMS);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Customer-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
