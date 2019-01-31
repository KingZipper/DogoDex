package com.example.williamrudwall.dogodex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Tab1Fragment extends android.support.v4.app.Fragment {

    private static final int REQUEST_CAMERA_CAPTURE = 2;
    private final int REQUEST_PERMISSION_CODE = 1000;
    private ImageView cameraView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);


        Button cameraButton = (Button) view.findViewById(R.id.cameraButton);
        cameraView = (ImageView) view.findViewById(R.id.cameraView);

        if(!checkPermissionFromDevice()) {
            requestPermission();
        }

        //Starta kameran knapp
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CAMERA_CAPTURE);
            }
        });

        return view;
    }

    //Frågar om tillåtelse att spara på enheten
    private void requestPermission() {
        ActivityCompat.requestPermissions(this.getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        }, REQUEST_PERMISSION_CODE);

    }

    // Kollar tillåtelse från enheten
    private boolean checkPermissionFromDevice(){
        int write_external_storage_result = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED;
    }


    // Efter att man tagit en bild så visas resultatet på skärmen i en imageview
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if (requestCode == REQUEST_CAMERA_CAPTURE) {
               Bitmap bitmap = (Bitmap) data.getExtras().get("data");
               cameraView.setImageBitmap(bitmap);
           }

    }

}

