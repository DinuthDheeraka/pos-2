package controller;

import javafx.event.ActionEvent;
import util.NavigateUI;

import java.io.IOException;

public class CustomerFormController {
    public void addNewCustomerOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Customer-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
