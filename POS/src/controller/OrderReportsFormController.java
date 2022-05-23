package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderReportsFormController {
    public TableView itemTbl;
    public TableColumn colItemCode;
    public TableColumn colItemDescription;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQTY;
    public TableColumn colItemDiscount;
    public TextField txtSearchBar;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtOrderDate;
    public JFXTextField txtTotalCost;
    public JFXTextField txtTotalDiscount;
    public TableColumn colItemPackSize;

    public void searchBtnOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
    }
}
