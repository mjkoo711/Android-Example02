package my.kmucs.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Koo on 2016-10-15.
 */

public class ProfileActivity extends Activity {
    TextView txt_name, txt_age, txt_gender;
    Button btnEnd;
    RadioButton rbtn03, rbtn04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_name = (TextView)findViewById(R.id.tv01);
        txt_age = (TextView)findViewById(R.id.tv02);
        txt_gender = (TextView)findViewById(R.id.tv03);
        btnEnd = (Button)findViewById(R.id.btn02);
        rbtn03 = (RadioButton)findViewById(R.id.rbtn03);
        rbtn04 = (RadioButton)findViewById(R.id.rbtn04);

        Intent get_i = getIntent();
        Data data = (Data)get_i.getSerializableExtra("data");
        /* 이건 각각받을떄 */
        //String getName = get_i.getExtras().getString("name");
        //int getAge = get_i.getExtras().getInt("age");
        //String getGender = get_i.getExtras().getString("gender");
        //txt_name.setText("이름 : " + getName);
        //txt_age.setText("나이 : " + getAge);
        //txt_gender.setText("성별 : " +getGender);
        //if(getGender.equals(rbtn03.getText())){
        //    rbtn03.setChecked(true);
        //}else if(getGender.equals(rbtn04.getText())){
        //    rbtn04.setChecked(true);
        //}

        txt_name.setText("이름 : " + data.name);
        txt_age.setText("나이 : " + data.age);
        txt_gender.setText("성별 : " + data.getGender());

        if("남".equalsIgnoreCase(data.gender)){
            rbtn03.setChecked(true);
        }else{
            rbtn04.setChecked(true);
        }





        btnEnd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
