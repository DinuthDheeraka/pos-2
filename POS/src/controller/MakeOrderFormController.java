package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.impl.CustomerBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tdm.CartTM;

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

    public TableView<CartTM> cartTbl;
    public TableColumn colItemCode;
    public TableColumn colItemDescription;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQTY;
    public TableColumn colItemDiscount;
    public TableColumn colItemTotal;
    public Label lblTotal;
    public Label lblTotalDiscount;

    private String cartItemCode;
    private String cartDescription;
    private double cartUnitPrice;
    private int cartQty;
    private double cartDiscount;
    private double cartTotal;

    private String selectedItemCode;
    private double selectedItemUnitPrice;
    ObservableList<CartTM> cartItems = FXCollections.observableArrayList();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colItemQTY.setCellValueFactory(new PropertyValueFactory("qty"));
        colItemDiscount.setCellValueFactory(new PropertyValueFactory("discount"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory("total"));
        addItemCodes();
        addCustomerIds();

        cmbxCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setCustomerDataToTextFileds((String) newValue);
                    }
                }
        );

        cmbxItemCodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                {
                    if (newValue!=null){
                        setItemDataToTextFileds((String) newValue);
                    }
                }
        );

        cartTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedCartData(newValue);
                });
    }

    private void setSelectedCartData(CartTM newValue) {
        cartItemCode = newValue.getItemCode();
        cartDescription = newValue.getDescription();
        cartUnitPrice = newValue.getUnitPrice();
        cartDiscount = newValue.getDiscount();
        cartTotal = newValue.getTotal();
        cartQty = newValue.getQty();
    }

    private void setItemDataToTextFileds(String selectedItemCode) {
        try {
            ItemDTO itemDTO = itemBO.getItem(selectedItemCode);
            txtItemDescription.setText(itemDTO.getDescription());
            txtItemPackSize.setText(itemDTO.getPackSize());
            txtItemUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtItemMaxDiscount.setText(String.valueOf(itemDTO.getMaxDiscount()));
            txtItemQOH.setText(String.valueOf(itemDTO.getQoh()));
            this.selectedItemCode = selectedItemCode;
            this.selectedItemUnitPrice = itemDTO.getUnitPrice();
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDataToTextFileds(String selectedCustId) {
        try {
            CustomerDTO customerDTO = customerBO.getCustomer(selectedCustId);
            txtCustName.setText(customerDTO.getCustName());
            txtCustAddress.setText(customerDTO.getCustAddress());
            txtCustCity.setText(customerDTO.getCity());
            txtCustProvince.setText(customerDTO.getProvince());
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
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

    public void addToCartBtnOnAction(ActionEvent actionEvent) {
        if(getExistsCartTM()!=null){
            CartTM cartTM = getExistsCartTM();
            cartTM.setQty(cartTM.getQty()+Integer.valueOf(txtItemAmount.getText()));
            cartTM.setDiscount(cartTM.getDiscount()+(Double.valueOf(txtItemGivenDiscount.getText())*Integer.valueOf(txtItemAmount.getText())));
            cartTM.setTotal(cartTM.getTotal()+(this.selectedItemUnitPrice*Double.valueOf(txtItemAmount.getText()))- (Double.valueOf(txtItemGivenDiscount.getText())*Double.valueOf(txtItemAmount.getText())));
        }else{
            cartItems.add(new CartTM(
                    selectedItemCode,txtItemDescription.getText(),
                    Double.valueOf(txtItemUnitPrice.getText()),Integer.valueOf(txtItemAmount.getText()),
                    Double.valueOf(txtItemGivenDiscount.getText())*Integer.valueOf(txtItemAmount.getText()),
                    (this.selectedItemUnitPrice*Double.valueOf(txtItemAmount.getText()))- (Double.valueOf(txtItemGivenDiscount.getText())*Double.valueOf(txtItemAmount.getText()))
            ));
            cartTbl.setItems(cartItems);
        }

        try {
            itemBO.subtractItemQOH(selectedItemCode,Integer.valueOf(txtItemAmount.getText()));
            setItemDataToTextFileds(selectedItemCode);
        }catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

        cartTbl.refresh();
        updateTotal();
        updateDiscount();
    }

    private void updateDiscount() {
        double discount = 0;
        for (CartTM cartTM : cartItems){
            discount+=cartTM.getDiscount();
        }
        lblTotalDiscount.setText(String.valueOf(discount));
    }

    private void updateTotal() {
        double total = 0;
        for (CartTM cartTM : cartItems){
            total+=cartTM.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private CartTM getExistsCartTM(){
        for(CartTM cartTM : cartItems){
            if(cartTM.getItemCode().equals(selectedItemCode)){
                return cartTM;
            }
        }
        return null;
    }

    public void ctxmRemoveItemOnAction(ActionEvent actionEvent) {
        try {
            itemBO.subtractItemQOH(cartItemCode,cartQty);
            setItemDataToTextFileds(selectedItemCode);
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
