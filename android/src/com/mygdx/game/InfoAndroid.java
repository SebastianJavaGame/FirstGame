package com.mygdx.game;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Sebastian on 2017-07-24.
 */

public class InfoAndroid implements DialogInfo {
    private Activity context;

    public InfoAndroid(Activity context) {
        this.context = context;
    }

    public void showDialog(final String message) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }


}
