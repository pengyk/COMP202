//Peng Yun Kai
//260864499
import java.util.Random;
public class Spell{

  //attributes
  private String name;
  private double minDmg;
  private double maxDmg;
  private double chanceSuccess;
  
  //constructor
  public Spell(String name, double minDmg, double maxDmg, double chanceSuccess){
    //if the chance of success isnt between 0 and 1 or the min damage is smaller 
    //than 0 or the minimum damage is bigger than the max damage, it breaks and throw an Illegalargumentexception
    if(this.chanceSuccess<0||this.chanceSuccess>1
      ||this.minDmg<0||this.minDmg>this.maxDmg){
      throw new IllegalArgumentException();
    }
    this.name=name;
    this.minDmg=minDmg;
    this.maxDmg=maxDmg;
    this.chanceSuccess=chanceSuccess;
  }
  
  public String getName(){
    return this.name;
  }
  
  public double getMagicDamage(int seed){
    Random randomGenerator=new Random(seed);
    //if the randomgenerator is larger than the success chance, the magic damage isn't returned
    //this makes a success chance of 100%(1.0) always run
    if(randomGenerator.nextDouble()>this.chanceSuccess){
      return 0;
    }else{
      return randomGenerator.nextDouble()*(this.maxDmg-this.minDmg)+this.minDmg;
    }
  }
  
  //returns the string with the information
  public String toString(){
    String s="Name: "+this.name+" Damage: "+this.minDmg+"-"+this.maxDmg+" Chance: "+
      this.chanceSuccess*100+"%";
    return s;
  }
}