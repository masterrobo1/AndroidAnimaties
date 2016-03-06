package com.rocdev.android.animaties;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView oneDollar;
    ImageView oneHundred;
    boolean isDollar;
    long duurAnimatie;
    SeekBar seekBar;
    RadioButton fadeButton;
    RadioButton rotateButton;
    RadioButton translateButton;
    boolean isFade;
    boolean isRotate;
    boolean isRotateAndScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oneDollar = (ImageView) findViewById(R.id.oneDollar);
        oneHundred = (ImageView) findViewById(R.id.oneHundred);

        seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duurAnimatie = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fadeButton = (RadioButton) findViewById(R.id.fadeRadioButton);
        translateButton = (RadioButton)findViewById(R.id.translateRadioButton);
        rotateButton = (RadioButton)findViewById(R.id.rotateRadioButton);

        fadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = true;
                isRotate = false;
                isRotateAndScale = false;

                if (isDollar){
                    oneHundred.setAlpha(0f);
                    oneHundred.setTranslationX(0f);
                    oneHundred.setScaleX(1f);
                    oneHundred.setScaleY(1f);
                }else{
                    oneDollar.setAlpha(0f);
                    oneDollar.setTranslationX(0f);
                    oneDollar.setScaleX(1f);
                    oneDollar.setScaleY(1f);
                }
            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFade = false;
                isRotate = true;
                isRotateAndScale = false;

                if (isDollar){
                    oneHundred.setAlpha(1f);
                    oneHundred.setTranslationX(-1000f);
                    oneHundred.setScaleX(1f);
                    oneHundred.setScaleY(1f);
                }else{
                    oneDollar.setAlpha(1f);
                    oneDollar.setTranslationX(1000f);
                    oneDollar.setScaleX(1f);
                    oneDollar.setScaleY(1f);
                }
            }
        });

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    isFade = false;
                    isRotate = false;
                    isRotateAndScale = true;

                    if (isDollar){
                        oneHundred.setAlpha(1f);
                        oneHundred.setTranslationX(0f);
                        oneHundred.setScaleX(0f);
                        oneHundred.setScaleY(0f);
                    }else{
                        oneDollar.setAlpha(1f);
                        oneDollar.setTranslationX(0f);
                        oneDollar.setScaleX(0f);
                        oneDollar.setScaleY(0f);
                    }
                }
            });


        isFade = true;
        fadeButton.setChecked(true);
        isDollar = true;
        duurAnimatie = 2000l;
        seekBar.setProgress((int)duurAnimatie);
        oneHundred.setAlpha(0f);
    }



    public void animate(View v) {
        if (isFade){
            fade();
        }else if(isRotate){
            translate();
        }else {
            rotateAndScale();
        }
        isDollar = !isDollar;
  }

    private void fade(){
        if (isDollar) {
            oneDollar.animate().alpha(0f).setDuration(duurAnimatie);
            oneHundred.animate().alpha(1f).setDuration(duurAnimatie);

        } else {
            oneDollar.animate().alpha(1f).setDuration(duurAnimatie);
            oneHundred.animate().alpha(0f).setDuration(duurAnimatie);
        }
    }

    private void translate(){
        if(isDollar){
            oneDollar.animate().translationX(1000l).setDuration(duurAnimatie);
            oneHundred.animate().translationX(0f).setDuration(duurAnimatie);
        }else{
            oneDollar.animate().translationX(0l).setDuration(duurAnimatie);
            oneHundred.animate().translationX(-1000f).setDuration(duurAnimatie);
        }
    }

    private void rotateAndScale(){
        if(isDollar){
            oneDollar.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
            oneHundred.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
        }else{
            oneDollar.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(duurAnimatie);
            oneHundred.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(duurAnimatie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
