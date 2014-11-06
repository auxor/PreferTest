package com.mayong.prefertest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PreferTest extends Activity implements View.OnClickListener {
    public static enum Type {Desktop, Browser, SmsTo, Tel, Camera, Picture, Music, Email};
    private LinearLayout mParent;
    private String[] mText;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mParent = (LinearLayout) findViewById(R.id.parent);
        mText = this.getResources().getStringArray(R.array.app_preferred_type);

        addButtons();
    }

    private void addButtons() {
        Type[] all = Type.values();
        int N = all.length;
        Button button = null;
        for (int i = 0; i < N; i++) {
            button = new Button(this);
            button.setText(mText[i]);
            button.setId(i);
            button.setOnClickListener(this);
            mParent.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        Type type = Type.values()[v.getId()];
        try {
            startActivity(getIntent(type));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
        }
    }

    private static Intent getIntent(Type type) {
        Intent intent = new Intent();
        switch (type) {
            case Browser:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://m.baidu.com"));
                break;
            case Desktop:
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                break;
            case SmsTo:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:10086"));
                break;
            case Tel:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                break;
            case Camera:
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
            case Picture:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://sdcard/test.jpeg"), "image/*");
                break;
            case Music:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file:///sdcard/test.mp3"), "audio/mpeg");
                break;
            case Email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto://mayong@baiyi_mobile.com"));
                break;
        }
        return intent;
    }
}
