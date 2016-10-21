package my.kmucs.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //여기서 View는 버튼자체를 의미
    public void Alpha(View v){
        //anim변수에 AnimationUtils클래스의 loadAnimation 메소드를 이용해서
        //만들어놓은(여기서는 alpha.xml) 에니매이션 효과를 로드(장착)
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);

        //View의 animation을 실행하는 것.
        //어떻게? 파라미터의 애니메이션 효과
        v.startAnimation(anim);
    }
    public void Rotate(View v){
        anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        v.startAnimation(anim);
    }
    public void Scale(View v){
        anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        v.startAnimation(anim);

    }
    public void Translate(View v){
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        v.startAnimation(anim);
    }
    public void Set(View v){
        anim = AnimationUtils.loadAnimation(this, R.anim.set);
        v.startAnimation(anim);

    }

}
