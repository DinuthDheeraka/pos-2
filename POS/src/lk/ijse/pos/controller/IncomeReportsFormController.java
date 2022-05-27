/**
 * @author : Dinuth Dheeraka
 * Project Name: POS
 * Date        : 5/25/2022
 * Time        : 8:58 PM
 */
package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.pos.bo.custom.JoinQueryBO;
import lk.ijse.pos.bo.custom.impl.JoinQueryBOImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IncomeReportsFormController implements Initializable {

    public TextField txtSearchBar;
    public Label txtTotalIncome;
    public Label txtTotalDiscount;
    public Label txtMonth;
    public Label txtMonthlyIncome;
    public LineChart incomeChart;

    private String year;

    JoinQueryBO joinQueryBO = new JoinQueryBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        year = String.valueOf(LocalDate.now().getYear());
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {

    }
}
