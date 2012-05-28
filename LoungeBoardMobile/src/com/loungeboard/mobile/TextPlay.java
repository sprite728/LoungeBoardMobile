package com.loungeboard.mobile;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {
	final Button checkCommand = (Button) findViewById(R.id.bResults);
	final ToggleButton passToggle = (ToggleButton) findViewById(R.id.tbPassword);
		//final => reference won't change
	final EditText input = (EditText) findViewById(R.id.etCommands);
	final TextView display = (TextView) findViewById(R.id.tvResults);
		// Still not sure when should I use final
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		
		
		
		passToggle.setOnClickListener(this);
		checkCommand.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bResults:
			String check = input.getText().toString();
			display.setText(check);
			
			if(check.contentEquals("left")){
				// Set gravity within Java
				display.setGravity(Gravity.LEFT);
				
				
			}else if(check.contentEquals("center")){
				display.setGravity(Gravity.CENTER);
				
			}else if(check.contentEquals("right")){
				display.setGravity(Gravity.RIGHT);
				
			}else if(check.contentEquals("blue")){
				display.setTextColor(Color.BLUE);
			}else if(check.contentEquals("random")){
				Random aNumber = new Random();
				display.setText("AHA!!");
				display.setTextSize(aNumber.nextInt(75));
				display.setTextColor(Color.rgb(aNumber.nextInt(256), aNumber.nextInt(256), aNumber.nextInt(256)));
				
			}else{
				display.setText("invalid");
				display.setGravity(Gravity.CENTER);
			}
			
			
			break;
		case R.id.tbPassword:	
			if(passToggle.isChecked()){
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}else{
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}	
	

}
