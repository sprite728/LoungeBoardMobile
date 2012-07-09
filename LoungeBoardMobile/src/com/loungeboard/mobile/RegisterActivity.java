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
import android.app.Dialog;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity{
	private final static String DEB_TAG = "Register";
	public final static String USERNAME = "com.loungeboard.mobile.USERNAME";
	
	private EditText usernameField;
	private EditText firstnameField;
	private EditText lastnameField;
	private EditText emailField;
	private EditText passwordField;
	private EditText password2Field;
	private Button signupButton;
	private TextView textViewPolicy;
	private CheckBox shareBtIdCheckBox;
	
	private ProgressDialog dialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		findViews();
		
//		Log.i(DEB_TAG, "onCreate");
//		
		signupButton.setOnClickListener(signup);
	}

	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		return new AlertDialog.Builder(RegisterActivity.this)
			.setTitle(R.string.alert_dialog_share_bluetooth_not_selected)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			})
			.create();
	}



	private Button.OnClickListener signup = new Button.OnClickListener(){
		public void onClick(View v) {
			// Check if user has selected to share their Bluetooth Address
			if(!shareBtIdCheckBox.isChecked()){
				// if not checked, show a pop up window or add a text in red
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
				alertDialogBuilder.setTitle("Hey!");
				alertDialogBuilder
					.setMessage(R.string.alert_dialog_share_bluetooth_not_selected)
					.setCancelable(false)
					.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							return;
						}
					});
				
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
				
				// show it
				alertDialog.show();
				
			}
			
			
			// When user click button, calls AsyncTask
			// Before attempting to fetch the URL, makes sure that there is a network connection
			
			
			Log.d(DEB_TAG+"onClick", "onClick");
//			NetworkHelper nwHelper = new NetworkHelper();
		
			String username = usernameField.getText().toString();
			String lastname = lastnameField.getText().toString();
			String firstname = firstnameField.getText().toString();
			String email = emailField.getText().toString();
			String password = passwordField.getText().toString();
			String password2 = password2Field.getText().toString();
		
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if( networkInfo != null && networkInfo.isConnected() ){ // Network Is Connected
				
				
				// Get the bluetooth Machine Address here
				BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
				String BTID = mBluetoothAdapter.getAddress();
				Log.d(DEB_TAG, "Bluetooth Address = " + BTID);
				
				
				
				new RegisterAccount().execute(username, lastname, firstname, email, password, password2, BTID);
				
				
			} else {
				// Network Is not Connected
				
			}
	
		}
	};
	
	private class RegisterAccount extends AsyncTask {
	    @Override
	    protected void onPreExecute() {
	    	
	    	// While sign up, the screen should show up a progress bar
	        dialog = new ProgressDialog(RegisterActivity.this);
	        dialog.setMessage("Waiting...");
	        dialog.setTitle("Connect to LoungeBoard...");
	        dialog.setIndeterminate(true);
	        dialog.setCancelable(false);
	        dialog.show();
	   }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			// TODO Auto-generated method stub
			String username = (String) params[0];
			String lastname = (String) params[1];
			String firstname = (String) params[2];
			String email = (String) params[3];
			String password = (String) params[4];
			String password2 = (String) params[5];
			String BTID = (String) params[6];
			
			try {
				HttpClient httpclient = new DefaultHttpClient();
//				URI host = new URI("http://inteco.groups.si.umich.edu/LoungeBoardMobile/");
				HttpPost httppost = new HttpPost("http://inteco.groups.si.umich.edu/LoungeBoardMobile/");
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username", username));
				nameValuePairs.add(new BasicNameValuePair("lastname", lastname));
				nameValuePairs.add(new BasicNameValuePair("firstname", firstname));
				nameValuePairs.add(new BasicNameValuePair("email", email));
				nameValuePairs.add(new BasicNameValuePair("password", password));
				nameValuePairs.add(new BasicNameValuePair("password2", password2));
				nameValuePairs.add(new BasicNameValuePair("BTID", BTID));
				
				// Execute HTTP Post Request
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
				HttpResponse response = httpclient.execute(httppost); 
				
				Log.d(DEB_TAG, "HTTP POST getStatusLine" + response.getStatusLine());
				
				int status = response.getStatusLine().getStatusCode();
				Log.i(DEB_TAG, "status = " + status);
				
				if(status == 200){
					Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
					intent.putExtra(USERNAME, username);
					startActivity(intent);
				} else {
					// if not success
					
				}
				
				
				return "OK";
				
			} catch (IOException e){
				return "Unable to retrieve web page.";
			}
			
		}

		// onPostExecute displays the results of the AsyncTask
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			dialog.dismiss();
		}
		
		
	}
	
	private void findViews()
	{
		usernameField = (EditText) findViewById(R.id.editTextUsername);
		firstnameField = (EditText) findViewById(R.id.editTextFirstName);
		lastnameField = (EditText) findViewById(R.id.editTextLastName);
		emailField = (EditText) findViewById(R.id.editTextEmail);
		passwordField = (EditText) findViewById(R.id.editTextPassword);
		password2Field = (EditText) findViewById(R.id.editTextConfirmPassword);	
		signupButton = (Button) findViewById(R.id.buttonSignUp);
		textViewPolicy = (TextView) findViewById(R.id.textViewPolicy);
		shareBtIdCheckBox = (CheckBox) findViewById(R.id.checkBoxBTPermission);
	}
}
