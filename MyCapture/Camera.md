#

# `SurfaceView`

>서피스뷰 (껍데기)

- 일반 뷰와 달리, 사이즈가 변하거나 하면 자동으로 callback이 호출되는 것이 아니다.

- 그러므로 1) 먼저 `SurfaceHolder.Callback`을 가져와주고 따로 callback을 설정 해주어야 한다.

- 이후 생성해준 SurfaceHolder에 `holder.addCallback(this);`
    
```java
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{}
```

# `SurfaceHolder`
> `SurfaceView`의 모든 로직들을 처리해주는 Holder

- `getHolder()`를 사용하면, 현재 extend하고 있는 `SurfaceView`의 `SurfaceHolder`를 가져올 수 있다.

# `SurfaceHolder.Callback`

> SurfaceHolder의 인터페이스

- 3가지 method를 override 해주어야 한다.

```java
    // 서비스뷰가 메모리에 만들어지는 시점에 호출
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }
    // 서비스뷰 크기 변경 시점에 호출
    // 화면에 보여지기 전에 크기가 결정되는 시점
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    // 서비스뷰 삭제될때 호출
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
```


# Camera

- `camera = Camera.open();`: 카메라 객체 생성
- `camera.setPreviewDisplay(holder);`: 카메라 객체의 preview를 해당 서피스뷰에 보여주겠다 설정.
    - 예외 발생 가능하여 `try ~ catch`를 사용


- 카메라 preview 시작할 경우: `camera.startPreview();`

- 카메라 삭제할 경우
```java
        camera.stopPreview();
        camera.release();
        camera = null;
```

# `Android Fail to connect to camera service`

- 안드로이드 6.0 이상 부터는 manifest 권한 요청 외에도, runtime 도중에, 권한 검사를 실시한다. 

- `camera.open()` 부분에서 `runtime error`가 떠서, 이를 해결하기 위하여, `RequestUserPermission.java`를 생성해주었고, 

```java
package com.example.capture;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class RequestUserPermission {

    private Activity activity;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public RequestUserPermission(Activity activity) {
        this.activity = activity;
    }

    public  void verifyStoragePermissions() {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}

```

- `mainActivity.java`의 `onCreate()`파트에서 권한 검사를 실시해주었다.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestUserPermission requestUserPermission = new RequestUserPermission(this);
        requestUserPermission.verifyStoragePermissions();
        super.onCreate(savedInstanceState);
```
