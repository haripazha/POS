package com.example.acer.jm_pos.reports.item_inventory.generate_inventoryBalance;

import java.util.ArrayList;

public interface generate_invBalanceContract {
    interface invBalance_view{
        void populateInventoryItem(String inventory_id,
                                   String inventory_item_name,
                                   String inventory_stock,
                                   String inventory_desc,
                                   String inventory_price);
    }
    interface invBalance_presenter{
        void get_inventoryBalance();
    }
}
