package com.example.gpstracking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {
	
	Button btnUbicacion;
	
	// GPSTracker class
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
                
        btnUbicacion = (Button) findViewById(R.id.btnUbicacion);
        
        // show location button click event
        btnUbicacion.setOnClickListener(new View.OnClickListener() {    	
        
              
        	
			@Override
			public void onClick(View arg0) {
							 				
				// create class object
		        gps = new GPSTracker(AndroidGPSTrackingActivity.this);
		        
		        gps.stopUsingGPS();
		        
				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Éstas son tus coordenadas. \n Latitud: " + latitude + ". \n Longitud: " + longitude, Toast.LENGTH_LONG).show();	
		      
		        	
			       // TextView txtLatitud = (TextView) findViewById(R.id.txtLatitud);
	               // TextView txtLongitud = (TextView) findViewById(R.id.txtLongitud);
	                
	                String latitud = Double.toString(latitude);
	                String longitud =Double.toString(longitude);
	                
	                //txtLatitud.setText(latitud);
	               // txtLongitud.setText(longitud);
	                
	        		WebView webView = (WebView) findViewById(R.id.webView1);
	        		webView.getSettings().setJavaScriptEnabled(true);
	        		webView.getSettings().setBuiltInZoomControls(true);
	        		webView.getSettings().setSupportZoom(true);
	        		webView.setWebViewClient(new WebViewClient());
	        	//	webView.loadUrl("http://www.openstreetmap.org/?mlat="+latitud+"&mlon="+longitud+"#map=14/"+latitud+"/"+longitud+"&layers=N");
	        		webView.loadUrl("https://www.google.com/maps/@"+latitud+","+longitud+",17z");
	        		//webView.loadUrl("http://www.openstreetmap.org/#map=5/"+latitud+"/"+longitud);
		        
		        
		        
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
		      
	        
			
			}
		});
    }
    
}