package com.carpool.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MemberActivity extends Activity {
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.members);
    }
    
    public void onButtonClicked(View v) {
    	
    	startActivity(
                new Intent(this,AddMemberActivity.class)
               
        );
    }

}
