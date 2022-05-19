package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import util.NavigateUI;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbLineChart0.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        dbLineChart2.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        showTime();
        lblDate.setText(String.valueOf(LocalDate.now()));
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

    public void imgItemsOnClick(MouseEvent mouseEvent) throws IOException {
        NavigateUI.getNavigateUI().addParentToCurrentStage("Item-Form",mainFormContext);
    }

    public void imgCustomerOnClick(MouseEvent mouseEvent) throws IOException {
        NavigateUI.getNavigateUI().addParentToCurrentStage("Customer-Form",mainFormContext);
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

    public void imgMakeOrderOnClick(MouseEvent mouseEvent) {
        try {
            NavigateUI.getNavigateUI().addParentToCurrentStage("Make-Order-Form",mainFormContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
