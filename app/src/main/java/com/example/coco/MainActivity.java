package com.example.coco;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.mainpage);            /// name of this paga
        super.onCreate(savedInstanceState);
    
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        /// find the view that shows numbers and set the view ON OnClickListener
        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext() , "Open the shit of numbers" ,Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, NumbersActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        /// find the view that shows family and set the view ON OnClickListener
        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext() , "Open the shit of family" ,Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, FamilyActivity.class);
                //      myIntent.putExtra("key", "hi" ); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        /// find the view that shows colors and set the view ON OnClickListener
        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext() , "Open the shit of colors" ,Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, ColorsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        /// find the view that shows phrases and set the view ON OnClickListener
        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext() , "Open the shit of phrases" ,Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
