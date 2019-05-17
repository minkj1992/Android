package com.example.capture;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Camera camera = null;
    private int camId = 1;

    public CameraSurfaceView(Context context) {
        super(context);
        init(context);
    }


    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        Log.e("Photo","init started");
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("Photo","preview created");
        try {
            releaseCameraAndPreview();
            if (camId == 0) {
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            }
            else {
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            }
        } catch (Exception e) {
            Log.e("photo", String.valueOf(R.string.app_name));
            e.printStackTrace();
        }
//        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
        Log.e("Photo","preview changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    //카메라 스냅샷 찍는 함수
    public boolean Capure(Camera.PictureCallback callback) {
        Log.e("Photo","Capture started");
        if (camera != null) {
            camera.takePicture(null,null,callback);
            return true;
        } else {
            return false;
        }
    }

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

}
