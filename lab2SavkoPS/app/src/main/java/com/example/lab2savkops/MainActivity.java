package com.example.lab2savkops;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView gestureTextView;
    private TextView multiTouchTextView;
    private View touchIndicator;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureTextView = findViewById(R.id.gestureTextView);
        multiTouchTextView = findViewById(R.id.multiTouchTextView);
        touchIndicator = findViewById(R.id.touchIndicator);
        gestureDetector = new GestureDetector(this, new GestureListener());
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);

        // Обновляем позицию индикатора касания
        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            touchIndicator.setX(event.getX() - touchIndicator.getWidth() / 2);
            touchIndicator.setY(event.getY() - touchIndicator.getHeight() / 2);
            touchIndicator.setVisibility(View.VISIBLE);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            touchIndicator.setVisibility(View.INVISIBLE);
        }

        // Обработка многопальцевого жеста
        int pointerCount = event.getPointerCount();
        if (pointerCount > 1) {
            multiTouchTextView.setText("Количество пальцев: " + pointerCount);
        } else {
            multiTouchTextView.setText("Многопальцевый жест");
        }

        return super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            gestureTextView.setText("Двойной тап");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            gestureTextView.setText("Одиночный тап");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            String direction = getFlingDirection(e1, e2);
            gestureTextView.setText("Смахивание: " + direction);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            gestureTextView.setText("Долгое нажатие");
        }

        private String getFlingDirection(MotionEvent e1, MotionEvent e2) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();
            String direction;

            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                direction = deltaX > 0 ? "вправо" : "влево";
            } else {
                direction = deltaY > 0 ? "вниз" : "вверх";
            }
            return direction;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (detector.getScaleFactor() > 1) {
                gestureTextView.setText("Увеличение");
            } else {
                gestureTextView.setText("Уменьшение");
            }
            return true;
        }
    }
}