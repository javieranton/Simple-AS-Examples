package joandev.sensorexample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class GPSActivity extends Activity {
	/** Called when the activity is first created. */
	List<Address> l;
	LocationManager lm;
	LocationListener lis;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		l = null;
		setContentView(R.layout.main);
		lm = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		lis = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
										Bundle extras) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				Geocoder gc = new Geocoder(getApplicationContext());
				try {
					l = gc.getFromLocation(location.getLatitude(),
							location.getLongitude(), 5);
				} catch (IOException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < l.size(); ++i) {
					Log.v("LOG", l.get(i).getAddressLine(0).toString());
					TextView t = (TextView) findViewById(R.id.textView);
					if (i == 0) t.setText("");
					t.setText(t.getText() + "\n" + l.get(i).getAddressLine(0).toString());
				}
				Log.v("LOG", ((Double) location.getLatitude()).toString());
			}
		};

		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, lis);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lis);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		lm.removeGpsStatusListener((Listener) lis);
		
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}