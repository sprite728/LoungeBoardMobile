package com.loungeboard.mobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = {"LoungeBoardMobileActivity", "TextPlay", "example2", "example3", "example4"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes) );
			// 
		
	}	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub	
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		
		try {
			
			Class myClass = Class.forName("com.loungeboard.mobile." + cheese);
				// Create a class variable for the class name
			Intent myIntent = new Intent(Menu.this, myClass); //set intent based on class variable
				// Menu.this = current context
		
			startActivity(myIntent);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}


	
	
}
