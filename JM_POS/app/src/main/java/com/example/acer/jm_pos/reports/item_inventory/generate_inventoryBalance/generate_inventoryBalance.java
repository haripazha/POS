package com.example.acer.jm_pos.reports.item_inventory.generate_inventoryBalance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.jm_pos.R;

import java.util.ArrayList;

public class generate_inventoryBalance extends AppCompatActivity implements generate_invBalanceContract.invBalance_view{
    //mvp declaration
    generate_invBalancePresenter presenter;

    //Object declaration
    TextView inventory_id;
    TextView item_name;
    TextView item_price;
    TextView uom;
    TextView stocks;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_inventory_balance);
        presenter = new generate_invBalancePresenter(this);


        inventory_id    = findViewById(R.id.inventory_id);
        item_name       = findViewById(R.id.item_name);
        item_price      = findViewById(R.id.item_price);
        uom             = findViewById(R.id.uom);
        stocks          = findViewById(R.id.stocks);
        back_button     = findViewById(R.id.back_button);

        //System start
        systemStart();
    }

    public void systemStart(){

        //Run Report
        presenter.get_inventoryBalance();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void populateInventoryItem(String inventory_id_string, String inventory_item_name_string, String inventory_stock_string,
                                      String inventory_desc,String inventory_price) {

        inventory_id.setText(inventory_id_string);
        item_name.setText(inventory_item_name_string);
        item_price.setText(inventory_price);

    }
}
