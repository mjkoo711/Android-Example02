package my.kmucs.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Koo on 2016-10-15.
 */

//	- Layout을 이용안함
// setContentView사용 x
 //       별도의 뷰 클래스를 만들어 onDraw()메소드를 이용하여 구현해야함
public class MyView extends View {

    Paint paint = new Paint(); //화면에 그려줄 도구를 셋팅
    Path path = new Path();

    //생성자
    public MyView(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE); //선 스타일
        paint.setStrokeWidth(10f); //선의 굵기
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //화면에 터치가 발생했을 때 호출되는 콜백 메소드
        //break;없이 동시에 일어나야함
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: //손을 댔을때
                //Toast.makeText(getContext(), "Action_down", Toast.LENGTH_SHORT).show();
                path.moveTo(x, y); //그리지 말고 위치만 이동(moveTo는 이동시키는 것)
            case MotionEvent.ACTION_MOVE: //손을 이동할떄
                //Toast.makeText(getContext(), "Action_Move", Toast.LENGTH_SHORT).show();
                path.lineTo(x, y); //자취에 선을 그려라
            case MotionEvent.ACTION_UP: //손을 떘을떄
                //Toast.makeText(getContext(), "Action_Up", Toast.LENGTH_SHORT).show();
                //x = (int)event.getX();
                //y = (int)event.getY();
        }
        invalidate(); //화면에 그려준다 이함수만 쓰면
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) { //화면을 셋팅
        super.onDraw(canvas);


        /* 그림 나타날 수 있는 것들의 예시들

        //Paint paint = new Paint(); //화면에 그려줄 도구를 셋팅
        //paint.setColor(Color.RED); //색상을 셋팅

        setBackgroundColor(Color.BLUE); //배경색을 셋팅
       // canvas.drawRect(100,500,200,200,paint); //사각형의 좌상우하 좌표
        canvas.drawRect(300,500,200,200,paint);
        canvas.drawCircle(400,320,40,paint); //원의 중심 x, y, 반지름

        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10f);  //선의 굵기
        canvas.drawLine(400,100,500,150,paint); //직선그리기

        //path 자취 만들기
        paint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(20, 100); //자취 이동
        path.lineTo(100,200); //자취 직선
        path.cubicTo(150, 30, 200, 300, 300, 200); //자취 베이지곡선
        canvas.drawPath(path, paint);

        //지정된 좌표에 점을 그림
        paint.setColor(Color.GRAY);
        canvas.drawPoint(300,30,paint);

        //호를 그려주는 메소드
        paint.setColor(Color.WHITE);
        RectF rectF = new  RectF(100,10,200,100);
        canvas.drawArc(rectF, 0, 90, true, paint);
        */
        canvas.drawPath(path, paint);
    }
}
