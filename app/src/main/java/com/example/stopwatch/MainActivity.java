package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView slotOne;
    ImageView slotTwo;
    ImageView slotThree;
    Button startButton;


    int time;
    int slot2num;
    int slot3num;
    int slot2speed;
    int slot3speed;
    int speed;
    Drawable cherry;
    Drawable grape;
    Drawable pear;
    Drawable strawberry;

    CountEvent event;
    EventTwo eventTwo;
    EventThree eventThree;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slotOne = findViewById(R.id.slotOne);
        slotTwo = findViewById(R.id.slotTwo);
        slotThree = findViewById(R.id.slotThree);
        startButton = findViewById(R.id.startButton);
        cherry = getDrawable(R.drawable.cherry);
        grape = getDrawable(R.drawable.grape);
        pear = getDrawable(R.drawable.pear);
        strawberry = getDrawable(R.drawable.strawberry);


        time = 0;
        slot2num = 3;
        slot3num = 3;
        speed = 600;
        slot2speed = 550;
        slot3speed = 550;


        event = new CountEvent();
        eventTwo = new EventTwo();
        eventThree = new EventThree();
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startButton.getText().equals("Start")) {
                    handler.postDelayed(event, speed);
                    handler.postDelayed(eventTwo, slot2speed);
                    handler.postDelayed(eventThree, slot3speed);
                    startButton.setText("Stop");
                } else {
                    handler.removeCallbacks(event);
                    handler.removeCallbacks(eventTwo);
                    handler.removeCallbacks(eventThree);
                    startButton.setText("Start");
                }
                if (time == slot2num && slot2num == slot3num) {
                    Toast t = Toast.makeText(getApplicationContext(), "You Win!!!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }


    private class CountEvent implements Runnable {
        @Override
        public void run() {
            handler.postDelayed(event, speed);
            time++;
            if (time == 4)
                time = 0;
            if (time == 0)
                slotOne.setImageDrawable(cherry);
            if (time == 1)
                slotOne.setImageDrawable(grape);
            if (time == 2)
                slotOne.setImageDrawable(pear);
            if (time == 3)
                slotOne.setImageDrawable(strawberry);
        }
    }

    private class EventTwo implements Runnable {
        @Override
        public void run() {
            handler.postDelayed(eventTwo, slot2speed);
            slot2num++;
            if (slot2num == 4)
                slot2num = 0;
            if (slot2num == 0)
                slotTwo.setImageDrawable(cherry);
            if (slot2num == 1)
                slotTwo.setImageDrawable(grape);
            if (slot2num == 2)
                slotTwo.setImageDrawable(pear);
            if (slot2num == 3)
                slotTwo.setImageDrawable(strawberry);
        }
    }

    private class EventThree implements Runnable {
        @Override
        public void run() {
            handler.postDelayed(eventThree, slot3speed);
            slot3num++;
            if (slot3num == 4)
                slot3num = 0;
            if (slot3num == 0)
                slotThree.setImageDrawable(cherry);
            if (slot3num == 1)
                slotThree.setImageDrawable(grape);
            if (slot3num == 2)
                slotThree.setImageDrawable(pear);
            if (slot3num == 3)
                slotThree.setImageDrawable(strawberry);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("time", time);
        super.onSaveInstanceState(savedInstanceState);
    }
}


 
