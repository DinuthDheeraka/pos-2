package controller;

import bo.BOFactory;
import bo.custom.JoinQueryBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tdm.OrderReportTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderReportsFormController implements Initializable {
    public TableView<OrderReportTM> itemTbl;
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

    ObservableList<OrderReportTM> orderReportTMS = FXCollections.observableArrayList();

    JoinQueryBO joinQueryBO = (JoinQueryBO) BOFactory.getBoFactory().getBO(BOFactory.BO.JOINQUERYBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colItemPackSize.setCellValueFactory(new PropertyValueFactory("packSize"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colItemQTY.setCellValueFactory(new PropertyValueFactory("qty"));
        colItemDiscount.setCellValueFactory(new PropertyValueFactory("discount"));
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<CustomDTO> customDTOS = joinQueryBO.getOrderDetailByOrderId(txtSearchBar.getText());
            for(CustomDTO customDTO : customDTOS){
                orderReportTMS.add(new OrderReportTM(
                        customDTO.getOrderDetailitemCode(),customDTO.getDescription(),
                        customDTO.getOrderDetailunitPrice(),customDTO.getOrderDetailorderQTY(),
                        customDTO.getOrderDetaildiscount(),customDTO.getPackSize()

                ));
            }
            itemTbl.setItems(orderReportTMS);
            setTotalAndDiscount();
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTotalAndDiscount(){
        double total = 0;
        double discount = 0;

        for(OrderReportTM orderReportTM : orderReportTMS){
            total+=(orderReportTM.getUnitPrice()*orderReportTM.getQty());
            discount+=(orderReportTM.getDiscount()*orderReportTM.getQty());
        }

        txtTotalCost.setText(String.valueOf(total-discount));
        txtTotalDiscount.setText(String.valueOf(discount));
    }
}
