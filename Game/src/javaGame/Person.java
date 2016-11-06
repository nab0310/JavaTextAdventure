package javaGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
	public double health, amour, pattack, mattack, mdefense, crit;
	public String weapon;
	public ArrayList<Double> weaponStats;
	public Person(double h, double a, double pa,double ma,double md, double c){
		health=h;
		amour = a;
		pattack = pa;
		mattack = ma;
		mdefense = md;
		crit = c;
	}
	public double getHealth(){
		return health; 
	}
	public double getAmour(){
		return amour; 
	}
	public double getPattack(){
		return pattack; 
	}
	public double getMattack(){
		return mattack; 
	}
	public double getMdefense(){
		return mdefense; 
	}
	public double getCrit(){
		return crit; 
	}
	public void setWeapon(String givenWeapon) throws FileNotFoundException{
		// TODO Auto-generated method stub
		weaponStats = new ArrayList<Double>();
		this.weapon = givenWeapon;
		double weaponPattack = 0;
		double weaponMattack=0;
		double attackSpeed=0;
		File fr = new File("C:\\Users\\nab0310\\Documents\\Java\\Relearning_Java\\src\\javaGame\\WeaponData.txt");
		Scanner scan = new Scanner(fr);
		while(scan.hasNextLine()){
			String weaponOnFile =scan.next();
			if(givenWeapon.equals(weaponOnFile)){
				weaponPattack = scan.nextDouble();
				weaponMattack = scan.nextDouble();
				attackSpeed = scan.nextDouble();
				break;
			}else{
				scan.nextLine();
			}
		}
		scan.close();
		weaponStats.add(weaponPattack);
		weaponStats.add(weaponMattack);
		weaponStats.add(attackSpeed);
	}
}
