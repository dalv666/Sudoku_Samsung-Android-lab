package com.example.sudoku_1_samsunglab;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NumBoardActivity extends FragmentActivity implements OnClickListener{

	boolean isEmpty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		isEmpty = intent.getBooleanExtra("isEmpty", true);
		setContentView(R.layout.num_activity);
		((Button) findViewById(R.id.btNum1)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum2)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum3)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum4)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum5)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum6)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum7)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum8)).setOnClickListener(this);
		((Button) findViewById(R.id.btNum9)).setOnClickListener(this);
		((Button) findViewById(R.id.btBuck)).setOnClickListener(this);
		if(!isEmpty){
			Button clean = ((Button) findViewById(R.id.btClean));
			clean.setOnClickListener(this);
			clean.setVisibility(0);
		}
	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra("value", ((Button)v).getText());
		setResult(RESULT_OK, intent);
		finish();
	}
	

}

