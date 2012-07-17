package com.loungeboard.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoungeBoardMobileActivity extends Activity {
    
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button signup = (Button) findViewById(R.id.buttonMainRegister);
        Button login = (Button) findViewById(R.id.buttonMainLogin);

//        // Test Toast
//        Toast.makeText(getBaseContext(), "Test Toast", Toast.LENGTH_LONG).show();
        
        signup.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				
				intent.setClass(LoungeBoardMobileActivity.this, RegisterActivity.class);
				startActivity(intent);
				
			}
		} );   
        
        
        login.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				
				intent.setClass(LoungeBoardMobileActivity.this, LoginActivity.class);
				startActivity(intent);
			}
        	
        } );
        
    }
    
    // Check Network Connectivity
    public boolean checkNetworkConnection(){
    	// Use getActiveNetworkInfo & isConnected()
    	ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
    	if(networkInfo != null && networkInfo.isConnected()){
    		return true;
    	}else {
    		return false;
    	}
    }
}