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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.pos.bo.custom.JoinQueryBO;
import lk.ijse.pos.bo.custom.impl.JoinQueryBOImpl;

import java.net.URL;
import java.sql.SQLException;
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
        setDataToIncomeChart(this.year);
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
        setDataToIncomeChart(txtSearchBar.getText());
    }

    public void setDataToIncomeChart(String year){
        XYChart.Series incomeChartSeries = new XYChart.Series();
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        for(int i = 1; i<=12; i++){
            try {
                double thisYearIncome = joinQueryBO.getIncomeByYearForEachMonth(getMonthLikeValue(year,i));
                incomeChartSeries.getData().add(new XYChart.Data<>(months[i-1],thisYearIncome));
            }
            catch (SQLException |ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        incomeChart.getData().add(incomeChartSeries);
    }

    private String getMonthLikeValue(String year,int month) {
        String val =  month<10? year+"-"+0+month+"%" : year+"-"+month+"%";
        return val;
    }
}
