package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.NavigateUI;
import view.tdm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TableView<ItemTM> itemTbl;
    public TableColumn colItemCode;
    public TableColumn colItemDesc;
    public TableColumn colItemQOH;
    public TableColumn colItemUnitPrice;
    public TableColumn colIteMaxDiscount;
    public TableColumn colItemPackSize;
    public TableColumn colItemAddedDate;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colItemQOH.setCellValueFactory(new PropertyValueFactory("qoh"));
        colIteMaxDiscount.setCellValueFactory(new PropertyValueFactory("maxDiscount"));
        colItemPackSize.setCellValueFactory(new PropertyValueFactory("packSize"));
        colItemAddedDate.setCellValueFactory(new PropertyValueFactory("addedDate"));
        loadAllItems();
    }

    private void loadAllItems() {
        try {
            ArrayList<ItemDTO> itemDTOS = itemBO.getAllItems();
            ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();
            for(ItemDTO dto : itemDTOS){
                itemTMS.add(new ItemTM(
                        dto.getItemCode(), dto.getDescription(), dto.getPackSize(),
                        dto.getUnitPrice(),dto.getMaxDiscount(),dto.getQoh(),
                        dto.getAddedDate()
                ));
            }
            itemTbl.setItems(itemTMS);
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewItemOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Item-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
