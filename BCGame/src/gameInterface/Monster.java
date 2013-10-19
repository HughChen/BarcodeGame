package gameInterface;

public class Monster { 
	String name;
	int[] stats = {10, 10, 10, 10, 10};
	int damage_counter = this.stats[0];
	boolean dead;

	public Monster(String name, int rarity){
		this.name = name;
		this.dead = false;
	}

	public Monster(int hp, int mp, int atk, int def, int attr) {
		this.stats[0] = hp;
		this.stats[1] = mp;
		this.stats[2] = atk;
		this.stats[3] = def;
		this.stats[4] = attr;
	}

	public void stat_change(int health, int mana, int attack, int defense, int attribute) {
		this.stats[0] = health;
		this.stats[1] = mana;
		this.stats[2] = attack;
		this.stats[3] = defense;
		this.stats[4] = attribute;
	}
	
	public void set_health(int enemy_atk) {
		this.stats[0] = this.stats[0] - (enemy_atk - this.stats[3]);
	}

	public int stat(String type) {
		if (type == "hp") {
			return this.stats[0]; 
		} else if (type == "mp") { 
			return this.stats[1]; 
		} else if (type == "atk") { 
			return this.stats[2]; 
		} else if (type == "def") { 
			return this.stats[3]; 
		} else if (type == "attr") {
			return this.stats[4];
		}
		return 0;            
	}

}
