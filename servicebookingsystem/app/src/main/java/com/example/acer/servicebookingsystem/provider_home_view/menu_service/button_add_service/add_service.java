package com.example.acer.servicebookingsystem.provider_home_view.menu_service.button_add_service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.acer.servicebookingsystem.R;
import com.example.acer.servicebookingsystem.user_register_view.register_view_a.register_upload_photos;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class add_service extends AppCompatActivity implements add_serviceContract.add_serviceView{
    //mvp declaration
    add_servicePresenter presenter;

    //object declaration

    Button add_service;

    ImageView back_button;
    ImageView service_img;

    TextInputLayout service_name;
    TextInputLayout service_description;
    TextInputLayout service_rate;

    Spinner company_service;


    String service_img_imgString = "";

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        presenter = new add_servicePresenter(this);

        //object declaration
        back_button     = findViewById(R.id.back_button);
        service_name    = findViewById(R.id.service_name);
        service_description = findViewById(R.id.service_description);
        service_rate        = findViewById(R.id.service_rate);
        service_img         = findViewById(R.id.service_img);
        add_service         = findViewById(R.id.add_service);
        company_service     = findViewById(R.id.company_service);
        pd                  = new ProgressDialog(add_service.this);

        //run this method when systemStart
        systemStart();
    }

    //system start method
    public void systemStart(){

        //go to previous page
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        //add service function
        add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pd.setMessage("Submitting...");
                pd.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.add_service(service_name.getEditText().getText().toString(),
                                service_description.getEditText().getText().toString(),
                                service_rate.getEditText().getText().toString(),
                                service_img_imgString,company_service.getSelectedItem().toString());
                    }
                },1000);

            }
        });

        //get service image
        service_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagePicker.Builder(add_service.this)
                        .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();
            }
        });

    }


    //Get the img result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            Log.d("Path",mPaths.toString() + " Size "+mPaths.size());
            Bitmap bmImg_0 = BitmapFactory.decodeFile(mPaths.get(0));

            service_img.setImageBitmap(bmImg_0);

            Log.d("bitmap_data",bmImg_0.toString());
            service_img_imgString = getStringImage(bmImg_0);

        }
    }


    //Convert image to imageString
    //Convert bitmap to image string
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();


        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public void onInsertSuccess() {
        onBackPressed();
        finish();
    }

    @Override
    public void onInsertFailed() {
        pd.hide();
    }
}
