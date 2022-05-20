package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.NavigateUI;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {
    public JFXButton addItemBtn;
    public Label lblTitle;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemPackSize;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQOH;
    public JFXTextField txtItemMaxDiscount;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtItemMaxDiscount.setText(String.valueOf(0.00));
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }

    public void addItemBtnOnAction(ActionEvent actionEvent) {
    }
}
