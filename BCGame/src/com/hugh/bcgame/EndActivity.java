package com.hugh.bcgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends Activity {

	private TextView win_lose;
	private Button newGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end);
		win_lose = (TextView)findViewById(R.id.win);
		Intent intent = getIntent();
		String res = intent.getStringExtra("result");
		win_lose.setText(res); //modify this to be win/loss later.
		newGame = (Button)findViewById(R.id.new_game);

	}

	public void newGame(View view) {
		Intent intermediate = new Intent(EndActivity.this, IntermediateActivity.class);
		startActivity(intermediate);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end, menu);
		return true;
	}

}
