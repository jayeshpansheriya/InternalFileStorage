package com.example.jayeshp.internalstroage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        btn1=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file=ed1.getText().toString();
                String data=ed2.getText().toString();


                try {
                    FileOutputStream outputStream=openFileOutput(file,Context.MODE_PRIVATE);
                    outputStream.write(data.getBytes());
                    outputStream.close();
                    ed2.setText("");

                    Toast.makeText(MainActivity.this, "Saved..", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file=ed1.getText().toString();
                StringBuffer stringBuffer=new StringBuffer();
                try {
                    BufferedReader reader=new BufferedReader(new InputStreamReader(openFileInput(file)));
                    String s;

                    while ((s=reader.readLine())!=null){
                        stringBuffer.append(s);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String data1=stringBuffer.toString();
                ed2.setText(data1);
                Toast.makeText(getApplicationContext(),"Read...",Toast.LENGTH_LONG).show();
            }
        });
    }
}
