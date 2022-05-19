package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.NavigateUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TableView itemTbl;
    public TableColumn colItemCode;
    public TableColumn colItemDesc;
    public TableColumn colItemQOH;
    public TableColumn colItemUnitPrice;
    public TableColumn colIteMaxDiscount;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllItems();
    }

    private void loadAllItems() {
    }

    public void addNewItemOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Item-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
