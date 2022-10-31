package com.candelatech.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button on_off_btn;
    public Boolean is_on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on_off_btn = findViewById(R.id.on_off);
        Boolean flash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        on_off_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flash){
                    is_on = !is_on;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        String cameraId = null;
                        try {
                            cameraId = camManager.getCameraIdList()[0];
                            camManager.setTorchMode(cameraId, is_on);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


    }
}