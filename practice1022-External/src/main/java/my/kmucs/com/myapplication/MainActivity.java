package my.kmucs.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv01;
    private EditText et01;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private String state;   //상태값을 저장할 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체얻기
        tv01 = (TextView)findViewById(R.id.tv01);
        et01 = (EditText)findViewById(R.id.et01);
        btn01 = (Button)findViewById(R.id.btn01);
        btn02 = (Button)findViewById(R.id.btn02);
        btn03 = (Button)findViewById(R.id.btn03);
        btn04 = (Button)findViewById(R.id.btn04);

        //외부 메모리에 파일 읽기/쓰기
        //1. 외부 메모리를 확인(권한확인)
        checkExternalStorage();
        //2. 읽기/쓰기 경로를 지정
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        //3. 읽기/쓰기 경로를 지정(개인영역)
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);

    }

    //외부 메모리 권한 확인
    public boolean checkExternalStorage(){
        state = Environment.getExternalStorageState();
        //외부 메모리 상태
        if(Environment.MEDIA_MOUNTED.equals(state)){
            //읽기, 쓰기가 모두 가능
            tv01.setText("읽기, 쓰기 모두 가능");
            return true;
        }
        else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            //읽기만 가능
            tv01.setText("읽기만 가능");

            return false;
        }
        else{
            //읽기, 쓰기 모두 불가능
            tv01.setText("읽기, 쓰기 모두 불가능");
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        String data;
        switch(v.getId()){
            case R.id.btn01: // 저장 => 어플이 지워져도 남아있다.
                data = et01.getText().toString();

                try{
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //
                    File f = new File(path, "external.txt"); //경로, 파일이름
                    FileWriter writer = new FileWriter(f, true); //데어터를 추가할지 말지 결정
                    PrintWriter out = new PrintWriter(writer);
                    out.println(data);
                    out.close(); //파일객체들의 연결을 끊어준다.
                    tv01.setText("저장완료");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btn02: // 로드
                try{
                    StringBuffer buffer = new StringBuffer();
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File f= new File(path, "external.txt");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                    String str = bufferedReader.readLine();
                    while(str != null){
                        buffer.append(str+"\n");  //\n은 개행문자(엔터값)
                        str = bufferedReader.readLine();
                    }
                    tv01.setText(buffer);
                    bufferedReader.close();


                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.btn03: //개인영역 저장 => 어플이 지워질때 같이 다지워짐
                data = et01.getText().toString();

                try{
                    File path = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES); //개인영역에 저장
                    File f = new File(path, "private.txt"); //경로, 파일이름
                    FileWriter writer = new FileWriter(f, true); //데어터를 추가할지 말지 결정
                    PrintWriter out = new PrintWriter(writer);
                    out.println(data);
                    out.close(); //파일객체들의 연결을 끊어준다.
                    tv01.setText("개인영역 저장완료");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.btn04: //개인영역 로드
                //위에거와 비슷...
               break;

        }
    }
}
