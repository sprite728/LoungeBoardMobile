package com.loungeboard.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private final static String DEB_TAG = "Login";
	private EditText etUsername;
	private EditText etPassword;
	private Button btLogin;
	
	private ProgressDialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// First, set up the background
		setContentView(R.layout.login);
		
		// Hook Up all the Views
		// 	Two ways to do that
		// 	1. directly involve in onCreate
		//  2. put it separately in a different methods
		// Here, let's try 2
		etUsername = (EditText) findViewById(R.id.editTextUsername); //et stands for Edit Text
		etPassword = (EditText) findViewById(R.id.editTextPassword);
		btLogin = (Button) findViewById(R.id.buttonLogin);
		
		btLogin.setOnClickListener(login);
		
	}
	
	private Button.OnClickListener login = new Button.OnClickListener(){
		public void onClick(View v) {
			// Check if user has selected to share their Bluetooth Address
			
			
		
			Log.d(DEB_TAG+"onClick", "onClick");
		
			// Translate all the input to string format
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			
		
			// Check Network Connection
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if( networkInfo != null && networkInfo.isConnected() ){ // If Network Is Connected

				new LoginAccount().execute(username, password);
				
			} else {
				// Network Is not Connected
				
			}
	
		}
		
	};

	
	private class LoginAccount extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String username = (String) params[0];
			String password = (String) params[1];
			
			Log.d(DEB_TAG, "doInBackground Begin");
			
			try {
				HttpClient httpclient = new DefaultHttpClient();
//				URI host = new URI("http://inteco.groups.si.umich.edu/LoungeBoardMobile/");
				HttpPost httppost = new HttpPost("http://67.194.36.171:3000/login");
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("name", username));
				nameValuePairs.add(new BasicNameValuePair("password", password));
				
				
				// Execute HTTP Post Request
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
				HttpResponse response = httpclient.execute(httppost); 
				
				Log.d(DEB_TAG, "HTTP POST getStatusLine" + response.getStatusLine());
				
				int status = response.getStatusLine().getStatusCode();
				Log.i(DEB_TAG, "status = " + status);
				
				if(status == 201 || status == 200){
					Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
					//intent.putExtra(USERNAME, username);
					startActivity(intent);
				} else {
					// if not success
					
					
				}
				
			} catch (IOException e){
				Log.d(DEB_TAG, "Unable to retrieve web page.");
				return "Unable to retrieve web page.";
			} 
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(LoginActivity.this);
	        dialog.setMessage("Waiting...");
	        dialog.setTitle("Connect to LoungeBoard...");
	        dialog.setIndeterminate(true);
	        dialog.setCancelable(false);
	        dialog.show();
		}
		
		
	}
	
}
