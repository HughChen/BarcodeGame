package com.hugh.bcgame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button newGame;
	private MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ourSong = MediaPlayer.create(MainActivity.this, R.raw.chip);
		ourSong.start();
		newGame = (Button)findViewById(R.id.new_game);
	}
	
	public void newGame(View view) {
		ourSong.pause();
		Intent intermediate = new Intent(MainActivity.this, IntermediateActivity.class);
		startActivity(intermediate);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

}
