package com.carpool.ui;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ViewMemberClubsActivity extends Activity {
	String phoneNumber;
	 
	 
     
     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.viewclubs);
     }
     
     public void onViewMemberClubsButtonClicked(View v) {
      	
     	 EditText etxt_phonenumber = (EditText) findViewById(R.id.editText_phonenumber);
         phoneNumber = etxt_phonenumber.getText().toString();
         
         new UIAsyncTask(this).execute();
     	 
     	 
     	 
         
     }
     
     
     private class UIAsyncTask extends AsyncTask<Void,Void,String> {
    		
    		
    		private ProgressDialog dialog;
    		
    		private ViewMemberClubsActivity viewMemberClubsActivity;
    		private static final String NAMESPACE = "http://action.carpool.com/";
    		private static final String URL = "http://10.0.2.2:8080/durban-taketwo/services/SelectMemberClubAction?wsdl";	
    		private static final String SOAP_ACTION = "http://action.carpool.com";
    		private static final String METHOD_NAME = "getClubName";

    		public UIAsyncTask(Activity activity){
    			
    			dialog = new ProgressDialog(activity);
    			
    			viewMemberClubsActivity = (ViewMemberClubsActivity) activity;
    		}
    		
    		protected void onPreExecute() {
    	        dialog.setMessage("Getting Club Names...");
    	        dialog.show();
    	    }


    		@Override
    		protected String doInBackground(Void...voids ) {
    		String clubName=null;	
    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	    	
    			
    	    	request.addProperty("phoneNumber", viewMemberClubsActivity.phoneNumber);
    	    	
    			SoapSerializationEnvelope envelope = 
    				new SoapSerializationEnvelope(SoapEnvelope.VER11); 

    			envelope.setOutputSoapObject(request);
    			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    			androidHttpTransport.debug = true;
    			try {
    				
    				androidHttpTransport.call(SOAP_ACTION, envelope);
    				SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
    				clubName=resultsRequestSOAP.getPropertyAsString("getClubNameReturn");
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    				
    				return clubName;
    			}
    	    return clubName ;
    			
    		}
    		
    		protected void onPostExecute(String v) {
    			if (dialog.isShowing()) {
    	            dialog.dismiss();
    	        }
    			
    		    //showViewStatusDialog(v);
    			//Show list of clubs
    			System.out.println("Club name: "+v);
    			
    	    }


    	}
     
     
     /*
     private void showAddClubStatusDialog(boolean success){
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
 				this);
  
 			// set title
 			alertDialogBuilder.setTitle("Add Club Status");
            if(success==true){
 			// set dialog message
 			alertDialogBuilder
 				.setMessage("Club added successfully!")
 				.setCancelable(false)
 				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddClubActivity.this.finish();
 					}
 				  })
 				;
            }
            else{
            	alertDialogBuilder
 				.setMessage("Adding Club Failed!")
 				.setCancelable(false)
 				.setPositiveButton("Try again?",new DialogInterface.OnClickListener() {
 					public void onClick(DialogInterface dialog,int id) {
 						// if this button is clicked, close
 						// current activity
 						AddClubActivity.this.recreate();
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
	

     	*/ 
     	 
     	 
            
}
     
     

     
     
  
