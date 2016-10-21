package my.kmucs.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
    EditText et_name, et_age;
    Button btn01;
    RadioButton radio01, radio02;
    RadioGroup radioG01;
    Intent put_i;
    //data객체를 이용해 값을 받을때
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button)findViewById(R.id.btn01);
        et_name = (EditText)findViewById(R.id.et01);
        et_age = (EditText)findViewById(R.id.et02);
        radioG01 = (RadioGroup)findViewById(R.id.radiogroup);
        radio01 = (RadioButton)findViewById(R.id.rbtn01);
        radio02 = (RadioButton)findViewById(R.id.rbtn02);

        put_i = new Intent(getApplicationContext(), ProfileActivity.class);

        btn01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /* 각각 전달 */
                //String name = et_name.getText().toString();
                //int age = Integer.parseInt(et_age.getText().toString());
                //put_i.putExtra("name", name);
                //put_i.putExtra("age", age);

                /* 한꺼번에 전달, data객체이용, data객체에 implemnets Serializable을 같이 써줘야함*/
                data.name = et_name.getText().toString();
                data.age = Integer.parseInt(et_age.getText().toString());
                put_i.putExtra("data", data);

                startActivity(put_i);
            }
        });

        //라디오 그룹
        radioG01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbtn01:
                        //put_i.putExtra("gender", radio01.getText().toString());
                        data.setGender(radio01.getText().toString());
                        break;
                    case R.id.rbtn02:
                        //put_i.putExtra("genter", radio02.getText().toString());
                        data.setGender(radio02.getText().toString());
                        break;
                }
            }
        });
    }
}
