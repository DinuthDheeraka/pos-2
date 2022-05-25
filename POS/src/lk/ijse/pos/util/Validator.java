/**
 * @author : Dinuth Dheeraka
 * Project Name: POS
 * Date        : 5/25/2022
 * Time        : 2:17 PM
 */
package lk.ijse.pos.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validator {

    public static void validate(LinkedHashMap<TextField,Pattern> map, JFXButton btn) {
        boolean isDisale = false;
        for(TextField textField : map.keySet()){
            Pattern pattern = map.get(textField);
            if((!textField.getText().isEmpty())){
                if((!pattern.matcher(textField.getText()).matches())){
                    textField.setStyle("-fx-text-fill: red");
                    isDisale=true;
                }else{
                    textField.setStyle("-fx-text-fill: white");
                }
            }else{
                isDisale = true;
            }
        }
        disableOrEnablebtn(isDisale,btn);
    }

    public static void disableOrEnablebtn(boolean isDisale,JFXButton btn){
        if(isDisale){
            btn.setDisable(true);
        }else{
            btn.setDisable(false);
        }
    }
}
