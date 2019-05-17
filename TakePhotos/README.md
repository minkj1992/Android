# 안드로이드 intent를 활용한 camera 호출 및 저장

[공식 자료](https://developer.android.com/training/camera/photobasics.html)
[참고 자료](https://raon-studio.tistory.com/6)

- `Intent`
- `startActivityForResult()`: 서브 액티비티로부터 결과값을 받고 싶을 경우
- `onActivityResult()`: 
    - `startActivityForResult()`의 callback method
    - `putExtra()`를 통하여 main activity에게 결과값을 넣어줄 수 있다.
- ``