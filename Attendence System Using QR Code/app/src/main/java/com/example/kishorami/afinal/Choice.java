package com.example.kishorami.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Choice extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        Button b3= (Button)findViewById(R.id.button3);;
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
        {
            Intent intent = new Intent(Choice.this, Main2Activity.class);
            startActivity(intent);

        }
        });


        Button b4= (Button)findViewById(R.id.button4);;
        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Choice.this, Date.class);
                startActivity(intent);

            }
        });

        Button b5= (Button)findViewById(R.id.button5);;
        b5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Choice.this, Students.class);
                startActivity(intent);

            }
        });

    }
}
