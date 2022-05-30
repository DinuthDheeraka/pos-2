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
import lk.ijse.pos.view.jaspertm.IncomeJTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class IncomeReportsFormController implements Initializable {

    public TextField txtSearchBar;
    public Label txtTotalIncome;
    public Label txtTotalDiscount;
    public Label txtMonth;
    public Label txtMonthlyIncome;
    public LineChart incomeChart;
    public Label txtTodayIncome;

    private String year;

    JoinQueryBO joinQueryBO = new JoinQueryBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        year = String.valueOf(LocalDate.now().getYear());
        setDataToIncomeChart(this.year);
        setAnnualIncomeAndDiscount(this.year);
        setTodayIncome();
    }

    private void setTodayIncome() {
        try {
            txtTodayIncome.setText(String.valueOf(joinQueryBO.getTotalIncomeByYear(String.valueOf(LocalDate.now()))));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
        if(!txtSearchBar.getText().isEmpty()){
            incomeChart.getData().clear();
            setDataToIncomeChart(txtSearchBar.getText());
        }
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

        setAnnualIncomeAndDiscount(year);
    }

    public void setAnnualIncomeAndDiscount(String year){
        try {
            double discount = joinQueryBO.getDiscountByYear(year+"%");
            double income = joinQueryBO.getTotalIncomeByYear(year+"%");

            txtTotalDiscount.setText(String.valueOf(discount));
            txtTotalIncome.setText(String.valueOf(income));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getMonthLikeValue(String year,int month) {
        String val =  month<10? year+"-"+0+month+"%" : year+"-"+month+"%";
        return val;
    }

    public void viewReportBtnOnAction(ActionEvent actionEvent) throws JRException {

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/pos/view/reports/MemberSchedule.jasper"));

        JasperPrint print = JasperFillManager.fillReport(jasperReport,null,new JRBeanCollectionDataSource(null));

        JasperViewer.viewReport(print,false);
    }
}
