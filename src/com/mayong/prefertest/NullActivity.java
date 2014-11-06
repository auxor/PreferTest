package com.mayong.prefertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NullActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.null_activity);

        TextView textView = (TextView)findViewById(R.id.label);
        textView.setText(getIntent().toString());
    }
}
