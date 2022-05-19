package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import util.NavigateUI;

public class AddCustomerFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public Label lblTitle;
    public JFXButton addCustomerSaveBtn;

    public void canselBtnOnaction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }

    public void canselBtnOnaClick(MouseEvent mouseEvent) {
    }
}
