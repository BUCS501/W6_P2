package com.example.w6_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get img from values res -> randomise -> setNewImg
                TypedArray imgs = getResources().obtainTypedArray(R.array.driver_images);
                Random rand = new Random();
                int rndInt = rand.nextInt(imgs.length());
                int resID = imgs.getResourceId(rndInt, 0);
                image.setImageResource(resID);
            }
        });
    }
}