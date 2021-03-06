package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAO.ITEM_IMPL);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList();
        for(Item item : items){
            itemDTOS.add(new ItemDTO(
                    item.getItemCode(),item.getDescription(), item.getPackSize(),
                    item.getUnitPrice(), item.getMaxDiscount(), item.getQoh(),
                    item.getAddedDate()
            ));
        }
        return itemDTOS;
    }

    @Override
    public void insertItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.insert(new Item(
                dto.getItemCode(),dto.getDescription(),dto.getPackSize(),
                dto.getUnitPrice(),dto.getMaxDiscount(),dto.getQoh(),
                dto.getAddedDate()
        ));
    }

    @Override
    public void deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        itemDAO.delete(itemCode);
    }

    @Override
    public void updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        itemDAO.update(new Item(
                dto.getItemCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrice(),
                dto.getMaxDiscount(),dto.getQoh(),dto.getAddedDate()
        ));
    }

    @Override
    public ItemDTO getItem(String itemCode) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.get(itemCode);
        return new ItemDTO(
                item.getItemCode(),item.getDescription(),item.getPackSize(),
                item.getUnitPrice(),item.getMaxDiscount(),item.getQoh()
        );
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemCodes = itemDAO.getAllIds();
        return itemCodes;
    }

    @Override
    public void subtractItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException {
        itemDAO.subtractItemQOH(itemCode,amount);
    }

    @Override
    public void increasetItemQOH(String itemCode,int amount) throws SQLException, ClassNotFoundException {
        itemDAO.increasetItemQOH(itemCode,amount);
    }

    @Override
    public String getItemLastId() throws SQLException, ClassNotFoundException {
        return itemDAO.getLastId();
    }

    @Override
    public int getItemsCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getCount();
    }

    @Override
    public double getItemsBelowFiftyUnits() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemsBelowFiftyUnits();
    }

    @Override
    public double getItemsBetweenFiftyAndOneFiftyUnits() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemsBetweenFiftyAndOneFiftyUnits();
    }

    @Override
    public double getItemsThatHaveMorethanOneFiftyUnits() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemsThatHaveMorethanOneFiftyUnits();
    }
}
