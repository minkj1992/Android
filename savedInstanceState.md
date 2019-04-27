# `SavedInstanceState`란


`public void onCreate(Bundle savedInstanceState) {`

도대체 `savedInstanceState` 란 뭘까? 이것이 왜 필요할까?

화면이 세로모드에서 가로모드로 전환 시 `onCreate`함수가 다시 호출된다. 만약 전역변수를 설정하고 그 값을 유지하며 항상 사용해야 하는 경우라도 화면이 세로모드에서 가로모드로 변경될 경우 전역변수에 설정한 값이 모두 초기화 된다. 이런 경우 변경된 값을 유지하고 싶다면  `savedInstanceState`을 이용하는 것이 좋다.

우선 다른 Activity를 호출할 경우 (세로모드 화면레이아웃과 가로모드 화면레이아웃이 다를경우도 해당된다)에 아래의 메써드가 호출된다.

`@Override protected void onSaveInstanceState(Bundle outState)`

```java
@Override
protected void onSaveInstanceState(Bundle outState) {
 int currentTodoPosition = getCurrentTodoList().indexOf(currentTodo);
 outState.putInt("currentCategoryPosition", currentCategoryPosition);
 outState.putInt("currentTodoPosition", currentTodoPosition);
 super.onSaveInstanceState(outState);
}
```
 

그런 후에 onCreate 메써드에서 savedInstanceState값이 null이 아닌경우 그 값을 불러서 쓸 수 있다.

예.
```java
if (savedInstanceState != null) {
 currentCategoryPosition = savedInstanceState.getInt("currentCategoryPosition");
 int currentTodoPosition = savedInstanceState.getInt("currentTodoPosition");
 refreshTodoList();
 if (currentTodoPosition != -1) {
  currentTodo = getCurrentTodoList().get(currentTodoPosition);
 }
}
```

출처: https://doraeul.tistory.com/43 [도래울]


# 화면 회전시 라이프 사이클
화면을 회전할 때 발생하는 이벤트들은 다음과 같습니다. 화면을 회전할 때마다 Activity가 종료되고 새로 만들어지기 때문에 라이프 사이클을 잘 이해하고 대처하는 것이 좋을 것 같습니다.

onPause() → onSaveInstanceState() → onStop() → onDestory() → onCreate() → onStart() → onResume()