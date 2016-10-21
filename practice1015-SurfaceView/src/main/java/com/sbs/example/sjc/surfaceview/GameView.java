package com.sbs.example.sjc.surfaceview;

/*
 * [[ 빠른 그래픽 처리를 위한 SurfaceView 사용하기 ]]
 * 1. surfaceview 클래스를 상속받는다.
 * 2. surfaceHolder.callback인터페이스를 구현한다.
 * 3. 인터페이스의 추상메소드를 오버라이딩 한다.
 * 4. 백그라운드에서 작업할 스레드를 만들어서 SurfaceHolder 객체를 넘겨준다.
 * 5. 스레드에서는 surfaceholder 객체를 이용해서 그래픽 작업한다.
 * 6.
 */

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//생성자 1개짜리는 View만 쓸때. 2개짜리는 다른것도 쓸때
public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    Context context;
    SurfaceHolder sHolder;
    GameThread gameThread;
    int width, height; //surfaceView가 차지하고 있는 실제 공간의 크기

    public GameView(Context context) {
        super(context);
        this.context = context;
        init(); //초기화
    }

    //초기화하는 메소드
    public void init(){
        //SurfaceHolder객체 얻어오기
        sHolder = getHolder(); //surface가 holder를 가지고 있기 때문에 그냥 getholder하면 된다.
        //콜백 객체 등록
        sHolder.addCallback(this);
        //스레드 객체 생성
        gameThread = new GameThread(context, sHolder);
    }

    //뷰화면의 크기가 변화가 생겼을 때 호출되는 메소드(화면의 폭과 높이 정보가 인자로 들어온다.)
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.e("*********", "surfaceChanged()");
        this.width = width;
        this.height = height;
        gameThread.setScreenSize(width, height);
        Log.e("*********", "width:"+width+" / height:"+height);

    }
    //뷰가 처음 만들어 질 때 호출되는 메소드
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("*********", "surfaceCreated()");
        try {
            //스레드를 시작시킨다.
            gameThread.start();
        } catch (Exception e) {
            Log.e("********", "스레드 시작 시 에러 발생! 스레드를 다시 생성");
            //에러 발생하면 재시작하기
            restartThread();
        }

    }
    //뷰가 파괴될 때 호출되는 메소드
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("*********", "surfaceDestroyed()");
        //에러 없이 스레드를 안전하게 종료하기 위해
        boolean tryJoin = true;
        gameThread.stopForever(); //종료시 에러를 잡기 위한 핵심!
        while(tryJoin){//join이 성공할때까지
            try{
                gameThread.join();
                tryJoin=false;
            }catch (Exception e) {

            }
        }

    }
    //스레드를 재시작하는 메소드
    public void restartThread(){
        //스레드 정지
        gameThread.stopForever();
        //스레드 비우고
        gameThread = null;
        //객체 다시 생성
        gameThread = new GameThread(context, sHolder);
        //화면의 폭과 높이 전달
        gameThread.setScreenSize(width, height);
        //스레드 시작
        gameThread.start();
    }
    //스레드 일시정지하는 메소드
    public void pauseThread(){
        gameThread.pauseNResume(true);

    }//일시 정지된 스레드를 재시작하는 메소드
    public void resumeThread(){
        gameThread.pauseNResume(false);

    }
}