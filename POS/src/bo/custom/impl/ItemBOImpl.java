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
}
