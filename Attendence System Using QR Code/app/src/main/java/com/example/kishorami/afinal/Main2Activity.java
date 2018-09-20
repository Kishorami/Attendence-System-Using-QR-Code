package com.example.kishorami.afinal;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private String LOG_TAG = "GenerateQRCode";
    public  String tempId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Firebase.setAndroidContext(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {




            case R.id.button1:
                EditText qrInputName = (EditText) findViewById(R.id.qrInputName);
                EditText qrInputId = (EditText) findViewById(R.id.qrInputId);
                tempId =  qrInputId.getText().toString();
                //Creating firebase object
                Firebase ref = new Firebase(Config.FIREBASE_URL);

                //Creating Person object
                Person Student = new Person();

                //Adding values
                Student.setName(qrInputName.getText().toString());
                Student.setId(qrInputId.getText().toString());

                //Storing values to firebase
                ref.child(qrInputId.getText().toString() ).setValue(Student);



                String qrInput= qrInputName.getText().toString() +","+ qrInputId.getText().toString();
                String qrInputText = qrInput;

                //Value event listener for realtime data update
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot snapshot) {
//                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                            //Getting the data from snapshot
//                            Person person = postSnapshot.getValue(Person.class);
//                            Firebase ref2 = new Firebase(Config.FIREBASE_URL);
//                            //geting array
//                           // int [] tempAttendence = person.Attendence;
//                            person.Attendence[7]= 1;
//
//                            if((person.getId()).equals(tempId)) {
//                                ref2.child(person.getId()).setValue(person);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//                        System.out.println("The read failed: " + firebaseError.getMessage());
//                    }
//                });




                Log.v(LOG_TAG, qrInputText);

                //Find screen size
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3/4;

                //Encode with a QR Code image
                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText,
                        null,
                        Contents.Type.TEXT,
                        BarcodeFormat.QR_CODE.toString(),
                        smallerDimension);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
                    myImage.setImageBitmap(bitmap);

                    String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                    File myDir = new File(root + "/saved_images");
                    myDir.mkdirs();
                    String fname = "Image-" + qrInputText + ".jpg";
                    File file = new File(myDir, fname);
                    if (file.exists())
                        file.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    MediaScannerConnection.scanFile(this, new String[] { file.toString() }, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> uri=" + uri);
                                }
                            });




                } catch (WriterException e) {
                    e.printStackTrace();
                }


                break;



        }
    }






}
