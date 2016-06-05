package f_app.dmx.com.securelocate;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CoordinatesView  extends View implements AlarmActivity.UpdatePoint {
    private  Paint paint;
    private Canvas canvas;
    private  int TIME=3000;
    private float height=50;
    private float width=23;

    public CoordinatesView(Context context) {
        super(context, null);
    }

    public CoordinatesView(Context context, AttributeSet attrs) {
        super(context, attrs);
         //  TypedArray tArray=context.obtainStyledAttributes(attrs,R.styleable.personAttrs);
//         float textSize = tArray.getDimension(R.styleable.personAttrs_fontSize, 30);
//         paint.setTextSize(textSize);
            // Color color=tArray.getColor();

    }

    Point pa = new Point(10, 10);
    Point pb = new Point(20, 40);

    Point po = new Point();

    int centerX, centerY;

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        po.set(centerX, centerY);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void onDraw(Canvas canvas) {


        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.BLACK);
        width=getWidth();
        height=getHeight();

        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            // 画坐标轴
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                // 画直线
                canvas.drawLine(0, centerY, 2 * centerX, centerY, paint);
                canvas.drawLine(centerX, 0, centerX, 2 * centerY, paint);
                // 画X轴箭头
                int x = 2 * centerX, y = centerY;
                drawTriangle(canvas, new Point(x, y), new Point(x - 10, y - 5),
                        new Point(x - 10, y + 5));
                canvas.drawText("X", x - 15, y + 18, paint);
                // 画Y轴箭头
                x = centerX;
                y = 0;
                drawTriangle(canvas, new Point(x, y), new Point(x - 5, y + 10),
                        new Point(x + 5, y + 10));
                canvas.drawText("Y", x + 12, y + 15, paint);
                // 画中心点坐标
                // 先计算出来当前中心点坐标的值
                String centerString = "(" + (centerX - po.x) / 2 + ","
                        + (po.y - centerY) / 2 + ")";
                // 然后显示坐标
                canvas.drawText(centerString, centerX - 25, centerY + 15, paint);


            }

            if (canvas != null) {
            /*
             * TODO 画数据 所有外部需要在坐标轴上画的数据，都在这里进行绘制
             */
                paint.setColor(Color.BLUE);

                canvas.drawPoint(width-150,50,paint);
                canvas.drawText(".锚节点",width-150,50,paint);

                canvas.drawPoint(50,50,paint);
                canvas.drawText(".1",50,50,paint);

                canvas.drawPoint(23,height-50,paint);
                canvas.drawText(".2",23,height-50,paint);


               canvas.drawPoint(width-70,height-60,paint);
                canvas.drawText(".3",width-70,height-60,paint);

            }
            if (canvas != null){

                paint.setColor(Color.RED);

                canvas.drawPoint(width-150,100,paint);
                canvas.drawText(".危险终点",width-150,100,paint);

                canvas.drawPoint(3*(width/4),2*(height/5),paint);
                canvas.drawText(".1",3*(width/4),2*(height/5),paint);

                canvas.drawPoint(width/4,height/3,paint);
                canvas.drawText(".2",width/4,height/3,paint);

                canvas.drawPoint(width/3,4*(height/5),paint);
                canvas.drawText(".3",width/3,4*(height/5),paint);

                paint.setColor(Color.GREEN);
                canvas.drawPoint(width-150,150,paint);
                canvas.drawText(".移动锚点",width-150,150,paint);





            }
        }
    }
        /**
         * 画三角形 用于画坐标轴的箭头
         */

    private void drawTriangle(Canvas canvas, Point p1, Point p2, Point p3) {
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        // 绘制这个多边形
        canvas.drawPath(path, paint);
    }












}







