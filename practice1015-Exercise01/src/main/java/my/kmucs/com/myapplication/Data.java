package my.kmucs.com.myapplication;

import java.io.Serializable;

/**
 * Created by Koo on 2016-10-15.
 */

//serializable은 직렬화, 약간 json에서 문자열로 직렬화시켜서 전송하는 느낌이랑 비슷해
public class Data implements Serializable{
    public String name;
    public int age;
    public String gender;

    public Data(){}
    public Data(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }


}
