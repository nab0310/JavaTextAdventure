package javaGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to my game!");
		System.out.println("Please Select a class(enter the number of the class you would like): ");
		System.out.println("Your choices are: (1)Mage, (2)Fighter, (3)Tank, and (4)Thief.");
		int playerClass = scan.nextInt();
		if(playerClass>4 || playerClass<0)
		{
			System.out.println("This is not a valid number. The game will restart.");
			main(null);
		}
		Person mainCharacter = selectClass(playerClass);
		System.out.println("Your First Fight: (1)Troll, (2)Dragon, (3)Squid");
		int monster = scan.nextInt();
		if(monster>3 || monster<0)
		{
			System.out.println("This is not a valid number. The game will restart.");
			main(null);
		}
		Person Monster = monsterStats(monster);
		System.out.println("Let's Fight!");
		FightMonster(mainCharacter,Monster);
		if(mainCharacter.getHealth()>=0){
			System.out.println("Thanks For Playing");
		}
		scan.close();
	}
	/**
	 * Returns the base stats of the player in array list form. 
	 * The array list is organized by health, amour, physical attack, magical attack, magical defense, and critical chance.
	 * @param playerClass
	 * @return
	 * @throws FileNotFoundException 
	 */
	private static Person selectClass(int classChoice){
		try{
		switch (classChoice){
		case 1: MageClass MageClass = new MageClass();
			MageClass.setWeapon("Staff");
			System.out.println("To start out the game you have "+MageClass.getHealth()+" health, "+ MageClass.getAmour()+" amour, "+ MageClass.getPattack()+" physical attack, "+ MageClass.getMattack()+ " magical attack, "+MageClass.getMdefense()+" magical defense, and "+MageClass.getCrit()+" critical chance.");
			System.out.println("You have a weapon of a "+ MageClass.weapon+". It has a Physical Attack of "+MageClass.weaponStats.get(0)+", a Magic Attack of "+MageClass.weaponStats.get(1)+", and an Attack Speed of "+MageClass.weaponStats.get(2)+".");
			return MageClass;
		case 2:FighterClass FighterClass = new FighterClass();
			FighterClass.setWeapon("Sword");
			System.out.println("To start out the game you have "+FighterClass.getHealth()+" health, "+ FighterClass.getAmour()+" amour, "+ FighterClass.getPattack()+" physical attack, "+ FighterClass.getMattack()+ " magical attack, "+FighterClass.getMdefense()+" magical defense, and "+FighterClass.getCrit()+" critical chance.");
			System.out.println("You have a weapon of a "+ FighterClass.weapon+". It has a Physical Attack of "+FighterClass.weaponStats.get(0)+", a Magic Attack of "+FighterClass.weaponStats.get(1)+", and an Attack Speed of "+FighterClass.weaponStats.get(2)+".");
			return FighterClass;
		case 3:TankClass TankClass = new TankClass();
			TankClass.setWeapon("Axe");
			System.out.println("To start out the game you have "+TankClass.getHealth()+" health, "+ TankClass.getAmour()+" amour, "+ TankClass.getPattack()+" physical attack, "+ TankClass.getMattack()+ " magical attack, "+TankClass.getMdefense()+" magical defense, and "+TankClass.getCrit()+" critical chance.");
			System.out.println("You have a weapon of a "+ TankClass.weapon+". It has a Physical Attack of "+TankClass.weaponStats.get(0)+", a Magic Attack of "+TankClass.weaponStats.get(1)+", and an Attack Speed of "+TankClass.weaponStats.get(2)+".");
			return TankClass;
		case 4:ThiefClass ThiefClass = new ThiefClass();
			ThiefClass.setWeapon("Dagger");
			System.out.println("To start out the game you have "+ThiefClass.getHealth()+" health, "+ ThiefClass.getAmour()+" amour, "+ ThiefClass.getPattack()+" physical attack, "+ ThiefClass.getMattack()+ " magical attack, "+ThiefClass.getMdefense()+" magical defense, and "+ThiefClass.getCrit()+" critical chance.");
			System.out.println("You have a weapon of a "+ ThiefClass.weapon+". It has a Physical Attack of "+ThiefClass.weaponStats.get(0)+", a Magic Attack of "+ThiefClass.weaponStats.get(1)+", and an Attack Speed of "+ThiefClass.weaponStats.get(2)+".");
			return ThiefClass;
		}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
		
	}
	private static Person monsterStats(int monsterClass) throws FileNotFoundException{
		switch (monsterClass){
			case 1: Troll troll = new Troll();
				troll.setWeapon("Club");
				System.out.println("Troll, has weapon of a "+troll.weapon);
				System.out.println("To start out the game a Troll has "+troll.getHealth()+" health, "+ troll.getAmour()+" amour, "+ troll.getPattack()+" physical attack, "+ troll.getMattack()+ " magical attack, "+troll.getMdefense()+" magical defense, and "+troll.getCrit()+" critical chance.");
				return troll;
			case 2: Dragon drag = new Dragon();
				drag.setWeapon("FireBreath");
				System.out.println("Dragon, has weapon of a "+drag.weapon);
				System.out.println("To start out the game a drag has "+drag.getHealth()+" health, "+ drag.getAmour()+" amour, "+ drag.getPattack()+" physical attack, "+ drag.getMattack()+ " magical attack, "+drag.getMdefense()+" magical defense, and "+drag.getCrit()+" critical chance.");
				return drag;
			case 3: Squid squid = new Squid();
				squid.setWeapon("ink");
				System.out.println("Squid, has weapon of a "+squid.weapon);
				System.out.println("To start out the game a squid has "+squid.getHealth()+" health, "+ squid.getAmour()+" amour, "+ squid.getPattack()+" physical attack, "+ squid.getMattack()+ " magical attack, "+squid.getMdefense()+" magical defense, and "+squid.getCrit()+" critical chance.");
				return squid;
		}
		return null;
		
	}
	private static void FightMonster(Person player,Person monster){
		while(player.getHealth()>0){
			int playerBuffs;
			System.out.println("Attack(1) or Defend(2)?");
			Scanner scan = new Scanner(System.in);
			int playerAction = scan.nextInt();
			double playerAttack;
			String typeOfPlayerAttack = null;
			if(playerAction ==1){
				System.out.println("You chose to attack!");
				System.out.println("You get a small increase in Magic and Physical Attack.");
				player.mattack = player.getMattack()+15;
				player.pattack = player.getPattack()+15;
				playerAttack = Math.max(player.pattack+player.weaponStats.get(0), player.mattack+player.weaponStats.get(1));
				if(player.pattack+player.weaponStats.get(0)>player.mattack+player.weaponStats.get(1)){
					typeOfPlayerAttack = "Physical";
				}else{
					typeOfPlayerAttack = "Magical";
				}
				double critChance = Math.random();
				if(critChance>.75){
					playerAttack = playerAttack + playerAttack*player.getCrit();
				}
			}else if(playerAction ==2){
				System.out.println("You chose to defend!");
				System.out.println("You get a small increase in Armour and Magic Defense.");
				playerAttack = 0;
				player.amour = player.getAmour()+15;
				player.mdefense = player.getMdefense()+15;
			}else{
				System.out.println("This is not a valid number, please enter 1 or 2");
				break;
			}
			double monsterAction = Math.random();
			double monsterAttack;
			String typeOfMonsterAttack = null;
			if(monsterAction>=.5){
				monsterAttack = Math.max(monster.pattack+monster.weaponStats.get(0), monster.mattack + monster.weaponStats.get(1));
				if(monster.pattack+monster.weaponStats.get(0)>monster.mattack+monster.weaponStats.get(1)){
					typeOfMonsterAttack = "Physical";
				}else{
					typeOfMonsterAttack = "Magical";
				}
				double critChance = Math.random();
				if(critChance>.75){
					monsterAttack = monsterAttack + monsterAttack*monster.getCrit();
				}
			}else{
				monsterAttack =0;
				monster.amour = monster.getAmour()+15;
				monster.mdefense = monster.getMdefense()+15;
			}
			//Combat
			if(player.weaponStats.get(2)>monster.weaponStats.get(2)){
				if(playerAction==1&&player.getHealth()>0){
					System.out.println("You attack First!");
					if(typeOfPlayerAttack.equals("Physical")){
						monster.health = monster.health - (playerAttack-(monster.getAmour()));
					}else{
						monster.health = monster.health -(playerAttack-(monster.getMdefense()));
					}
				}
				if(monsterAction>=.5&&monster.getHealth()>0){
					System.out.println("The Monster attacked!");
					if(typeOfMonsterAttack.equals("Physical")){
						player.health = player.health- (monsterAttack -(player.getAmour()));
					}else{
						player.health = player.health -(monsterAttack -(player.getMdefense()));
					}
				}
			}else{
				if(monsterAction>=.5&&monster.getHealth()>0){
					System.out.println("The Monster Attacks First!");
					if(typeOfMonsterAttack.equals("Physical")){
						player.health = player.health - (monsterAttack -(player.getAmour()));
					}else{
						player.health = player.health- (monsterAttack -(player.getMdefense()));
					}
				}else{
					System.out.println("The monster isnt attacking!");
				}
				if(playerAction==1&&player.getHealth()>0){
					System.out.println("You chose to Attack!");
					if(typeOfPlayerAttack.equals("Physical")){
						monster.health =- playerAttack-(monster.getAmour());
					}else{
						monster.health =- playerAttack-(monster.getMdefense());
					}
				}
			}
			System.out.println("The monster has "+monster.getHealth()+" health left. You have "+player.getHealth()+" health left.");
			if(monster.getHealth()<=0){
				System.out.println("You won!!");
				break;
			}
		}
		if(player.getHealth()<=0){
			System.out.println("You have died....");
		}
	}
}
