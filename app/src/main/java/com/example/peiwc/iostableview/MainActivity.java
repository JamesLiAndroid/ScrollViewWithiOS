package com.example.peiwc.iostableview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements MyScrollView.ScrollViewListener {
    private TextView title0, title1, title2, title3, title;
    private long topDistance0, topDistance1, topDistance2, topDistance3, height;
    private MyScrollView scrollView;

    // 行进距离
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title0 = (TextView) findViewById(R.id.title0);
        title1 = (TextView) findViewById(R.id.title1);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);
        title = (TextView) findViewById(R.id.title);
        scrollView = (MyScrollView) findViewById(R.id.scv);
        scrollView.setScrollViewListener(this);
    }

    // Activity加载完毕后立即进行
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.d("TAG", "当前window获取焦点");
            topDistance0 = title0.getTop();
            topDistance1 = title1.getTop();
            topDistance2 = title2.getTop();
            topDistance3 = title3.getTop();//按钮左上角相对于父view（LinerLayout）的y坐标
            height = title.getMeasuredHeight();
            Log.d("", "=getMeasuredHeight=" + height);
            Log.i("", "=topDistance0=" + topDistance0);
            Log.i("", "=topDistance1=" + topDistance1);
            Log.i("", "=topDistance2=" + topDistance2);
            Log.i("", "=topDistance3=" + topDistance3);
            Log.i("", "=topDistance3=" + topDistance3);
            Log.i("", "=height=" + height);
        } else {
            Log.d("TAG", "当前window失去焦点");
        }
    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        Log.i("TAG", "=topDistance=distance=" + "x=" + x + ";y=" + y + ";oldx=" + oldx + ";oldy" + oldy);
        distance = y;
        distancePlace(topDistance0, topDistance1);
        distancePlaceLayout(topDistance1, topDistance0);
        distancePlace(topDistance1, topDistance2);
        distancePlaceLayout(topDistance2, topDistance1);
        distancePlace(topDistance2, topDistance3);
        distancePlaceLayout(topDistance3, topDistance2);

        if (distance > topDistance3) {
            title.layout(0, 0, title.getRight(), (int) height);
            showText(topDistance3);
        }
    }

    private void distancePlaceLayout(long topDistance1, long topDistance0) {
        if (distance > topDistance1 - height && distance < topDistance1) {
            // 下一个title顶到title的顶部，以后进行滑动
            title.layout(0, (int) (topDistance1 - distance - height), title.getRight(), (int) (topDistance1 - distance));
            showText(topDistance0);
        }
    }

    private void distancePlace(long topDistance0, long topDistance1) {
        if (distance >= topDistance0 && distance < topDistance1 - height) {
            // 当前滑动在内容区内滑动，确定当前的title定位
            title.layout(0, 0, title.getRight(), (int) height);
            showText(topDistance0);
        }
    }

    private void showText(long topDistance) {
        if (topDistance == topDistance0) {
            title.setText("条目1");
            title.setBackground(getResources().getDrawable(R.color.colorZi));
        }
        if (topDistance == topDistance1) {
            title.setText("条目2");
            title.setBackground(getResources().getDrawable(R.color.colorRed));
        }
        if (topDistance == topDistance2) {
            title.setText("条目3");
            title.setBackground(getResources().getDrawable(R.color.colorYel));
        }
        if (topDistance == topDistance3) {
            title.setText("条目4");
            title.setBackground(getResources().getDrawable(R.color.colorBlue));
        }


    }
}
