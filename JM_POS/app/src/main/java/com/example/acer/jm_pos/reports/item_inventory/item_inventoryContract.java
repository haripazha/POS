package com.example.acer.jm_pos.reports.item_inventory;

public interface item_inventoryContract {
    interface item_inventoryView{

    }
    interface item_inventoryPresenter
    {
        void startAndEnd_validationAlert();
        void generate_inventoryBalance();
    }

}
