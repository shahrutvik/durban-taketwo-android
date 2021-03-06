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

public class AddMemberActivity extends Activity {
	String firstName;
	 String lastName;
	 String phoneNumber;
	 
	 
     
     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.addmember);
     }
     
     public void onAddMemberButtonClicked(View v) {
     	
     	 EditText etxt_firstname = (EditText) findViewById(R.id.editText_firstname);
         firstName = etxt_firstname.getText().toString();
         EditText etxt_lastname = (EditText) findViewById(R.id.editText_lastname);
         lastName = etxt_lastname.getText().toString();
         EditText etxt_phonenumber = (EditText) findViewById(R.id.editText_phonenumber);
         phoneNumber = etxt_phonenumber.getText().toString();
         new UIAsyncTask(this).execute();
     	 
     	 
     	 
         
     }
     
     
     private class UIAsyncTask extends AsyncTask<Void,Void,Boolean> {
    		
    		
    		private ProgressDialog dialog;
    		
    		private AddMemberActivity addMemberActivity;
    		private static final String NAMESPACE = "http://action.carpool.com/";
    		private static final String URL = "http://10.0.2.2:8080/durban-taketwo/services/AddMemberAction?wsdl";	
    		private static final String SOAP_ACTION = "http://action.carpool.com";
    		private static final String METHOD_NAME = "add";

    		public UIAsyncTask(Activity activity){
    			
    			dialog = new ProgressDialog(activity);
    			
    			addMemberActivity = (AddMemberActivity) activity;
    		}
    		
    		protected void onPreExecute() {
    	        dialog.setMessage("Adding Member...");
    	        dialog.show();
    	    }


    		@Override
    		protected Boolean doInBackground(Void...voids ) {
    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	    	
    			
    	    	request.addProperty("firstName", addMemberActivity.firstName);
    	    	request.addProperty("lastName", addMemberActivity.lastName);
    	    	request.addProperty("phoneNumber", addMemberActivity.phoneNumber);
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
    			
    		    showAddMemberStatusDialog(v);
    			
    			
    	    }


    	}
     
     
     
     private void showAddMemberStatusDialog(boolean success){
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
 				this);
  
 			// set title
 			alertDialogBuilder.setTitle("Add Member Status");
            if(success==true){
 			// set dialog message
 			alertDialogBuilder
 				.setMessage("Member added successfully!")
 				.setCancelable(false)
 				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddMemberActivity.this.finish();
 					}
 				  })
 				;
            }
            else{
            	alertDialogBuilder
 				.setMessage("Adding Member Failed!")
 				.setCancelable(false)
 				.setPositiveButton("Try again?",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddMemberActivity.this.recreate();
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
	


