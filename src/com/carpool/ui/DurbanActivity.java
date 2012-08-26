package com.carpool.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DurbanActivity extends Activity {
	
	 
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       /*Button memberButton = (Button) findViewById(R.id.button_members);
        Button clubButton = (Button) findViewById(R.id.button_clubs);
        memberButton.setOnClickListener(this);
        clubButton.setOnClickListener(this);*/
    }
    
    public void onMemberButtonClicked(View v){
    	startActivity(
                new Intent(this,MemberActivity.class)
        );
    }
    
    public void onClubButtonClicked(View v){
    	startActivity(
                new Intent(this,ClubActivity.class)
        );
    }
    
    
    
   
}