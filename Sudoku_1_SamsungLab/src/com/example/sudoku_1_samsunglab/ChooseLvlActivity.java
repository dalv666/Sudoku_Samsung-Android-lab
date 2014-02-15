package com.example.sudoku_1_samsunglab;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ChooseLvlActivity extends FragmentActivity implements OnClickListener, OnSeekBarChangeListener{

	private SeekBar seekBar;
	private TextView showBar;
	private Button btChooseHint;
	private int countHint;

	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.lvl_chooser_activity);
		btChooseHint = (Button) findViewById(R.id.btChooseLvl);
		btChooseHint.setOnClickListener(this);
		seekBar = (SeekBar) findViewById(R.id.skLvlChooser);
		showBar = (TextView) findViewById(R.id.tvShowBar);
		showBar.setText(25+seekBar.getProgress()+"");
		countHint = 25+seekBar.getProgress();
		seekBar.setMax(60);
		seekBar.setOnSeekBarChangeListener(this);
	}


	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		countHint = arg1 + 20;
		showBar.setText(countHint + "");
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("hints", 81-countHint);
		startActivity(intent);
	}



	
}
