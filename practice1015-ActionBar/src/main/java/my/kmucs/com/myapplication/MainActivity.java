package my.kmucs.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button)findViewById(R.id.btn01);
        btn02 = (Button)findViewById(R.id.btn02);

        //Context Menu로 등록
        //어떠한 view객체도 context menu로 등록가능
        registerForContextMenu(btn01);
    }

    //Option Menu만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater= getMenuInflater();
        //inflater : 전개하다, xml있는것을 장착한다. 로봇장착의 장착
        inflater.inflate(R.menu.actionmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }



    //context Menu 만들기
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.btn01:
                Toast.makeText(getApplication(), "일반적인 클릭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn02:
                //PopupMenu 객체 생성
                //첫번째 파라미터 : context
                //두번째 파라미터 : popup Menu를 붙일 객체
                PopupMenu popup = new PopupMenu(this, v);

                getMenuInflater().inflate(R.menu.contextmenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.save:
                                Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.search:
                                Toast.makeText(getApplicationContext(),"SEARCH", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.setting:
                                Toast.makeText(getApplicationContext(),"SETTING", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });


                popup.show();
                break;
        }
    }

    //option menu 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){ //눌러진 MenuItem의 item id
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
