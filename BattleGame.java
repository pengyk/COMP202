//Peng Yun Kai
//260864499
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class BattleGame {
  private static Random randomGenerator=new Random();
  
  //playgame takes a file for the player, the monster and the spell
  public static void playGame(String filePlayer, String fileMonster, String fileSpell){
    //reads the files for the player and the spell
    Character player=FileIO.readCharacter(filePlayer);
    Character monster=FileIO.readCharacter(fileMonster);
    ArrayList<Spell> spell= FileIO.readSpells(fileSpell);
    if(spell==null){
      System.out.println("The game will be played without spells.\n");
    }
    //if either the player or the monster file doesn't read out a correct character, the game cannot be played
    if(player==null||monster==null){
      System.out.println("The game cannot be played.\n");
      return;
    }else{
      //print out the characteristics of the player and the monster
      printInfo(player, monster);
      player.setSpells(spell);
      System.out.println("\nHere are the available spells:");
      player.displaySpells();
      //scanner for the input of the command
      Scanner read=new Scanner(System.in);
      while(player.getCurrHealth()>0&&monster.getCurrHealth()>0){
        System.out.println("\nEnter a command: ");
        String command;
        //takes the next line of input in command
        command=read.nextLine();
        
        //for all the commands, checks after each one attacks if the health of the other is 0
        //if it is, it prints out a message and takes you out of the while loop.
        
        if(command.equals("attack")){
          oneAttackOther(player, monster);
          if(monster.getCurrHealth()<=0){
            System.out.println("\nFantastic! You killed the monster");
            player.increaseWins();
            break;
          }
          System.out.println();
          oneAttackOther(monster, player);
          if(player.getCurrHealth()<=0){
            System.out.println("\nOh no! You lost!");
            monster.increaseWins();
            break;
          }
        }
        else if(command.equals("quit")){
          System.out.println("Goodbye!");
          return;
        }
        else{
          playerSpellMonster(player, monster, spell, command);
          if(monster.getCurrHealth()<=0){
            System.out.println("\nFantastic! You killed the monster!");
            player.increaseWins();
            break;
          }
          System.out.println();
          oneAttackOther(monster, player);
          if(player.getCurrHealth()<=0){
            System.out.println("\nOh no! You lost!");
            monster.increaseWins();
            break;
          }
        }
      }
      //Writes out the win into the winners file
      if(monster.getCurrHealth()<=0){
        FileIO.writeCharacter(player,"player.txt");
        System.out.println(player.getName()+" has won: "+player.getNumWins()+" times.");
      }else{
        FileIO.writeCharacter(monster,"monster.txt");
        System.out.println(monster.getName()+" has won: "+monster.getNumWins()+" times.");
      }
    }
  }
  //own method to print out the information for the player and the monster
  private static void printInfo(Character player, Character monster){
    System.out.println("Name: "+player.getName());
    System.out.println("Health: "+player.getMaxHealth());
    System.out.println("Attack: "+player.getAttackValue());
    System.out.println("Number of wins: "+player.getNumWins());
    System.out.println("\nName: "+monster.getName());
    System.out.println("Health: "+monster.getMaxHealth());
    System.out.println("Attack: "+monster.getAttackValue());
    System.out.println("Number of wins: "+monster.getNumWins());
  }
  
  //method used for when the command attack is used(works for the player and the monster)
  private static void oneAttackOther(Character one, Character other){
    double damage=one.getAttackDamage(randomGenerator.nextInt());
    String damageStr=String.format("%1$.2f", damage);
    System.out.println("\n"+one.getName()+" attacks for "+damageStr+" damage!");
    double damagedHealth=other.takeDamage(damage);
    //performs a check to see if the other character is dead or not
    if(damagedHealth>0){
      System.out.println(other.toString());
    }else{
      System.out.println(other.getName()+" was knocked out!");
      return;
    }
  }
  
  //method for when a player attacks a monster with a spell
  private static void playerSpellMonster(Character player, Character monster, ArrayList<Spell> spell, String command){
    double damage=player.castSpell(command,(randomGenerator.nextInt()));
    //if it returns less than 0, it will return -1, then the castSpell method didn't work and the spell doesnt exist
    if(damage<0){
      System.out.println("\n"+player.getName()+" tried to cast "+command+" but they don't know that spell.");
      return;
      //if the damage is 0, it means that the spell is known, but it didn't have the chance to cast
    }else if(damage==0){
      System.out.println("\n"+player.getName()+" tried to cast "+command+", but they failed.");
    }else{
      //if the damage isn't smaller or equal than 0, then it worked
      String dmgCast=String.format("%1$.2f", damage);
      double damagedHealth=monster.takeDamage(damage);
      System.out.println("\n"+player.getName()+" casts "+command+" dealing "+dmgCast+" damage!");
      if(damagedHealth>0){
        //if the monster is still alive, it prints out his health
        //if it is lower, it will print the message saying the monster was kicked out
        System.out.println(monster.toString());
      }else{
        System.out.println(monster.getName()+" was knocked out!");
        return;
      }
    }
  }
  
 /* public static void main(String args[]){
    playGame("player.txt", "monster.txt", "spells.txt");
  }
  */
}

