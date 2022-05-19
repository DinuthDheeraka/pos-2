package controller;

import javafx.event.ActionEvent;
import util.NavigateUI;

public class AddItemFormController {
    public void canselBtnOnAction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
    }
}
