package com.example.Lab0201_stmColor;

import android.view.View;
import android.widget.TextView;

/**
 * Created by hoangpt on 12/27/13.
 */
public class ColorListener implements View.OnClickListener {

    int color;
    TextView view;

    public ColorListener(int color, TextView view) { /** pass value */
        this.color = color;
        this.view = view;
    }

    public void onClick(View v) {
        view.setBackgroundColor(color);
    }
}
