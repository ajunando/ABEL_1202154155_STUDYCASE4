package com.android.kelompok3.abel_1202154155_modul4;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button a, b;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (Button) findViewById(R.id.pencari);
        b = (Button) findViewById(R.id.list);

        a.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent ab = new Intent(MainActivity.this,SearchImage.class);
                startActivity(ab);
            }

        });

        b.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent ac = new Intent(MainActivity.this, ListName.class);
                startActivity(ac);
            }


        });
    }
}