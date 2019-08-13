//Peng Yun Kai
//260864499
import java.util.Random;
import java.util.ArrayList;

public class Character {
  //attributes
  private String name;
  private double atckValue;
  private double maxHealth;
  private double currHealth;
  private int numWins;
  private static ArrayList <Spell> spells=new ArrayList <Spell> ();
  
  //constructor
  public Character(String name, double atckValue, double maxHealth,
                   int numWins) {
    this.name=name;
    this.atckValue=atckValue;
    this.maxHealth=maxHealth;
    this.numWins=numWins;
    this.currHealth=maxHealth;
  }
  
  public String getName(){
    return this.name;
  }
  
  public double getAttackValue(){
    return this.atckValue;
  }
  
  public double getMaxHealth(){
    return this.maxHealth;
  }
  
  public double getCurrHealth(){
    return this.currHealth;
  }
  
  public int getNumWins(){
    return this.numWins;
  }
  
  public String toString(){
    String dmgHealth=String.format("%1$.2f", this.currHealth);
    String output=this.name+" current health is "+dmgHealth+".";
    return output;
  }
  
  //the random value (between 0 and 1 excluded) is multiplied by the difference of 1 and 0.7 which is 0.3
  //it then has 0.7 added to it so that it gives a random from 0.7 to 1.0
  //the attack damage is then multiplied by it
  public double getAttackDamage(int dmg){
    Random randomGenerator=new Random(dmg);
    double attDmg=atckValue*(randomGenerator.nextDouble()*0.3+0.7);
    return attDmg;
  }
  
  public double takeDamage(double dmgTaken){
    double damagedHealth=currHealth-dmgTaken;
    currHealth=damagedHealth;
    return currHealth;
  }
  
  public void increaseWins(){
    numWins=numWins+1;
  }
  
  //foreach loop to set all the spells in the arraylist
  public static void setSpells(ArrayList<Spell> allSpells){
    for(Spell spell:allSpells){
      spells.add(spell);
    }
  }
  
  //foreach loop to display all the spells
  public static void displaySpells(){
    for(Spell spell: spells){
      System.out.println(spell.toString());
    }
  }
  
  //cast a spell if the input matches the name of the actual spell
  public double castSpell(String name, int i){
    for(Spell spell:this.spells){
      if(name.equalsIgnoreCase(spell.getName())){
        return spell.getMagicDamage(i);
      }
    }
    //this -1 will be used to check if the spell is known or not
    return -1;
  }
}
