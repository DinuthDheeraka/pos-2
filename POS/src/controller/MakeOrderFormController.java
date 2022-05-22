package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailBO;
import bo.custom.OrdersBO;
import bo.custom.impl.CustomerBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import util.IdsGenerator;
import view.tdm.CartTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
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
    public Label lblOrderId;
    public JFXButton addToCartBtn;
    public JFXButton placeOrderBtn;

    private String cartItemCode;
    private String cartDescription;
    private double cartUnitPrice;
    private int cartQty;
    private double cartDiscount;
    private double cartTotal;

    private String selectedCustomerId;
    private String selectedItemCode;
    private double selectedItemUnitPrice;
    ObservableList<CartTM> cartItems = FXCollections.observableArrayList();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);
    OrdersBO ordersBO = (OrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ORDERBO_IMPL);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ORDERDETAILBO_IMPL);

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

        addToCartBtn.setDisable(true);
        placeOrderBtn.setDisable(true);

        try {
            lblOrderId.setText(IdsGenerator.generateId("O-",ordersBO.getLastOrderId()));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

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
            if(cartItems.size()>0){
                placeOrderBtn.setDisable(false);
            }
            CustomerDTO customerDTO = customerBO.getCustomer(selectedCustId);
            txtCustName.setText(customerDTO.getCustName());
            txtCustAddress.setText(customerDTO.getCustAddress());
            txtCustCity.setText(customerDTO.getCity());
            txtCustProvince.setText(customerDTO.getProvince());
            this.selectedCustomerId = selectedCustId;
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
        if(selectedCustomerId!=null){
            placeOrderBtn.setDisable(false);
        }
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
            itemBO.increasetItemQOH(cartItemCode,cartQty);
            setItemDataToTextFileds(selectedItemCode);
            removeItemFromCartTbl();
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeItemFromCartTbl(){
        Iterator<CartTM> iter = cartItems.iterator();
        while (iter.hasNext()) {
            CartTM cartTM = iter.next();

            if (cartTM.getItemCode().equals(cartItemCode))
                iter.remove();
        }
        cartTbl.setItems(cartItems);
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) {
        //Add order
        try {
            ordersBO.insertOrder(new OrdersDTO(
                   lblOrderId.getText() , LocalDate.now(),selectedCustomerId
            ));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Add Order Detail
        for(CartTM cartTM : cartItems){
            try {
                orderDetailBO.insertOrderDetail(new OrderDetailDTO(
                        lblOrderId.getText(),cartTM.getItemCode(),cartTM.getQty(),
                        cartTM.getUnitPrice(),cartTM.getDiscount()
                ));
            }
            catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void txtAmountKeyReleased(KeyEvent keyEvent) {
        if(!txtItemAmount.getText().isEmpty()){
            if((Integer.valueOf(txtItemAmount.getText())>0)&&(!txtItemAmount.getText().startsWith("-"))&&Integer.valueOf(txtItemAmount.getText())<=Integer.valueOf(txtItemQOH.getText())&&(!txtItemGivenDiscount.getText().isEmpty())){
                addToCartBtn.setDisable(false);
            }else{
                addToCartBtn.setDisable(true);
            }
        }else{
            addToCartBtn.setDisable(true);
        }
    }

    public void txtItemGivenDiscountKeyReleased(KeyEvent keyEvent) {
        if(!txtItemGivenDiscount.getText().isEmpty()){
            if((!txtItemGivenDiscount.getText().startsWith("-"))&&Double.valueOf(txtItemGivenDiscount.getText())<=Double.valueOf(txtItemMaxDiscount.getText())&&(!txtItemAmount.getText().isEmpty())){
                addToCartBtn.setDisable(false);
            }else{
                addToCartBtn.setDisable(true);
            }
        }else{
            addToCartBtn.setDisable(true);
        }
    }
}
