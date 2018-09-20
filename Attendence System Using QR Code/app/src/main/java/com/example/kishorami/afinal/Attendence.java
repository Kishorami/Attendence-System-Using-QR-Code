package com.example.kishorami.afinal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Attendence extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    public int attendenceDate = 0;
    public String name="";
    public String id="";

    public String tempResult="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        Firebase.setAndroidContext(this);
    }

    public void QrScanner(View view){


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attendance Taken");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();


        Date da = new Date();
        attendenceDate = da.date;



        tempResult = rawResult.getText();
        //System.out.println(tempResult);
        String[] separated = tempResult.split(",");

        name = separated[0];
        id = separated[1];
        Log.e("handler", name);
        Log.e("handler", id);
        // taking attendance
        Intent i = getIntent();
        attendenceDate = i.getIntExtra("date",0);
        Log.e("handler", (""+attendenceDate));

        Firebase ref = new Firebase(Config.FIREBASE_URL);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    Person person = postSnapshot.getValue(Person.class);
                    Firebase ref2 = new Firebase(Config.FIREBASE_URL);
                    //geting array
                    // int [] tempAttendence = person.Attendence;
                    person.Attendence[attendenceDate-1]= 1;

                    if((person.getId()).equals(id)) {
                        ref2.child(person.getId()).setValue(person);
                    }

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });








        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }

    public int getDate() {
        return attendenceDate;
    }

    public void setDate(int day) {
        this.attendenceDate = day;
    }



}
