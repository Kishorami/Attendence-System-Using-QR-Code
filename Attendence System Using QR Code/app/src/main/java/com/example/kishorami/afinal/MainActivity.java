package com.example.kishorami.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("Teacher") &&
                        ed2.getText().toString().equals("teacher")) {

                    Intent myIntent = new Intent(MainActivity.this, Choice.class);
                     //Optional parameters
                    startActivity(myIntent);





                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Password",Toast.LENGTH_SHORT).show();




                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
