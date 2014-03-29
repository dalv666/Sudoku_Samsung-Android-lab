package com.dalv.sudoku_samsung_lab.sudoku;

import java.util.BitSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalv.sudoku_samsung_lab.sudokuGenerator.Sudoku;
import com.example.sudoku_1_samsunglab.R;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements OnClickListener {

	private TextView mResult;
	private LinearLayout mLlMain;
	private int[][] mGrid;
	private Button[][] mGridButton;
	private int coordinates[] = new int[2];
	private int mHints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Sudoku sudoku = new Sudoku();
		mHints = getIntent().getIntExtra("hints", 75);
		mGrid = sudoku.getGridSudokuByHard(mHints);
		mGridButton = new Button[9][9];
		mResult = (TextView) findViewById(R.id.tvResult);
		Button check = (Button) findViewById(R.id.btCheckValidation);
		check.setOnClickListener(this);
		mLlMain = (LinearLayout) findViewById(R.id.llMain);
		LayoutParams linLayoutParam = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		int buttonSize = width < height ? width / 9 : height / 9;
		for (int j = 0; j < 9; j++) {
			LinearLayout linLayout = new LinearLayout(this);
			linLayout.setOrientation(LinearLayout.HORIZONTAL);
			for (int i = 0; i < 9; i++) {
				Button btnNew = new Button(this);
				btnNew.setTextSize(14);
				btnNew.setTag(j + "" + i);
				btnNew.setOnClickListener(this);
				mGridButton[j][i] = btnNew;
				btnNew.setLayoutParams(new LinearLayout.LayoutParams(
						buttonSize, buttonSize, 1.0f));
				if (mGrid[j][i] != 0) {
					btnNew.setTextColor(Color.RED);
					btnNew.setText(mGrid[j][i] + "");
					btnNew.setClickable(false);
				}
				linLayout.setLayoutParams(linLayoutParam);
				linLayout.addView(btnNew);
			}
			mLlMain.addView(linLayout);
		}

	}

	public void changeValueCell(String value) {
		if (value.equals(getResources().getString(R.string.clean))) {
			value = "";
			mGrid[coordinates[0]][coordinates[1]] = 0;
		} else {
			mGrid[coordinates[0]][coordinates[1]] = Integer.valueOf(value);
		}
		mGridButton[coordinates[0]][coordinates[1]].setText(value);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null
				|| data.getStringExtra("value").equals(
						getResources().getString(R.string.back))) {
			return;
		}
		String name = data.getStringExtra("value");
		changeValueCell(name);
		super.onActivityResult(requestCode, resultCode, data);
	}

	public static boolean isValid(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			BitSet bsRow = new BitSet(9);
			BitSet bsColumn = new BitSet(9);
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0 || board[j][i] == 0)
					continue;
				if (bsRow.get(board[i][j] - 1) || bsColumn.get(board[j][i] - 1))
					return false;
				else {
					bsRow.set(board[i][j] - 1);
					bsColumn.set(board[j][i] - 1);
				}
			}
		}
		for (int rowOffset = 0; rowOffset < 9; rowOffset += 3) {
			for (int columnOffset = 0; columnOffset < 9; columnOffset += 3) {
				BitSet threeByThree = new BitSet(9);
				for (int i = rowOffset; i < rowOffset + 3; i++) {
					for (int j = columnOffset; j < columnOffset + 3; j++) {
						if (board[i][j] == 0)
							continue;
						if (threeByThree.get(board[i][j] - 1))
							return false;
						else
							threeByThree.set(board[i][j] - 1);
					}
				}
			}
		}
		return true;
	}

	private boolean isHaveEmptySell() {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (mGrid[j][i] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		if ((((Button) v).getText()).equals(getResources().getString(
				R.string.check))) {
			if (!isHaveEmptySell()) {
				if (isValid(mGrid)) {
					mResult.setTextColor(Color.GREEN);
					mResult.setText(getResources().getString(R.string.correct));
				} else {
					mResult.setTextColor(Color.RED);
					mResult.setText(getResources()
							.getString(R.string.incorrect));
				}
			} else {
				mResult.setTextColor(Color.BLACK);
				mResult.setText(getResources().getString(R.string.notFill));
			}
		} else {
			mResult.setText("");
			char x = ((String) v.getTag()).charAt(0);
			char y = ((String) v.getTag()).charAt(1);
			coordinates[0] = Character.getNumericValue(x);
			coordinates[1] = Character.getNumericValue(y);
			Intent intent = new Intent(this, NumBoardActivity.class);
			intent.putExtra("isEmpty",
					mGridButton[coordinates[0]][coordinates[1]].getText()
							.equals(""));
			startActivityForResult(intent, 1);
		}
	}
}
