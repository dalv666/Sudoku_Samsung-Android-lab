package com.example.sudoku_1_samsunglab;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ChooseLvlActivity extends FragmentActivity implements OnClickListener, OnSeekBarChangeListener{

	private SeekBar mSeekBar;
	private TextView mShowBar;
	private Button mChooseHint;
	private int mCountHint;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.lvl_chooser_activity);
		mChooseHint = (Button) findViewById(R.id.btChooseLvl);
		mChooseHint.setOnClickListener(this);
		mSeekBar = (SeekBar) findViewById(R.id.skLvlChooser);
		mShowBar = (TextView) findViewById(R.id.tvShowBar);
		mShowBar.setText(25+mSeekBar.getProgress()+"");
		mCountHint = 25+mSeekBar.getProgress();
		mSeekBar.setMax(60);
		mSeekBar.setOnSeekBarChangeListener(this);
	}


	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		mCountHint = arg1 + 20;
		mShowBar.setText(mCountHint + "");
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
		intent.putExtra("hints", 81-mCountHint);
		startActivity(intent);
	}



	
}
