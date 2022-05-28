package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.bo.custom.OrdersBO;
import lk.ijse.pos.util.NavigateUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public LineChart dbLineChart0;
    public LineChart dbLineChart2;
    public Label lblTime;
    public Label lblDate;
    public volatile boolean stop = false;
    public ImageView imgItems;
    public ImageView imgCustomers;
    public ImageView imgOrderReports;
    public ImageView imgMakeOrder;
    public ImageView imgIncomeReports;
    public AnchorPane mainFormContext;

    public JFXButton itemsBtn;
    public JFXButton customerBtn;
    public JFXButton makeOrdersBtn;
    public JFXButton orderReportBtn;
    public JFXButton incomeReportBtn;
    public LineChart chart3;
    public Label lblCustomerCount;
    public Label lblOrdersCount;
    public Label lblItemsCount;

    private String year;
    private String lastYear;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ITEMBO_IMPL);
    OrdersBO ordersBO = (OrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ORDERBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        year = String.valueOf(LocalDate.now().getYear());
        lastYear = String.valueOf(LocalDate.now().minusYears(1)).substring(0,4);
        showTime();
        lblDate.setText(String.valueOf(LocalDate.now()));
        setTestData();
        setTestData1();
        setTestData2();
        setItemCustomerOrdersCounts();
    }

    private void setItemCustomerOrdersCounts() {
        try {
            lblOrdersCount.setText(String.format("%02d+",customerBO.getCustomerCount()));
            lblItemsCount.setText(String.format("%02d+",itemBO.getItemsCount()));
            lblOrdersCount.setText(String.format("%02d+",ordersBO.getOrdersCount()));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showTime(){
        Thread thread = new Thread(()->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while(!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(timeNow);
                });
            }
        });
        thread.start();
    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().minimizeStage(actionEvent);
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        NavigateUI.getNavigateUI().closeStage(actionEvent);
        System.exit(1);
    }

    public void imgHomeOnClick(MouseEvent mouseEvent) {
        try {
            NavigateUI.getNavigateUI().closeStage(mouseEvent);
            NavigateUI.getNavigateUI().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void itemsBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Item-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void customerBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Customer-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeOrdersBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Make-Order-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void orderReportBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Order-Reports-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incomeReportBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Income-Reports-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTestData(){
        dbLineChart0.setTitle("Growth of members in this year and last year");

        XYChart.Series thisYearCustGrowthChart = new XYChart.Series();
        XYChart.Series lastYearCustGrowthChart = new XYChart.Series();

        thisYearCustGrowthChart.setName("Members count for each month in this year");
        lastYearCustGrowthChart.setName("Members count for each month last year");

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        //getting this year member growth data
        for(int i = 1; i<=12; i++){
            try {
                int customerCountThisYear = customerBO.getCustomerCountByMonth(getMonthLikeValue(year,i));
                thisYearCustGrowthChart.getData().add(new XYChart.Data<>(months[i-1],customerCountThisYear));

                int customerCountLastYear = customerBO.getCustomerCountByMonth(getMonthLikeValue(lastYear,i));
                lastYearCustGrowthChart.getData().add(new XYChart.Data<>(months[i-1],customerCountLastYear));
            }
            catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        dbLineChart0.getData().add(thisYearCustGrowthChart);
        dbLineChart0.getData().add(lastYearCustGrowthChart);
    }

    private String getMonthLikeValue(String year,int month) {
        String val =  month<10? year+"-"+0+month+"%" : year+"-"+month+"%";
        return val;
    }

    public void setTestData1(){
        //dbLineChart0.setTitle("Customers Joining Rate By Month");
        XYChart.Series s = new XYChart.Series();
        //s.setName("Member rate");

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep",
                "Oct","Nov","Dec"};

        for(int i = 12,j = 0; i>1; i--){
            s.getData().add(new XYChart.Data<>(months[j],i));
            j++;
        }

        dbLineChart2.getData().add(s);
    }

    public void setTestData2(){
        //dbLineChart0.setTitle("Customers Joining Rate By Month");
        XYChart.Series s = new XYChart.Series();
        //s.setName("Member rate");

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep",
                "Oct","Nov","Dec"};

        for(int i = 12,j = 0; i>1; i--){
            s.getData().add(new XYChart.Data<>(months[j],i));
            j++;
        }

        chart3.getData().add(s);
    }
}
