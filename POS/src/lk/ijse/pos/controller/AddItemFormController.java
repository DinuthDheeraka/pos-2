package lk.ijse.pos.controller;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.pos.dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.pos.util.IdsGenerator;
import lk.ijse.pos.util.NavigateUI;
import lk.ijse.pos.util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        addItemBtn.setDisable(true);
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

    public void validate(KeyEvent keyEvent) {
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

        Pattern description = Pattern.compile("[A-Za-z0-9 ,-,(,)/.]{1,}");
        map.put(txtItemDescription,description);

        Pattern packSize = Pattern.compile("[0-9]{1,}.[0-9]{2,3}(Kg|g|ml|l)");
        map.put(txtItemPackSize,packSize);

        Pattern unitPrice = Pattern.compile("[0-9]{1,}.[0-9]{2}");
        map.put(txtItemUnitPrice,unitPrice);

        Pattern maxDiscount = Pattern.compile("[0-9]{1,}.[0-9]{2}");
        map.put(txtItemMaxDiscount,maxDiscount);

        Pattern qoh = Pattern.compile("[0-9]{1,}");
        map.put(txtItemQOH,qoh);

        Validator.validate(map,addItemBtn);
    }
}
