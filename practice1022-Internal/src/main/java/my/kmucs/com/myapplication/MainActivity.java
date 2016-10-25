package my.kmucs.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText edit01;
    TextView tv01;
    Button btn_save, btn_load, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체 얻어오기기
       edit01 = (EditText)findViewById(R.id.et01);
        tv01 = (TextView)findViewById(R.id.tv01);
        btn_save = (Button)findViewById(R.id.btn01);
        btn_load = (Button)findViewById(R.id.btn02);
        btn_delete = (Button)findViewById(R.id.btn03);

        //이벤트 장착
        btn_save.setOnClickListener(this);
        btn_load.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    //이벤트 구현1. Button을 클릭했을 때 자동으로 onClick을 호출
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn01:  //internal저장소에 file저장
                Toast.makeText(getApplicationContext(), "btn01클릭", Toast.LENGTH_SHORT).show();
                //EditText에서 데이터 얻어오기
                String data = edit01.getText().toString(); //EditText에서 입력받은 Text 얻기

                //EditText 비워주기
                edit01.setText("");

                //내부영역에 file생성하면서 데이터저장
                try{
                    //FileOutputStream을 이용해서 파일생성, 파일명 "data.txt"
                    //MODE_APPEND 하면 저장되는 파일들을 이어서 붙일 수 있다.
                    //MODE_PRIVATE는 계속 파일 입력하면 새로저장한다.
                    FileOutputStream fos = openFileOutput("data.txt", Context.MODE_APPEND);
                    PrintWriter writer = new PrintWriter(fos);
                    writer.println(data);
                    writer.close();
                    Toast.makeText(getApplicationContext(), "데이터 저장 성공", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.btn02:  //internal저장소에서 file로드
                Toast.makeText(getApplicationContext(), "btn02클릭", Toast.LENGTH_SHORT).show();
                StringBuffer buffer = new StringBuffer();
                try{
                    //FileInputStream을 이용해서 file load
                    FileInputStream fis = openFileInput("data.txt");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    String str = reader.readLine(); //한줄 씩 읽어와라
                    while(str != null){           //아직 데이터가 남아있다면
                        buffer.append(str + "\n");
                        str = reader.readLine();
                    }
                    tv01.setText(buffer.toString());

                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.btn03: //internal 저장소에서 파일 삭제
                //file 영역 데이터삭제
                Toast.makeText(getApplicationContext(),"btn03클릭", Toast.LENGTH_SHORT).show();
                File file = new File(getFilesDir(), "data.txt");   //getFilesDir경로에 data.txt파일 얻기
                Log.d("isFile", file.isFile()+""); //isfile하면 파일이 있냐 없냐를 확인해준다
                    if(file.isFile()){
                        tv01.setText("파일이 정상적으로 삭제되었습니다.");
                    }
                    else{
                        tv01.setText("삭제할 파일이 없습니다.");
                    }
                file.delete();

                //cache 영역 데이터 삭제
                File cache_file = new File(getCacheDir(), "internal_cache_data");
                cache_file.delete();

                break;
        }
    }

    public void cashFile(){
        String file_name = "internal_cache_data";
        String data = "Test Cache";
        try{
            File cacheDir = getCacheDir();
            File cacheFile = new File(cacheDir.getAbsoluteFile(), file_name);
            FileOutputStream fos = new FileOutputStream(cacheFile.getAbsoluteFile());
            fos.write(data.getBytes());
            fos.close();

        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
}
