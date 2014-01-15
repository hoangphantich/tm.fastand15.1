package com.hoangphan.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class PuzzleView extends View {

  private GameActivity game;
  private int height;
  private int width;
  private int selX;
  private int selY;
  private Rect selRect = new Rect();


  public final String TAG = "Sudoku";


  public PuzzleView(Context context) {
    super(context);
    this.game = (GameActivity) context;
    setFocusable(true);
    setFocusableInTouchMode(true);
  }

  //responsive design
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    width = (int) (w / 9f);
    height = (int) (h / 9f);
    getRect(selX, selY, selRect);
    Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
    super.onSizeChanged(w, h, oldw, oldh);
  }

  private void getRect(int selX, int selY, Rect selRect) {
    selRect.set((int) (selX * width), (int) (selY * height), (int) (selX
      * width + width), (int) (selY * height + height));
  }


  @Override

  protected void onDraw(Canvas canvas) {
    // Draw the background...
    Paint background = new Paint();
    background.setColor(getResources().getColor(R.color.puzzle_background));
    canvas.drawRect(0, 0, getWidth(), getHeight(), background);


    // Draw the board...


    // Define colors for the grid lines
    Paint dark = new Paint();
    dark.setColor(getResources().getColor(R.color.puzzle_dark));
    Paint hilite = new Paint();
    hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
    Paint light = new Paint();
    light.setColor(getResources().getColor(R.color.puzzle_light));

    // Draw the minor grid lines, draw 90 lines
    for (int i = 0; i < 9; i++) {
      canvas.drawLine(0, i * height, getWidth(), i * height, light);
      canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
      canvas.drawLine(i * width, 0, i * width, getHeight(), light);
      canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
    }

    // Draw the major grid lines
    for (int i = 0; i < 9; i++) {
      if (i % 3 != 0)
        continue;
      canvas.drawLine(0, i * height, getWidth(), i * height, dark);

      canvas.drawLine(0, i * height + 1, getWidth(), i * height

        + 1, hilite);

      canvas.drawLine(i * width, 0, i * width, getHeight(), dark);

      canvas.drawLine(i * width + 1, 0, i * width + 1,

        getHeight(), hilite);

    }


    // Draw the numbers...


    // Define color and style for numbers

    Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);

    foreground.setColor(getResources().getColor(

      R.color.puzzle_foreground));

    foreground.setStyle(Paint.Style.FILL);

    foreground.setTextSize(height * 0.75f);

    foreground.setTextScaleX(width / height);

    foreground.setTextAlign(Paint.Align.CENTER);


    // Draw the number in the center of the tile

    Paint.FontMetrics fm = foreground.getFontMetrics();

    // Centering in X: use alignment (and X at midpoint)

    float x = width / 2;

    // Centering in Y: measure ascent/descent first

    float y = height / 2 - (fm.ascent + fm.descent) / 2;

    for (int i = 0; i < 9; i++) {

      for (int j = 0; j < 9; j++) {

        canvas.drawText(this.game.getTileString(i, j), i

          * width + x, j * height + y, foreground);

      }

    }


    // Draw the hints...


    // Pick a hint color based on #moves left

    Paint hint = new Paint();

    int c[] = {getResources().getColor(R.color.puzzle_hint_0),

      getResources().getColor(R.color.puzzle_hint_1),

      getResources().getColor(R.color.puzzle_hint_2),};

    Rect r = new Rect();

    for (int i = 0; i < 9; i++) {

      for (int j = 0; j < 9; j++) {

        int movesleft = 9 - game.getUsedTiles(i, j).length;

        if (movesleft < c.length) {

          getRect(i, j, r);

          hint.setColor(c[movesleft]);

          canvas.drawRect(r, hint);

        }

      }

    }


    // Draw the selection...


    Log.d(TAG, "selRect=" + selRect);

    Paint selected = new Paint();

    selected.setColor(getResources().getColor(

      R.color.puzzle_selected));

    canvas.drawRect(selRect, selected);
  }
}