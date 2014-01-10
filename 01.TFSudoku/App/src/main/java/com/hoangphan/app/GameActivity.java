package com.hoangphan.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends Activity {

  public static final String DIFFICULTY = "game_difficulty";
  public static final int EASY_DIFFICULTY = 1;
  public static final int MEDIUM_DIFFICULTY = 2;
  public static final int HARD_DIFFICULTY = 3;


  private int[] puzzle;
  private final String easyPuzzle =
    "360000000004230800000004200" +
      "070460003820000014500013020" +
      "001900000007048300000000045";
  private final String mediumPuzzle =
    "650000070000506000014000005" +
      "007009000002314700000700800" +
      "500000630000201000030000097";
  private final String hardPuzzle =
    "009000000080605020501078000" +
      "000000700706040102004000000" +
      "000720903090301080000000600";

  //View
  private PuzzleView puzzleView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_game);

    //get Intent from Main
    Intent i = getIntent();
    int level = i.getIntExtra(DIFFICULTY, EASY_DIFFICULTY);

    //get database --> build Puzzle View
    //example 900005001+0024 --> array
    puzzle = getPuzzle(level);

    //calculateUsedTiles();
    puzzleView = new PuzzleView(this);
    //parse to puzzle view -> build ui
    setContentView(puzzleView);
    puzzleView.requestFocus();


  }

  private int[] getPuzzle(int level) {
    String puz;

    //switch level
    switch (level) {
      case HARD_DIFFICULTY:
        puz = hardPuzzle;
        break;
      case MEDIUM_DIFFICULTY:
        puz = mediumPuzzle;
        break;
      case EASY_DIFFICULTY:
      default:
        puz = easyPuzzle;
        break;
    }

    //parse string build array [][]
    return fromPuzzleString(puz);

  }

  private int[] fromPuzzleString(String strPuzzle) {
    int[] puz = new int[strPuzzle.length()];
    for (int i = 0; i < puz.length; i++) {
      puz[i] = strPuzzle.charAt(i) - '0';
    }
    return puz;
  }

  public String getTileString(int i, int j) {
    return "";
  }

  public int[] getUsedTiles(int i, int j) {
    return new int[0];
  }
}
