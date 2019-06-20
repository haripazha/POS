package com.example.acer.jm_pos.reports.item_inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.acer.jm_pos.R;
import com.example.acer.jm_pos.reports.item_inventory.generate_inventoryBalance.generate_inventoryBalance;

public class item_inventory extends AppCompatActivity implements item_inventoryContract.item_inventoryView{
    //mvp declaration
    item_inventoryPresenter presenter;

    //object declaration
    ImageView back_button;

    EditText start_date;
    EditText end_date;

    Button gen_inventoryBalance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_inventory);
        presenter = new item_inventoryPresenter(this);

        //object declaration
        start_date = findViewById(R.id.start_date);
        end_date   = findViewById(R.id.end_date);
        back_button = findViewById(R.id.back_button);
        gen_inventoryBalance = findViewById(R.id.generate_inventory_balance_report);


        //systemStart
        systemStart();
    }

    //systemStart
    public void systemStart(){

        //back pressed
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //proceed the process
        gen_inventoryBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK","CLICK");
                /*
                //condition if start date and end date is empty
                if(!start_date.getText().toString().equals("")&&!end_date.getText().toString().equals("")){
                    //presenter.storedDateSearchFilter(start_date.getText().toString(),end_date.getText().toString());
                }else{

                    //show alert dialog
                    presenter.startAndEnd_validationAlert();
                }*/

                Intent intent = new Intent(item_inventory.this,generate_inventoryBalance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
    }
}
