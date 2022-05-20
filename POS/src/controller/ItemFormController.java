package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.NavigateUI;
import view.tdm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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

    private String selectedItemCode;
    private String selectedDescription;
    private String selectedPackSize;
    private double selectedUnitPrice;
    private double selectedMaxDiscount;
    private int selectedQOH;
    private LocalDate selectedAddedDate;

    private Parent parent;
    private Scene scene;
    private Stage stage;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colItemQOH.setCellValueFactory(new PropertyValueFactory("qoh"));
        colIteMaxDiscount.setCellValueFactory(new PropertyValueFactory("maxDiscount"));
        colItemPackSize.setCellValueFactory(new PropertyValueFactory("packSize"));
        colItemAddedDate.setCellValueFactory(new PropertyValueFactory("addedDate"));

        itemTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedItemData(newValue);
                });

        loadAllItems();
    }

    private void setSelectedItemData(ItemTM newValue) {
        selectedItemCode = newValue.getItemCode();
        selectedDescription = newValue.getDescription();
        selectedPackSize = newValue.getPackSize();
        selectedUnitPrice = newValue.getUnitPrice();
        selectedMaxDiscount = newValue.getMaxDiscount();
        selectedQOH = newValue.getQoh();
        selectedAddedDate = newValue.getAddedDate();
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

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
        try {
            itemBO.deleteItem(selectedItemCode);
            loadAllItems();
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        loadAllItems();
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Add-Item-Form.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddItemFormController controller = fxmlLoader.getController();
        controller.setValuesForTextFields(new ItemDTO(
                selectedItemCode,selectedDescription,selectedPackSize,
                selectedUnitPrice,selectedMaxDiscount, selectedQOH
        ));

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        NavigateUI.getNavigateUI().transparentUi(stage,scene);
    }
}
