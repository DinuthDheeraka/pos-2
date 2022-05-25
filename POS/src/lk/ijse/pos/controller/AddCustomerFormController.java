package lk.ijse.pos.controller;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.pos.dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.util.IdsGenerator;
import lk.ijse.pos.util.NavigateUI;
import lk.ijse.pos.util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        txtCustomerId.setEditable(false);
        addCustomerSaveBtn.setDisable(true);
        try {
            txtCustomerId.setText(IdsGenerator.generateId("C-",customerBO.getCustomerLastId()));
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void canselBtnOnaction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }

    public void canselBtnOnaClick(MouseEvent mouseEvent) {
    }

    public void saveCustomerBtnOnAction(ActionEvent actionEvent) {
        if(addCustomerSaveBtn.getText().equals("SAVE CUSTOMER")){
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
        }else{
            try {
                //String custID, String cusTitle, String custName, String custAddress,String city, String province, String postalCode
                customerBO.updateCustomer(new CustomerDTO(
                       txtCustomerId.getText(),txtCustomerTitle.getText(),txtCustomerName.getText(),
                       txtCustomerAddress.getText(),txtCustomerCity.getText(),txtCustomerProvince.getText(),
                       txtCustomerPostalCode.getText()
                ));
            }
            catch (ClassNotFoundException|SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setValuesForTextFields(CustomerDTO customerDTO) {
        txtCustomerId.setText(customerDTO.getCustID());
        txtCustomerTitle.setText(customerDTO.getCusTitle());
        txtCustomerName.setText(customerDTO.getCustName());
        txtCustomerAddress.setText(customerDTO.getCustAddress());
        txtCustomerCity.setText(customerDTO.getCity());
        txtCustomerProvince.setText(customerDTO.getProvince());
        txtCustomerPostalCode.setText(customerDTO.getPostalCode());
        lblTitle.setText("UPDATE CUSTOMER");
        addCustomerSaveBtn.setText("UPDATE CUSTOMER");

    }

    public void validate(KeyEvent keyEvent) {
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
        Pattern name = Pattern.compile("[A-Za-z .]{3,}");
        map.put(txtCustomerName,name);

        Pattern title = Pattern.compile("(Mr|Mrs|Ms|Miss)");
        map.put(txtCustomerTitle,name);

        Pattern address = Pattern.compile("[A-Za-z0-9 .,/-]{5,}");
        map.put(txtCustomerAddress,address);

        Pattern city = Pattern.compile("[A-za-z ]{3,}");
        map.put(txtCustomerCity,city);

        Pattern province = Pattern.compile("(western|suthern|north)");
        map.put(txtCustomerProvince,province);

        Pattern postalCode = Pattern.compile("[0-9]{5}$");
        map.put(txtCustomerPostalCode,postalCode);


        Validator.validate(map,addCustomerSaveBtn);
    }
}
