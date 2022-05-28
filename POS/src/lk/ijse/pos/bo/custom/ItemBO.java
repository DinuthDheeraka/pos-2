package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    void insertItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    void deleteItem(String itemCode) throws SQLException,ClassNotFoundException;
    void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    ItemDTO getItem(String itemCode) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    void subtractItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
    void increasetItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException;
    String getItemLastId() throws SQLException, ClassNotFoundException;
    int getItemsCount() throws SQLException, ClassNotFoundException;
}
