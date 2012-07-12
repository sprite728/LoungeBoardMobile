package com.loungeboard.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private EditText usernameField;
	private EditText passwordField;
	private Button loginButton;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// First, set up the background
		setContentView(R.layout.login);
		
	}
	

}
