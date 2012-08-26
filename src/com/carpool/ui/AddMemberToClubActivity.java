package com.carpool.ui;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMemberToClubActivity extends Activity {
	 String clubName;
	 String phoneNumber;
	 
	 
     
     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.addmembertoclub);
     }
     
     public void onAddMemberToClubButtonClicked(View v) {
     	
     	 EditText etxt_clubname = (EditText) findViewById(R.id.editText_clubname);
         clubName = etxt_clubname.getText().toString();
         EditText etxt_phonenumber = (EditText) findViewById(R.id.editText_phonenumber);
         phoneNumber = etxt_phonenumber.getText().toString();
         new UIAsyncTask(this).execute();
     	 
     	 
     	 
         
     }
     
     
     private class UIAsyncTask extends AsyncTask<Void,Void,Boolean> {
    		
    		
    		private ProgressDialog dialog;
    		
    		private AddMemberToClubActivity addMemberToClubActivity;
    		private static final String NAMESPACE = "http://action.carpool.com/";
    		private static final String URL = "http://10.0.2.2:8080/durban-taketwo/services/AddMemberToClubAction?wsdl";	
    		private static final String SOAP_ACTION = "http://action.carpool.com";
    		private static final String METHOD_NAME = "add";

    		public UIAsyncTask(Activity activity){
    			
    			dialog = new ProgressDialog(activity);
    			
    			addMemberToClubActivity = (AddMemberToClubActivity) activity;
    		}
    		
    		protected void onPreExecute() {
    	        dialog.setMessage("Adding Member To Club...");
    	        dialog.show();
    	    }


    		@Override
    		protected Boolean doInBackground(Void...voids ) {
    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	    	
    			
    	    	request.addProperty("clubName", addMemberToClubActivity.clubName);
    	    	request.addProperty("phoneNumber", addMemberToClubActivity.phoneNumber);
    			SoapSerializationEnvelope envelope = 
    				new SoapSerializationEnvelope(SoapEnvelope.VER11); 

    			envelope.setOutputSoapObject(request);
    			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    			androidHttpTransport.debug = true;
    			try {
    				
    				androidHttpTransport.call(SOAP_ACTION, envelope);
    				SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
    				
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    				
    				return false;
    			}
    	    return true;
    			
    		}
    		
    		protected void onPostExecute(Boolean v) {
    			if (dialog.isShowing()) {
    	            dialog.dismiss();
    	        }
    			
    		    showAddMemberToClubStatusDialog(v);
    			
    			
    	    }


    	}
     
     
     
     private void showAddMemberToClubStatusDialog(boolean success){
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
 				this);
  
 			// set title
 			alertDialogBuilder.setTitle("Add Member To Club Status");
            if(success==true){
 			// set dialog message
 			alertDialogBuilder
 				.setMessage("Member added to club successfully!")
 				.setCancelable(false)
 				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddMemberToClubActivity.this.finish();
 					}
 				  })
 				;
            }
            else{
            	alertDialogBuilder
 				.setMessage("Adding Member To Club Failed!")
 				.setCancelable(false)
 				.setPositiveButton("Try again?",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddMemberToClubActivity.this.recreate();
 					}
 				  })
 				;
            }
 				// create alert dialog
 				AlertDialog alertDialog = alertDialogBuilder.create();
  
 				// show it
 				alertDialog.show();
 			}
     }
	


