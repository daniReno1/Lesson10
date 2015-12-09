package com.example.reno.example9;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class lesson9lab extends Activity {

    private GopherPokeView gpv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        gpv = new GopherPokeView(this);

        FrameLayout outerLayout = new FrameLayout(this);
        outerLayout.addView(gpv);

        setContentView(outerLayout);
    }

    @Override
    protected void onPause()
    {

        String tempscore=Integer.toString( gpv.getScore());




        try {
            FileOutputStream fOut = openFileOutput("GopherPokeData",MODE_WORLD_READABLE);
            fOut.write(tempscore.getBytes());
            fOut.close();
        }

        catch (Exception e) {

            e.printStackTrace();
        }

        super.onPause();
        gpv.killThread();
    }
    @Override
    protected void onResume()
    {

        try{
            FileInputStream fIn = openFileInput("GopherPokeData");
            int readInteger;
            String retrievedHighScore="";

            while( (readInteger = fIn.read()) != -1){
                retrievedHighScore = retrievedHighScore + Character.toString((char)readInteger);
            }
            gpv.setScore(Integer.valueOf(retrievedHighScore) );
        }
        catch(Exception e){
        }

        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        gpv.onDestroy();
    }
}
