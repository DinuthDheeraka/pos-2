package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.impl.CustomerBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MakeOrderFormController implements Initializable {
    public JFXComboBox cmbxCustomerIds;
    public JFXComboBox cmbxItemCodes;
    public JFXTextField txtCustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustCity;
    public JFXTextField txtCustProvince;
    public JFXTextField txtItemDescription;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemPackSize;
    public JFXTextField txtItemQOH;
    public JFXTextField txtItemMaxDiscount;
    public JFXTextField txtItemGivenDiscount;
    public JFXTextField txtItemAmount;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addItemCodes();
        addCustomerIds();

        cmbxCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setCustomerDataToTextFileds((String) newValue);
                    }
                }
        );
    }

    private void setCustomerDataToTextFileds(String selectedCustId) {
        //CustomerDTO customerDTO = customerBO;
    }

    private void addCustomerIds() {
        try {
            ObservableList<String> custIds = FXCollections.observableArrayList(
                    customerBO.getAllCustomerIds()
            );
            cmbxCustomerIds.setItems(custIds);
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    private void addItemCodes() {
        try {
            ObservableList<String> itemCodes = FXCollections.observableArrayList(
                    itemBO.getAllItemIds()
            );
            cmbxItemCodes.setItems(itemCodes);
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
}
