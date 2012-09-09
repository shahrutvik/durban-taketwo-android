package com.carpool.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClubActivity extends Activity {
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs);
    }
    
    public void onAddClubButtonClicked(View v) {
    	
    	startActivity(
                new Intent(this,AddClubActivity.class)
               
        );
    }
    
    public void onViewClubButtonClicked(View v) {
     	
   	 startActivity(
                new Intent(this,ViewMemberClubsActivity.class)
               
        );
    }

}
