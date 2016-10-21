package my.kmucs.com.mysbs02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int SAVE = 1;
    public static final int SEARCH = 2;
    public static final int SETTING = 3;
    public static final int CHECKABLE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //add (GroupID, ItemID, 배치순서, 메뉴타이틀
        //Group ID : 메뉴아이템을 그룹으로 관리할 때 그룹의 식별값
        //Item ID : 각각의 MEnuItem을 구별하기위한 고유식별값 =>id값
        //배치순서(order) : add한 순서와 상관없이 배치순서를 정할 떄 사용

        //보통메뉴, 선택할 수 있는 메뉴, 아이콘이 들어간메뉴, 서브메뉴

        //보통메뉴, save에는 아이콘이 들어간메뉴(어떻게해야실행되니)
        menu.add(0,SAVE,0, "SAVE").setIcon(R.mipmap.ic_launcher);
        menu.add(0,SEARCH,0, "SEARCH");
        menu.add(0,SETTING,0, "SETTING");



        //선택할 수 있는 메뉴
        MenuItem item01 = menu.add(0,CHECKABLE,0, "Checkable");
        item01.setCheckable(true);
        item01.setChecked(true);

        //서브메뉴
        SubMenu sub = menu.addSubMenu("Sub");
        sub.add(0,1,0, "신촌");
        sub.add(0,2,0, "거제");
        sub.add(0,3,0, "이대");

        return super.onCreateOptionsMenu(menu);
    }

    //메뉴리스트에서 메뉴를 클릭했을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //case 뒤에 id값이 숫자로 들어간건 안좋은 코딩법 : 가시적으로 잘 확인이ㄷ안됨
        //case SAVE처럼 숫자대신에 final 변수이름을 넣어주는게 좋은 코딩이다.
        switch(item.getItemId()){
            case SAVE:
                Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_SHORT).show();
                break;
            case SEARCH:
                Toast.makeText(getApplicationContext(), "SEARCH", Toast.LENGTH_SHORT).show();
                break;
            case SETTING:
                Toast.makeText(getApplicationContext(), "SETTING", Toast.LENGTH_SHORT).show();
                break;
            case CHECKABLE:
                Toast.makeText(getApplicationContext(), "CHECKABLE", Toast.LENGTH_SHORT).show();

                if(item.isChecked()){ //체크가 되어있는가?
                    item.setChecked(false);
                }
                else{
                    item.setChecked(true);
                }
                
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
