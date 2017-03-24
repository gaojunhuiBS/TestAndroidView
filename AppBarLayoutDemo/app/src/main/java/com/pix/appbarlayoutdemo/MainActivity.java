package com.pix.appbarlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Button btn = (Button) v;
        if(btn.getText().equals("Scroll Mode")) {
            startActivity(new Intent(this,AppBarLayoutScrollActivity.class));
        }

        if(btn.getText().equals("Scroll | EnterAlways Mode")) {
            startActivity(new Intent(this,ScrollEnterAlwaysActivity.class));
        }

        if(btn.getText().equals("Scroll | ExitUntilCollapsed")) {
            startActivity(new Intent(this,ScrollExitUntilCollapsedActivity.class));
        }

        if(btn.getText().equals("Scroll|EnerAlways|enterAlwaysCollapsed")) {
            startActivity(new Intent(this,EnterAlwaysCollapsedActivity.class));
        }

    }

}
