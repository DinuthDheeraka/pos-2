package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.IdsGenerator;
import util.NavigateUI;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
        txtItemCode.setEditable(false);
        try {
            txtItemCode.setText(IdsGenerator.generateId("I-",itemBO.getItemLastId()));
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void canselBtnOnAction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }

    public void addItemBtnOnAction(ActionEvent actionEvent) {
        if(addItemBtn.getText().equals("ADD ITEM")){
            try {
                itemBO.insertItem(new ItemDTO(
                        txtItemCode.getText(),txtItemDescription.getText(),txtItemPackSize.getText(),
                        Double.valueOf(txtItemUnitPrice.getText()),Double.valueOf(txtItemMaxDiscount.getText()),
                        Integer.valueOf(txtItemQOH.getText()), LocalDate.now()
                ));

                txtItemCode.setText(IdsGenerator.generateId("I-",itemBO.getItemLastId()));
                txtItemDescription.clear();
                txtItemQOH.clear();
                txtItemMaxDiscount.clear();
                txtItemPackSize.clear();
                txtItemUnitPrice.clear();
            }
            catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                //(String itemCode, String description, String packSize, double unitPrice, double maxDiscount, int qoh)
                itemBO.updateItem(new ItemDTO(
                        txtItemCode.getText(),txtItemDescription.getText(),txtItemPackSize.getText(),
                        Double.valueOf(txtItemUnitPrice.getText()),Double.valueOf(txtItemMaxDiscount.getText()),
                        Integer.valueOf(txtItemQOH.getText())
                ));
            }
            catch (ClassNotFoundException|SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setValuesForTextFields(ItemDTO dto) {
        txtItemCode.setText(dto.getItemCode());
        txtItemDescription.setText(dto.getDescription());
        txtItemUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtItemQOH.setText(String.valueOf(dto.getQoh()));
        txtItemMaxDiscount.setText(String.valueOf(dto.getMaxDiscount()));
        txtItemPackSize.setText(dto.getPackSize());
        lblTitle.setText("UPDATE ITEM");
        addItemBtn.setText("UPDATE ITEM");
    }
}
