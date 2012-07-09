package com.loungeboard.mobile;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HomePageActivity extends Activity {
	private final static String DEB_TAG = "HomePageActivity";
	Switch turnOnBtButton;
	TextView title;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		// Hook up all the views
		findViews();
		
		// Get the username send from RegisterActivity
		Intent intent = getIntent();
		String username = intent.getStringExtra(RegisterActivity.USERNAME);
		//title.setText(username);
		turnOnBtButton.setOnClickListener(turnOnBt);
		
		
	}
	
	private View.OnClickListener turnOnBt = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Check if device support bluetooth
			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if( mBluetoothAdapter == null){
				// Device does not support Bluetooth
			}
			
			if(turnOnBtButton.isChecked()){
				// If it is not Checked, turn on the bluetooth
				Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
				startActivity(discoverableIntent);
				
			} else {
				Log.d(DEB_TAG, "Bluetooth off");
				mBluetoothAdapter.cancelDiscovery();
			}
			
		}
	}; 
	
	
	protected void findViews(){
		turnOnBtButton = (Switch) findViewById(R.id.switchButtonHomeBT);
		title = (TextView) findViewById(R.id.textViewHomeTitle);
		
	}
	
	

}
