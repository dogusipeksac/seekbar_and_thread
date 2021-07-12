package com.example.seekbar_and_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    int maxCounter=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textViewCounter);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(maxCounter);


    }
    boolean isRunning=false;
    int counterUp=0;



    public void startButton(View view) {
        isRunning=true;
        myThread tread=new myThread();
        tread.start();
    }

    public void stopButton(View view) {
        isRunning=false;
    }

    class  myThread extends Thread{
        @Override
        public  void run(){
            while (isRunning){
                if(counterUp<=maxCounter){
                    //call ui
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(counterUp);
                            textView.setText("Counter="+counterUp);
                        }
                    });
                    counterUp=counterUp+1;
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}