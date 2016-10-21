package my.kmucs.com.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Animation - 화면에 변화하는 효과를 주는 기법
            - Frame Animation : 한장한장 바뀌는 이미지를 교체하는 방식
                                고전적으로 많이 사용되는 방식
            - Tween Animation : 움직이는 방법을 설정해서
                                계산 결과로 중간이미지를 표현하는 방식
         */
        Button btn01 = (Button)findViewById(R.id.btn01);
        ImageView iv01 = (ImageView)findViewById(R.id.imageView01);

        //ImageView의 배경으로 지정한 animation 설정파일을 객체로 얻어옴
        final AnimationDrawable drawable = (AnimationDrawable)iv01.getBackground();

        btn01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(drawable.isRunning()){ //동작중일 때
                     //멈추기
                    drawable.stop();
                }
                else{                      //동작이 멈춰있을때
                    drawable.start();
                }
            }
        });


    }
}
