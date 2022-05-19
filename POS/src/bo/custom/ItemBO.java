package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems();
}
