package com.hugh.bcgame;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class IntermediateActivity extends Activity implements OnClickListener{

	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	public final static String EXTRA_MESSAGE = "com.hugh.bcgame.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intermediate);
		scanBtn = (Button)findViewById(R.id.scan_button);
		formatTxt = (TextView)findViewById(R.id.scan_format);
		contentTxt = (TextView)findViewById(R.id.scan_content);
		scanBtn.setOnClickListener(this);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	public void onClick(View v){
		if(v.getId()==R.id.scan_button){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intermediate, menu);
		return true;
	}

	public static int random(int max, int min, int offset) {
	    return min + (int)(Math.random() * (((max - offset - min) + 1)));
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
			Intent fight = new Intent(IntermediateActivity.this, FightActivity.class);
			//scanContent = "" + scanContent.charAt(scanContent.length() - 1);
		    //String mess = Integer.toString(Integer.parseInt(scanContent) % 2);
		    fight.putExtra("scanContent", scanContent);
		    int hprandom = random(scanContent.length(), 1, 3);
		    fight.putExtra("hp", scanContent.substring(hprandom, hprandom + 3));
		    int mprandom = random(scanContent.length(), 1, 3);
		    fight.putExtra("mp", scanContent.substring(mprandom, mprandom+3));
		    int atkrandom = random(scanContent.length(), 1, 2);
		    fight.putExtra("atk", scanContent.substring(atkrandom, atkrandom + 2));
		    int defrandom = random(scanContent.length(), 1, 2);
		    fight.putExtra("def", scanContent.substring(defrandom, defrandom+2));
		    fight.putExtra("attr", Integer.toString((int)scanContent.charAt(scanContent.length() - 1) % 5));


		    startActivity(fight);
		}else{
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
