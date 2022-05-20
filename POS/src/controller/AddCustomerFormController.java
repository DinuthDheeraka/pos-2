package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import util.NavigateUI;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public Label lblTitle;
    public JFXButton addCustomerSaveBtn;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void canselBtnOnaction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }

    public void canselBtnOnaClick(MouseEvent mouseEvent) {
    }

    public void saveCustomerBtnOnAction(ActionEvent actionEvent) {
        try {
            customerBO.insertCustomer(new CustomerDTO(
                    txtCustomerId.getText(),txtCustomerTitle.getText(),
                    txtCustomerName.getText(),txtCustomerAddress.getText(),
                    txtCustomerCity.getText(),txtCustomerProvince.getText(),
                    txtCustomerPostalCode.getText(),LocalDate.now()
            ));
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
}
