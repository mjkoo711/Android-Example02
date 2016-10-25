package my.kmucs.com.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SqliteDBConn helper;
    private String dbName = "mySBSDB.db";  //db명
    private int dbVersion = 1;              //db 버전
    private SQLiteDatabase db;                //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this : 현재 화면의 제어자(activity정도?), dbName : 사용할(만들어질) DB 이름, null : 커서팩토리(null값을 넣으면 표준 커서가 사용됨), dbVersion
        helper = new SqliteDBConn(this, dbName, null, dbVersion);


        try {
            db = helper.getWritableDatabase(); //읽고쓸수있는 데이터베이스객체
            //db = helper.getReadableDatabase(); 읽기 전용 = select만 가능 => 고객전용
            insert();
            select();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "DB에러", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //insert()
    private void insert(){
        db.execSQL("INSERT INTO myDB(name) Values('seo')");
        db.execSQL("INSERT INTO myDB(name) Values('lee')");
        db.execSQL("INSERT INTO myDB(name) Values('koo')");
        db.execSQL("INSERT INTO myDB(name) Values('um')");
        db.execSQL("INSERT INTO myDB(name) Values('cha')");

    }
    //update()

    //select()
    private void select(){
        Cursor c = db.rawQuery("SELECT * FROM myDB;", null);
        while(c.moveToNext()){
            int id = c.getInt(0); //첫행, id 줄임
            String name = c.getString(1);
            Log.d("DBTEST", "id" + id + ", name = " + name);
            Toast.makeText(getApplicationContext(),"id" + id + ", name = " + name,Toast.LENGTH_SHORT).show();
        }
    }
    //delete()
}

