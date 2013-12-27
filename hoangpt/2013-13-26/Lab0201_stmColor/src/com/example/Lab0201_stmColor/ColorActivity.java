package com.example.Lab0201_stmColor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class ColorActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //get ui
        Button btnRed = (Button) findViewById(R.id.btnRed);
        Button btnBlue = (Button) findViewById(R.id.btnBlue);
        Button btnYellow = (Button) findViewById(R.id.btnYellow);

        RadioButton radRed = (RadioButton) findViewById(R.id.radRed);
        RadioButton radBlue = (RadioButton) findViewById(R.id.radBlue);
        RadioButton radYellow = (RadioButton) findViewById(R.id.radYellow);

        TextView color = (TextView) findViewById(R.id.color);

        //add event
        btnRed.setOnClickListener(new ColorListener(Color.RED, color));
        btnBlue.setOnClickListener(new ColorListener(Color.BLUE, color));
        btnYellow.setOnClickListener(new ColorListener(Color.YELLOW, color));

        radRed.setOnClickListener(new ColorListener(Color.RED, color));
        radBlue.setOnClickListener(new ColorListener(Color.BLUE, color));
        radYellow.setOnClickListener(new ColorListener(Color.YELLOW, color));
    }
}
