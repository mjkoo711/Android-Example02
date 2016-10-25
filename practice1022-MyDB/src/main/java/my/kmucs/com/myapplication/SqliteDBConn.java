package my.kmucs.com.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Koo on 2016-10-22.
 */

public class SqliteDBConn extends SQLiteOpenHelper {
    public SqliteDBConn(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //최초에 데이터베이스가 없을 경우, 데이터 베이스 생성을 위해 호출됨
        //테이블생성
        String sql = "CREATE table myDB(id integer primary key autoincrement, name text)";  //autoincrement : 1부터 1씩 증가하는 옵션
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB의 버전이 바뀌었을 때 호출되는 콜백 메소드
        //버전 바뀌었을 때 기존 데이터 베이스를 어떻게 변경할 것인지를 작성
        //가 버전의 변경 내용들을 버전마다 하는게 좋음.
        String sql = "DROP table myDB"; //테이블 드랍
        db.execSQL(sql);

        //데이터 추출작업(그냥지우면 다날라가니까)

        //테이블 생성
        onCreate(db);
    }
}
