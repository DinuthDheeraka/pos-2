package lk.ijse.pos.controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.OrdersBO;
import lk.ijse.pos.dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.pos.util.NavigateUI;
import lk.ijse.pos.view.tdm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public TableView<CustomerTM> customerTbl;
    public TableColumn colCustId;
    public TableColumn colCustTitle;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustCity;
    public TableColumn colCustProvince;
    public TableColumn colCustPostalCode;
    public TableColumn colCustJoinedDate;
    public TextField txtCustSearchBar;
    public Label lblCustCount;
    public TextField txtCustomerOrderSearchBar;
    public LineChart customerOrderChart;
    public Label lblMonth;
    public Label lblNoOfOrdersForMonth;
    public Label lblTotalOrders;
    public Label lblTodayOrders;
    public TextField txtCustomerGrowthSearchBar;
    public LineChart customerGrowthChart;
    public Label lblCustMonth;
    public Label lblNoOfCustomers;
    public Label lblTotalCustomers;
    public Label lblTodayNewCustomers;
    public TextField txtCustomerOrderYearSearchBar;

    private String selectedCustID;
    private String selectedCusTitle;
    private String selectedCustName;
    private String selectedCustAddress;
    private String selectedCity;
    private String selectedProvince;
    private String selectedPostalCode;
    private LocalDate selectedJoinedDate;

    private Parent parent;
    private Scene scene;
    private Stage stage;

    private String thisYear;
    private String lastYear;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BO.CUSTOMERBO_IMPL);
    OrdersBO ordersBO = (OrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BO.ORDERBO_IMPL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory("cusTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory("custName"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory("custAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory("province"));
        colCustPostalCode.setCellValueFactory(new PropertyValueFactory("postalCode"));
        colCustJoinedDate.setCellValueFactory(new PropertyValueFactory("joinedDate"));

        customerTbl.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue!=null)setSelectedCustomerData(newValue);
                });

        loadAllCustomers();

        this.thisYear = String.valueOf(LocalDate.now().getYear());
        this.lastYear = String.valueOf(Integer.valueOf(thisYear)-1);

        setCustomerGrowthData();
    }

    private void setCustomerGrowthData() {
        try {
            lblTotalCustomers.setText(String.format("%02d",customerBO.getCustomerCount()));
            lblTodayNewCustomers.setText(String.format("%02d",customerBO.getCustomerCountByMonth(String.valueOf(LocalDate.now()))));
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setSelectedCustomerData(CustomerTM newValue) {
        selectedCustID = newValue.getCustID();
        selectedCusTitle = newValue.getCusTitle();
        selectedCustName = newValue.getCustName();
        selectedCustAddress = newValue.getCustAddress();
        selectedCity = newValue.getCity();
        selectedProvince = newValue.getProvince();
        selectedPostalCode = newValue.getPostalCode();
        selectedJoinedDate = newValue.getJoinedDate();
    }

    private void loadAllCustomers() {
        try {
            ArrayList<CustomerDTO> customerDTOS = customerBO.getAllCustomers();
            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
            for(CustomerDTO customerDTO : customerDTOS){
                customerTMS.add(new CustomerTM(
                        customerDTO.getCustID(),customerDTO.getCusTitle(),
                        customerDTO.getCustName(),customerDTO.getCustAddress(),
                        customerDTO.getCity(),customerDTO.getProvince(),
                        customerDTO.getPostalCode(),customerDTO.getJoinedDate()
                ));
            }
            customerTbl.setItems(customerTMS);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().setNewStage("Add-Customer-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshCtxmOnAction(ActionEvent actionEvent) {
        loadAllCustomers();
    }

    public void deleteCtxmOnAction(ActionEvent actionEvent) {
        try {
            customerBO.deleteCustomer(selectedCustID);
            loadAllCustomers();
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCtxmOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Add-Customer-Form.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddCustomerFormController controller = fxmlLoader.getController();
        controller.setValuesForTextFields(
                new CustomerDTO(
                selectedCustID,selectedCusTitle,selectedCustName,selectedCustAddress,
                selectedCity,selectedProvince,selectedPostalCode
        ));

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        NavigateUI.getNavigateUI().transparentUi(stage,scene);
    }

    public void customerSearchBar(ActionEvent actionEvent) {
        if(!txtCustSearchBar.getText().isEmpty()){
            customerTbl.getItems().clear();
            try {
                CustomerDTO customerDTO = customerBO.getCustomer(txtCustSearchBar.getText());
                ObservableList<CustomerTM> customer = FXCollections.observableArrayList(
                        new CustomerTM(
                                customerDTO.getCustID(),customerDTO.getCusTitle(),customerDTO.getCustName(),
                                customerDTO.getCustAddress(),customerDTO.getCity(),customerDTO.getProvince(),
                                customerDTO.getPostalCode(),customerDTO.getJoinedDate()
                        )
                );
                customerTbl.setItems(customer);
            }
            catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void txtCustomerOrderSearchBarOnAction(ActionEvent actionEvent) {
        setCustomerAnalyzedData(txtCustomerOrderSearchBar.getText());
    }

    public void customerOrderSearcgBtnOnAction(ActionEvent actionEvent) {
        setCustomerAnalyzedData(txtCustomerOrderSearchBar.getText());
    }

    public void setCustomerAnalyzedData(String customerId){
        try {
            lblTotalOrders.setText(String.valueOf(ordersBO.getOrderCountByCustomerId(customerId)));
            lblTodayOrders.setText(String.valueOf(ordersBO.getCustomerOrderCountByDate(customerId,String.valueOf(LocalDate.now()))));
            setDataToCustomerOrderChart(customerId,thisYear,lastYear);
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToCustomerOrderChart(String customerId,String thisYear,String lastYear) {
       // chart3.setTitle("Orders Count of this and last year");

        XYChart.Series<String,Double> thisYearOrderCount = new XYChart.Series();
        XYChart.Series<String,Double> lastYearOrderCount = new XYChart.Series();

        thisYearOrderCount.setName("This year orders");
        lastYearOrderCount.setName("Last year orders");

        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        for(int i = 1; i<=12; i++){
            try {
                double thisYearIncome = ordersBO.getCustomerOrderCountByDate(customerId,getMonthLikeValue(thisYear,i));
                thisYearOrderCount.getData().add(new XYChart.Data(months[i-1],thisYearIncome));

                double lastYearIncome = ordersBO.getCustomerOrderCountByDate(customerId,getMonthLikeValue(lastYear,i));
                lastYearOrderCount.getData().add(new XYChart.Data(months[i-1],lastYearIncome));
            }
            catch (SQLException |ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        customerOrderChart.getData().clear();
        customerOrderChart.getData().add(lastYearOrderCount);
        customerOrderChart.getData().add(thisYearOrderCount);
    }

    private String getMonthLikeValue(String year,int month) {
        String val =  month<10? year+"-"+0+month+"%" : year+"-"+month+"%";
        return val;
    }

    public void customerOrderYearSearchBtnOnAction(ActionEvent actionEvent) {
        if(!txtCustomerOrderSearchBar.getText().isEmpty()){
            setDataToCustomerOrderChart(
                    txtCustomerOrderSearchBar.getText(),
                    txtCustomerOrderYearSearchBar.getText(),
                    String.valueOf(Integer.valueOf(txtCustomerOrderYearSearchBar.getText())-1));
        }
    }

    public void txtCustomerOrderYearSearchBarOnAction(ActionEvent actionEvent) {
        if(!txtCustomerOrderSearchBar.getText().isEmpty()){
            setDataToCustomerOrderChart(
                    txtCustomerOrderSearchBar.getText(),
                    txtCustomerOrderYearSearchBar.getText(),
                    String.valueOf(Integer.valueOf(txtCustomerOrderYearSearchBar.getText())-1));
        }
    }

    public void txtCustomerGrowthSearchBarOnAction(ActionEvent actionEvent) {
        setDataToCustomerGrowthChart(txtCustomerGrowthSearchBar.getText(),
                String.valueOf(Integer.valueOf(txtCustomerGrowthSearchBar.getText())-1));
    }

    public void customerGrowthOnAction(ActionEvent actionEvent) {
        setDataToCustomerGrowthChart(txtCustomerGrowthSearchBar.getText(),
                String.valueOf(Integer.valueOf(txtCustomerGrowthSearchBar.getText())-1));
    }

    private void setDataToCustomerGrowthChart(String year,String lastYear) {

        customerGrowthChart.setTitle("Growth of members in this year and last year");

        XYChart.Series thisYearCustGrowthChart = new XYChart.Series();
        XYChart.Series lastYearCustGrowthChart = new XYChart.Series();

        thisYearCustGrowthChart.setName("New customers for each month in this year");
        lastYearCustGrowthChart.setName("New customers for each month in last year");

        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

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

        customerGrowthChart.getData().clear();
        customerGrowthChart.getData().add(thisYearCustGrowthChart);
        customerGrowthChart.getData().add(lastYearCustGrowthChart);
    }
}
