package com.example.kishorami.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Students extends AppCompatActivity {


    TextView textViewPersons ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        Firebase.setAndroidContext(this);
        //Creating firebase object
        Firebase ref = new Firebase(Config.FIREBASE_URL);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String string= "";
                int totalAttendance=0;
                double mark = 0.0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    Person person = postSnapshot.getValue(Person.class);
                    textViewPersons= (TextView) findViewById(R.id.textViewPersons);
                    List<Person> lst = new ArrayList<Person>(); // Result will be holded Here


                        lst.add(person) ; //add result into array list


                    for(Person data:lst){
                        int [] temp = data.Attendence;
                        String attend="";
                    for(int i=0;i<temp.length;i++){
                        attend= attend +"Day"+ (i+1)+": "+temp[i]+"\n";
                        if(temp[i]==1){
                            totalAttendance++;
                        }
                    }
                        if(totalAttendance >= 24){
                            mark = 4.5;
                        }else if(totalAttendance == 22){
                            mark = 4.0;
                        }else if(totalAttendance == 21){
                            mark = 3.0;
                        }else if(totalAttendance == 20){
                            mark = 2.5;
                        }else if(totalAttendance == 19){
                            mark = 1.5;
                        }else if(totalAttendance == 18){
                            mark = 1.0;
                        }else{
                            mark=0.0;
                        }
                        Log.e("Data: ","Name: "+data.getName()+"\nID: "+data.getId()+"\nAttandence: "+attend+"\n\n");
                        string =string+"Name: "+data.getName()+"\nID: "+data.getId()+"\nAttandence:\n "+attend+"\nTotal Attendance: "+ totalAttendance+ "\nMark Obtain: "+mark+"\n\n";
                        totalAttendance=0;
                        mark=0.0;

                    }
                    textViewPersons.setText(string);

//                    int [] temp = person.Attendence;
//                    String attend="";
//                    for(int i=0;i<temp.length;i++){
//                        attend= attend +"Day"+ (i+1)+": "+temp[i]+"\n";
//                    }
//                    //Adding it to a string
//                    String string = "Name: "+person.getName()+"\nAddress: "+person.getId()+"\nAttandence: "+attend+"\n\n";

//                    Log.e("String: ",string);
//
//                    textViewPersons= (TextView) findViewById(R.id.textViewPersons);
//                    //Displaying it on textview
//                    textViewPersons.setText(string);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });





    }
}
