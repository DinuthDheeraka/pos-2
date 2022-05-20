package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Item;
import javafx.scene.control.Alert;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item;");
        while (resultSet.next()){
            items.add(new Item(
                    resultSet.getString("itemCode"),resultSet.getString("description"),
                    resultSet.getString("packSize"),resultSet.getDouble("unitPrice"),
                    resultSet.getDouble("maxDiscount"),resultSet.getInt("qoh"),
                    LocalDate.parse(String.valueOf(resultSet.getDate("addedDate")))
            ));
        }
        return items;
    }

    @Override
    public void insert(Item item) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("INSERT INTO Item VALUES(?,?,?,?,?,?,?)",
                item.getItemCode(),item.getDescription(),item.getPackSize(),
                item.getUnitPrice(),item.getMaxDiscount(),item.getQoh(),
                item.getAddedDate())){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Item").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't add Item").show();
        }
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("DELETE FROM Item WHERE ItemCode = ?",id)){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Delete Item").show();
        }
    }

    @Override
    //ItemCode | Description   | PackSize | UnitPrice | MaxDiscount | QOH  | AddedDate
    public void update(Item item) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,MaxDiscount=?,QOH=?,AddedDate=? WHERE ItemCode = ?",
                item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getMaxDiscount(),item.getQoh(),item.getAddedDate())){

            new Alert(Alert.AlertType.CONFIRMATION,"Item Updated").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Update Item").show();
        }
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1");
        return resultSet.next()? resultSet.getString("ItemCode") : null;
    }
}
