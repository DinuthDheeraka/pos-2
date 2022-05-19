package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.NavigateUI;

import java.io.IOException;

public class ItemFormController {
    public TableView itemTbl;
    public TableColumn colItemCode;
    public TableColumn colItemDesc;
    public TableColumn colItemQOH;
    public TableColumn colItemUnitPrice;

    public void addNewItemOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Item-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
