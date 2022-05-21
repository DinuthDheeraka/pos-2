package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

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
    public void subtractItemQOH(int amount) throws SQLException, ClassNotFoundException {
        itemDAO.subtractItemQOH(amount);
    }

    @Override
    public String getItemLastId() throws SQLException, ClassNotFoundException {
        return itemDAO.getLastId();
    }
}
