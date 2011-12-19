package com.android.vibratortest;

import android.app.Activity;
import android.os.Vibrator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import java.lang.String;


public class VibratorActivity extends Activity {
    /** Called when the activity is first created. */
	private int i;
	
	private Vibrator vib;
	private Button btnOk;
	private Button btnCancel;
	private PowerManager pm;
	private PowerManager.WakeLock mWakeLock;
	private static final String TAG = "my vibratortest";
	
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
    init();
    btnOk.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			vib.vibrate(new long[]{1000, 2000, 1000, 2000}, 0);
			Log.i(TAG, "vibrator starting now .");
			showToast("ok");
		}
    }
    );
    
    btnCancel.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			vib.cancel();
			
			if (i != 2)
			{
				mWakeLock.release();
				Log.i(TAG, "vibrator ending now .");
				i = 2;
			}
			showToast("cancel");
		}
    }
    );
    
    }
    
    
    private void  init(){
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);  
        pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "VibratorTest");
        mWakeLock.acquire();
        i = 1;
    } 
    
    private void showToast(String msg){  
        Toast.makeText(this, msg, 1).show();  
    }  

}