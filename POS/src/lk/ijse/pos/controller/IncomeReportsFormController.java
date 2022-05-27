/**
 * @author : Dinuth Dheeraka
 * Project Name: POS
 * Date        : 5/25/2022
 * Time        : 8:58 PM
 */
package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
        incomeChart.getData().clear();
        setDataToIncomeChart(txtSearchBar.getText());
    }

    public void setDataToIncomeChart(String year){
        incomeChart.setTitle("Income Chart for "+year);

        XYChart.Series<String,Double> incomeChartSeries = new XYChart.Series();
        incomeChartSeries.setName("Income for each month");
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        for(int i = 1; i<=12; i++){
            try {
                double thisYearIncome = joinQueryBO.getIncomeByYearForEachMonth(getMonthLikeValue(year,i));
                incomeChartSeries.getData().add(new XYChart.Data(months[i-1],thisYearIncome));
            }
            catch (SQLException |ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        incomeChart.getData().add(incomeChartSeries);

        for (XYChart.Data<String,Double> data: incomeChartSeries.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    txtMonth.setText(data.getXValue());
                    txtMonthlyIncome.setText(String.valueOf(data.getYValue()));
                }
            });
        }
    }

    private String getMonthLikeValue(String year,int month) {
        String val =  month<10? year+"-"+0+month+"%" : year+"-"+month+"%";
        return val;
    }
}
