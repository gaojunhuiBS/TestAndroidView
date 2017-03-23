package com.pix.coordinatorlayoutdemo1;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

class MainActivity extends AppCompatActivity {
    FloatingActionButton faButtonButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        faButtonButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void onClick(View v) {
        Snackbar.make(v,"FAB", Snackbar.LENGTH_LONG)
                .setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //这里的单击事件代表点击消除Action后的响应事件

                    }
                })
                .show();
    }
}
