package com.example.testroomtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SecondPage extends AppCompatActivity {

    CheckBox ch_box;
    Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        init();
        onSubmitClick();
    }

    private void init()
    {
        ch_box = (CheckBox) findViewById(R.id.ch_box);
        bt_submit = (Button) findViewById(R.id.bt_submit);
    }

    private void onSubmitClick()
    {
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch_box.isChecked())
                {

                }
            }
        });
    }
}
