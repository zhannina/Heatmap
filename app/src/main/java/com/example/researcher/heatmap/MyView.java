package com.example.researcher.heatmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

    Paint paint, paint1;
    ArrayList<Point> points = new ArrayList<>();
    private int pointsPos; //Which point we will be drawing
    public float x;
    public float y;
    public float radius;
    public float width;
    public float height;
    public HashSet<Integer> positions = new HashSet<>();

    SharedPreferences prefs;


    // camilo said to use shared preferences to keep pointspos?

    public MyView(Context context) {
        super(context);
        x = this.getX();
        y = this.getY();
        prefs = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        radius = prefs.getFloat("radius", 0);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        x = this.getX();
        y = this.getY();
        prefs = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        x = this.getX();
        y = this.getY();
        prefs = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        init();
    }

    private void init() {
        // Load attributes
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStrokeWidth(9);
        paint1.setStyle(Paint.Style.STROKE);

        populateCirclesArrayList();
        positions.clear();

        Random r1 = new Random(System.nanoTime());
        pointsPos = r1.nextInt(points.size()); //between 0 and points.length
        positions.add(pointsPos);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint1.setStyle(Paint.Style.STROKE);

        canvas.drawColor(Color.WHITE);

        canvas.drawCircle(points.get(pointsPos).x, points.get(pointsPos).y, radius, paint);
        canvas.drawPoint(points.get(pointsPos).x, points.get(pointsPos).y, paint1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX, touchY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:
                //Check if the point press is within the circle
                if(contains(event, points.get(pointsPos))){

                    Random r = new Random(System.nanoTime());
                    pointsPos = r.nextInt(points.size()); //between 0 and points.length

                    if (positions.size() == points.size()) {

                        Toast.makeText(this.getContext(), "positions full", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        positions.add(pointsPos);
                        postInvalidate();
                        Log.d("hur point ", "" + pointsPos);
                        Log.d("hur hashSet ", "" + positions);
                        Log.d("hur hash size ", "" + positions.size());
                        touchX = event.getX();
                        touchY = event.getY();
                        Toast.makeText(this.getContext(), "x: " + touchX + ", y:" + touchY, Toast.LENGTH_SHORT).show();
                    }
                }


            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }
        postInvalidate();
        return true;

    }

    private boolean contains(MotionEvent event, Point point) {
        float xTouch = event.getX();
        float yTouch = event.getY();
        if ((xTouch - point.x) * (xTouch - point.x) + (yTouch - point.y) * (yTouch - point.y) <= radius * radius) {
            return true;
        }
        else {
            return false;
        }
    }

    public void populateCirclesArrayList(){
        width = prefs.getFloat("maxX", 0);
        height = prefs.getFloat("maxY", 0);
        Log.d("screen",prefs.getFloat("maxX", 0)+"");
        Log.d("screen", "x: " + width + " y: " + height);
        points.clear();
        points.add(new Point((int) (width/8), (int) (height/12)));
        points.add(new Point((int) (3*width/8), (int) (height/12)));
        points.add(new Point((int) (5 * width/8), (int) (height/12)));
        points.add(new Point((int) (7 * width / 8), (int) (height / 12)));

        points.add(new Point((int) (width/8), (int) (3*height/12)));
        points.add(new Point((int) (3*width/8), (int) (3*height/12)));
        points.add(new Point((int) (5 * width/8), (int) (3*height/12)));
        points.add(new Point((int) (7*width/8), (int) (3*height/12)));

        points.add(new Point((int) (width/8), (int) (5*height/12)));
        points.add(new Point((int) (3*width/8), (int) (5*height/12)));
        points.add(new Point((int) (5 * width/8), (int) (5*height/12)));
        points.add(new Point((int) (7*width/8), (int) (5*height/12)));

        points.add(new Point((int) (width/8), (int) (7*height/12)));
        points.add(new Point((int) (3*width/8), (int) (7*height/12)));
        points.add(new Point((int) (5 * width/8), (int) (7*height/12)));
        points.add(new Point((int) (7*width/8), (int) (7*height/12)));

        points.add(new Point((int) (width/8), (int) (9*height/12)));
        points.add(new Point((int) (3*width/8), (int) (9*height/12)));
        points.add(new Point((int) (5 * width/8), (int) (9*height/12)));
        points.add(new Point((int) (7*width/8), (int) (9*height/12)));

        points.add(new Point((int) (width/8), (int) (11*height/12)));
        points.add(new Point((int) (3*width/8), (int) (11*height/12)));
        points.add(new Point((int) (5 * width/8), (int) (11*height/12)));
        points.add(new Point((int) (7*width/8), (int) (11*height/12)));

    }

}
