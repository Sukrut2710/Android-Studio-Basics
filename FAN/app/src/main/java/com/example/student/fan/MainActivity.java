package com.example.student.fan;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private SeekBar sBar;
    private TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sBar = (SeekBar) findViewById(R.id.seekBar3);
        tView = (TextView) findViewById(R.id.textView2);
        tView.setText("OFF");
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(progress == 0)
                {
                    tView.setText("OFF");
                }
                else
                {
                    tView.setText(""+progress+"");
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}

