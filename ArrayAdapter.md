# AdapterView
> 어댑터 뷰는 배열, 파일, 데이터베이스에 저장된 데이트를 읽어서 화면에 표시할 때 유용한 뷰이다.

- `ListView`
- `Gallery`
- `GridView`

어댑터 뷰에 데이터를 공급하는 클래스가 `Adapter`이다. 어댑터는 개념적으로 데이터 소스와 어댑터 뷰 중간에 위치하여 데이터 소스에서 데이터를 읽어 어댑터 뷰에 공급한다. 

어댑터에는 배열에서 데이터를 가져오는 `ArrayAdapter`, 데이터베이스에서 데이터를 가져오는 `SimpleCursorAdapter`등이 있다.

## ListView
> 항목들을 수직으로 보여주는 어댑터 뷰이다.
상하로 스크롤이 가능하며, 이때 보여지는 항목 데이터들은 이 뷰에 설정된 어댑터로 부터 제공된다. 어댑터는 리스트뷰와 데이터 사이의 브릿지라 할 수 있다.

### `ListView` 생성법
1) xml에 생성한 listview를 `ListView listView = view.findViewById(R.id.word_list);`로 생성한다.

2) activity 파일에서 `public class MainActivity extends ListActivity {}`를 사용하여 `extend`한다.

    -  `ListActivity`클래스는 `ListView`를 화면으로 사용하는 액티비티이다. 따라서 `ListView` 객체를 `findViewById`를 사용하여 찾을 필요가 없다. 단순히 `setListAdapter(adapter)`를 활용하여 리스트뷰와 어댑터를 연결만 해주면 스크롤이 가능한 수직 리스트가 화면에 나타난다.

### `Adapter` 생성
`ArrayAdapter(Context context, int textViewResourceId, T[] objects)`
`ArrayAdapter(현재 어플리케이션 컨텍스트,레이아웃아이디, 배열)`

```java
        listView.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                        Data.words)
        );
```

