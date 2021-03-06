package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import javafx.scene.control.Alert;
import lk.ijse.pos.util.CrudUtil;

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
        if(CrudUtil.execute("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,MaxDiscount=?,QOH=? WHERE ItemCode = ?",
                item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getMaxDiscount(),item.getQoh(),item.getItemCode())){

            new Alert(Alert.AlertType.CONFIRMATION,"Item Updated").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Couldn't Update Item").show();
        }
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemCodes = new ArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT ItemCode FROM Item;");
        while (resultSet.next()){
            itemCodes.add(resultSet.getString("ItemCode"));
        }
        return  itemCodes;
    }

    @Override
    public Item get(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE ItemCode = ?",itemCode);
        if(resultSet.next()){
            return new Item(
                    resultSet.getString("ItemCode"),resultSet.getString("Description"),
                    resultSet.getString("PackSize"),resultSet.getDouble("UnitPrice"),
                    resultSet.getDouble("MaxDiscount"),resultSet.getInt("QOH")
            );
        }
        return null;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1");
        return resultSet.next()? resultSet.getString("ItemCode") : null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(ItemCode) FROM Item;");
        return resultSet.next()? resultSet.getInt(1) : 0;
    }

    @Override
    public void subtractItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE Item SET QOH = QOH-? WHERE ItemCode = ?",amount,itemCode)){
        }
    }

    @Override
    public void increasetItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE Item SET QOH = QOH+? WHERE ItemCode = ?",amount,itemCode)){
        }
    }

    @Override
    public double getItemsBelowFiftyUnits() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(ItemCode) FROM item WHERE QOH<=50;");
        return resultSet.next()? resultSet.getDouble(1):0;
    }

    @Override
    public double getItemsBetweenFiftyAndOneFiftyUnits() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(ItemCode) FROM item WHERE QOH BETWEEN 50 AND 150;");
        return resultSet.next()? resultSet.getDouble(1):0;
    }

    @Override
    public double getItemsThatHaveMorethanOneFiftyUnits() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(ItemCode) FROM item WHERE QOH>=150;");
        return resultSet.next()? resultSet.getDouble(1):0;
    }
}
