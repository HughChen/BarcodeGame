package com.hugh.bcgame;

import gameInterface.Monster;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleActivity extends Activity {

	private TextView monsterTxt, hpTxt, mpTxt, atkTxt, defTxt, attrTxt, monstersTxt, hpsTxt, mpsTxt, atksTxt, defsTxt, attrsTxt;
	private ImageView enemy;
	private Monster missingNo;
	private Monster slime;
	private String missName;
	private Button newGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fight);
		Intent intent = getIntent();
		//enemy monster
		monsterTxt = (TextView)findViewById(R.id.monster_view);
		hpTxt = (TextView)findViewById(R.id.monster_hp);
		mpTxt = (TextView)findViewById(R.id.monster_mp);
		atkTxt = (TextView)findViewById(R.id.monster_atk);
		defTxt = (TextView)findViewById(R.id.monster_def);
		attrTxt = (TextView)findViewById(R.id.monster_attr);
		String monster = intent.getStringExtra("name");
		missName = monster;
		int hp = intent.getIntExtra("hp", 0);
		int mp = intent.getIntExtra("mp", 0);
		int atk = intent.getIntExtra("atk", 0);
		int def = intent.getIntExtra("def", 0);
		int attr = intent.getIntExtra("attr", 0);
		missingNo = new Monster(hp, mp, atk, def, attr);
		monsterTxt.setText("MONSTER: " + monster);
		hpTxt.setText("HP: " + missingNo.stat("hp"));
		mpTxt.setText("MP: " + missingNo.stat("mp"));
		atkTxt.setText("ATK: " + missingNo.stat("atk"));
		defTxt.setText("DEF: " + missingNo.stat("def"));
		attrTxt.setText("ATTR: " + missingNo.stat("attr"));
		//CONSTRUCT SLIME
		monstersTxt = (TextView)findViewById(R.id.monsters_view);
		hpsTxt = (TextView)findViewById(R.id.monsters_hp);
		mpsTxt = (TextView)findViewById(R.id.monsters_mp);
		atksTxt = (TextView)findViewById(R.id.monsters_atk);
		defsTxt = (TextView)findViewById(R.id.monsters_def);
		attrsTxt = (TextView)findViewById(R.id.monsters_attr);
		int slimehp = intent.getIntExtra("hps", 0);
		int slimemp = intent.getIntExtra("mps", 0);
		int slimeatk = intent.getIntExtra("atks", 0);
		int slimedef = intent.getIntExtra("defs", 0);
		int slimeattr = intent.getIntExtra("attrs", 0);

		slime = new Monster(slimehp, slimemp, slimeatk, slimedef, slimeattr);
		hpsTxt.setText("HP: " + slime.stat("hp"));
		mpsTxt.setText("MP: " + slime.stat("mp"));
		atksTxt.setText("ATK: " + slime.stat("atk"));
		defsTxt.setText("DEF: " + slime.stat("def"));
		attrsTxt.setText("ATTR: " + slime.stat("attr"));
		newGame = (Button)findViewById(R.id.new_game);
	}
	
	public void newGame(View view) {
		Intent intermediate = new Intent(BattleActivity.this, IntermediateActivity.class);
		startActivity(intermediate);
	}
	
	public static int random(int max, int min, int offset) {
	    return min + (int)(Math.random() * (((max - offset - min) + 1)));
	}
	
	public static int damage(int myhp, int youratk, int mydef) {
		int youratknew = random(youratk + youratk/2, youratk/2, 0);
		if (youratknew > mydef) {
			return myhp - youratknew - mydef;
		} else {
			return myhp - 1;
		}
	}

	public void attack(View view) {
		int missingNohp = damage(missingNo.stat("hp"), slime.stat("atk"), missingNo.stat("def"));
		int slimehp = damage(slime.stat("hp"), missingNo.stat("atk"), slime.stat("def"));
		if (missingNohp > 0 && slimehp > 0 ){ //need to make sure that this is reimplemented.  Doesn't cover the dying slime case.
			Intent fight = new Intent(BattleActivity.this, BattleActivity.class);
			fight.putExtra("name", missName);
			fight.putExtra("hp", missingNohp);
			fight.putExtra("mp", missingNo.stat("mp"));
			fight.putExtra("atk", missingNo.stat("atk"));
			fight.putExtra("def", missingNo.stat("def"));
			fight.putExtra("attr", missingNo.stat("attr"));
			fight.putExtra("hps", slimehp);
			fight.putExtra("mps", slime.stat("mp"));
			fight.putExtra("atks", slime.stat("atk"));
			fight.putExtra("defs", slime.stat("def"));
			fight.putExtra("attrs", slime.stat("attr"));
			startActivity(fight);
		} else if (missingNohp < 0) {
			Intent done = new Intent(BattleActivity.this, EndActivity.class);
			done.putExtra("result", "YOU WON!");
			startActivity(done);
		} else if (slimehp < 0) {
			Intent done = new Intent(BattleActivity.this, EndActivity.class);
			done.putExtra("result", "YOU LOST!");
			startActivity(done);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}

}
