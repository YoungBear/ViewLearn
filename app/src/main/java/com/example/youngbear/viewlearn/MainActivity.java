package com.example.youngbear.viewlearn;

import android.app.Activity;
import android.os.Bundle;

import com.example.youngbear.viewlearn.view.CustomView;

public class MainActivity extends Activity {

    private CustomView mCustomView;// 我们的自定义View

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) findViewById(R.id.main_cv);
        new Thread(mCustomView).start();
    }
}
