package com.sbs.example.sjc.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

public class GameThread extends Thread{

    Context context;
    SurfaceHolder sHolder; //핵심개체이며 화면의 구성에 대한 정보를 모두 가지고 있다.
    boolean isRun = true;
    boolean isWait = false;
    Canvas canvas; //화면에 무언가를 그리기 위한 캔바스 객체(sHolder가 가지고 있다)
    int width, height;
    //랜덤한 수를 발생시키기 위해
    Random ran = new Random();
    //색을 저장할 배열
    Paint[] paint = new Paint[5];
    //원 객체를 저장할 배열
    ArrayList<Circle> list = new ArrayList<Circle>();



    //생성자
    public GameThread(Context context, SurfaceHolder sHolder){
        this.context = context;
        this.sHolder = sHolder;
        //원을 그릴 색 만들어서 배열에 저장하기
        initPaint();

    }
    //화면의 폭과 높이를 전달바든ㄴ다
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
        Log.e("*********", "width:"+width+" / height:"+height);
    }
    //빠르게 돌아가는 스레드에서 메소드를 실행하거나 멤버필드가 교환되는 작업을 하면
    //스레드 작업이 깨질수있기 때문에 동기화가 필요하다.
    //스레드의 일시정지 혹은 재시작 하는 메소드
    public void pauseNResume(boolean isWait){
        synchronized (this) {
            this.isWait = isWait;
            notify();
        }
    }

    //스레드 완전 정지하는 메소드
    public void stopForever(){
        synchronized (this) {
            this.isRun = isRun;
            notify();
        }
    }

    //스레드 본체인 run메소드에서 그리는 작업을 해준다.
    @Override
    public void run() {
        while(isRun){ //isRun의 초기 값이 true 이므로 기본적으로 무한 루프이다.
            //Canvas객체 얻어오기
            canvas = sHolder.lockCanvas();//화면정보를 다 담을때까지 화면을 잠궈놓는다.
            //화면에 그리는 작업을 한다.
            try{
                //동기화 블럭에서 작업을 해야한다.
                synchronized (sHolder) { //그리기위한 모든 작업을 하는 곳
                    //canvas 객체를 이용해서 반복적인 그리기 작업을 한다.
                    canvas.drawColor(Color.WHITE);
                    //반복문을 돌면서 원을 그린다
                    for(int i=0 ; i < list.size() ; i++){
                        //해당 인덱스의 원객체를 얻어온다.
                        Circle cir = list.get(i);
                        canvas.drawCircle(cir.x, cir.y, //원의 위치
                                5,  //반지름
                                paint[cir.color]); //원의 색
                    }
                    canvas.drawText("공의수:"+list.size(), 0, 20, paint[0]);
                    //원 만들기(10번 돌때 한번의 확률로 원을 만들기 위해)
                    //if(ran.nextInt(5)==0)
                    makeCircle();
                    //원 움직이기
                    moveCircle();
                }
            }finally{
                if(canvas!=null)//실제로 화면을 그리는 곳(while을 돌면서 화면을 덧그리기 때문에 invalidate가 필요하지 않다)
                    //잠근 화면을 풀고 작업한canvas 객체를 전달해서 화면을 그린다.
                    sHolder.unlockCanvasAndPost(canvas);
            }

            //스레드를 일시 정지 하기 위해
            if(isWait){ //isWait의 초기값은 false 이다
                try {
                    synchronized (this) {
                        wait(); //notify할때까지 기다린다.(일시정지)
                    }
                } catch (Exception e) {}
            }//if
        }//while

    }//run

    //페인트 객체를 생성해서 배열에 담는 메소드
    public void initPaint(){
        //paint 객체 생성해서 배열에 담기
        Paint p1 = new Paint();
        p1.setColor(Color.DKGRAY);
        p1.setStyle(Paint.Style.FILL); // 원을 채우도록
        Paint p2 = new Paint();
        p2.setColor(Color.RED);
        p2.setStyle(Paint.Style.FILL); // 원을 채우도록
        Paint p3 = new Paint();
        p3.setColor(Color.GREEN);
        p3.setStyle(Paint.Style.FILL); // 원을 채우도록
        Paint p4 = new Paint();
        p4.setColor(Color.BLUE);
        p4.setStyle(Paint.Style.FILL); // 원을 채우도록
        Paint p5 = new Paint();
        p5.setColor(Color.YELLOW);
        p5.setStyle(Paint.Style.FILL); // 원을 채우도록
        //배열에 담는다.
        paint[0]=p1;
        paint[1]=p2;
        paint[2]=p3;
        paint[3]=p4;
        paint[4]=p5;
    }
    //circle 객체를 만드는 메소드
    public void makeCircle(){
        //원의 위치
        int x = ran.nextInt(width); //화면의 폭 안의 랜덤한 x지점
        int y = ran.nextInt(height); //화면의 높이 안의 랜덤한 y지점
        //원의 초기 속도
        int speedX = ran.nextInt(10)-5;
        int speedY = ran.nextInt(10)-5;
        //원의 색
        int color = ran.nextInt(5);
        //Circle 객체를 생성해서 배열에 담는다
        Circle cir = new Circle(x, y, color, speedX, speedY);
        list.add(cir);
    }
    //circle객체를 움직이는 메소드
    public void moveCircle(){
        //반복문 돌면서 원 객체 움직이기
        for(Circle cir : list){
            cir.x += cir.speedX;
            cir.y += cir.speedY;
            //화면을 벗어나지 못하도록 처리
            if(cir.x <= 0 || cir.x >= width){
                //속도의 부호를 바꾸어서 반대방향으로 움직인다.
                cir.speedX *= -1;
                //바뀐 속도로 한번 움직여 준다.
                cir.x += cir.speedX;
            }
            if(cir.y <= 0 || cir.y >= height){
                //속도의 부호를 바꾸어서 반대방향으로 움직인다.
                cir.speedY *= -1;
                //바뀐 속도로 한번 움직여 준다.
                cir.y += cir.speedY;
            }
        }
    }
}