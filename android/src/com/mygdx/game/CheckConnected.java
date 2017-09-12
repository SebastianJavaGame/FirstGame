package com.mygdx.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Sebastian on 2017-09-10.
 */

public class CheckConnected extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        startActivity(new Intent(getApplicationContext(), AndroidLauncher.class));
        if(!isConnected()){
            setContentView(R.layout.layout_connection);
        }else{
            startActivity(new Intent(getApplicationContext(), AndroidLauncher.class));
            finish();
        }
    }

    public void listenerRestart(View v){
        if(!isConnected()){
            Toast.makeText(getApplicationContext(), "Nie nawiązano połączenia", Toast.LENGTH_LONG).show();
        }else{
            startActivity(new Intent(getApplicationContext(), AndroidLauncher.class));
            finish();
        }
    }

    public void listenerExit(View v){
        System.exit(0);
    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null)
            return false;
        else
            return true;
    }
}
