package com.example.kishorami.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Date extends AppCompatActivity {

    public int date=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Button buttonScan= (Button)findViewById(R.id.buttonScan);;
        buttonScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText inputDate = (EditText) findViewById(R.id.editText3);
                String temoDate= inputDate.getText().toString();
                date = Integer.parseInt(temoDate);

                Attendence dateToSet = new Attendence();

                dateToSet.setDate(date);

                Intent intent = new Intent(Date.this, Attendence.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });

    }
}
