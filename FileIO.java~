//Peng Yun Kai
//260864499
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class FileIO {
  public static Character readCharacter(String filename) {
    Character c;
    //initialize string array of size 4 for the 4 characteristics of a character
    String[] traits = new String[4];
    try {
      //reads and assigns buffer
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      //make currLine start at the first line
      String currLine = br.readLine();
      int index =0;
      //while loop stops at the end of the document
      //set a specfic value for each instance of the string array
      while(currLine != null) {
        traits[index] = currLine;
        currLine = br.readLine();
        index++;
      }
      br.close();
      fr.close();
      //catch exceptions
    } catch (FileNotFoundException e) {
      System.out.println("The file isn't there.");
      return null;
    } catch (IOException e) {
      System.out.println("There is an IOException error");
      return null;
    } finally{
      //assign to each trait its assigning type
      String name=traits[0];
      double atckValue=Double.parseDouble(traits[1]);
      double maxHealth=Double.parseDouble(traits[2]);
      int numWins=Integer.parseInt(traits[3]);
      //create the character with its traits of the right type
      c=new Character(name, atckValue, maxHealth, numWins);
    }
    return c;
  }
  
  
  public static ArrayList<Spell> readSpells(String filename){
    //initialize a new arraylist of type spell and a new string array of size 4
    //for the 4 elements of traits of a spell
    ArrayList<Spell> allSpells=new ArrayList<Spell>();
    String[]parts=new String[4];
    try {
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String currLine = br.readLine();
      while(currLine!=null){
        //splits the currentline, the first line, by the tabs,
        //so that each instance of the parts is a section seperated by a tab
        //assign the correct type to each part
        parts=currLine.split("\t");
        String name=parts[0];
        double minDmg=Double.parseDouble(parts[1]);
        double maxDmg=Double.parseDouble(parts[2]);
        double chanceSuccess=Double.parseDouble(parts[3]);
        //add this spell into the array list of all spells
        allSpells.add(new Spell(name,minDmg,maxDmg,chanceSuccess));
        //goes to the next line and continues adding until there is no more next line
        currLine=br.readLine();
      }
      br.close();
      fr.close();
      //catch exceptions
    } catch (FileNotFoundException e) {
      System.out.println("The file isn't there.");
      return null;
    } catch (IOException e) {
      System.out.println("There is an IOException");
      return null;
    } 
    return allSpells;
    
  }
  
  public static void writeCharacter(Character player, String filename){
    try{
      //writes a file of type .txt and gives the name of the string filename
      FileWriter fw=new FileWriter(filename);
      BufferedWriter bw=new BufferedWriter(fw);
      //write the name, the attack value, the max health and the number of wins
      //in new lines each time into the file
      bw.write(player.getName());
      bw.newLine();
      bw.write(""+player.getAttackValue());
      bw.newLine();
      bw.write(""+player.getMaxHealth());
      bw.newLine();
      bw.write(""+player.getNumWins());
      bw.close();
      fw.close();
      System.out.println("Successfully wrote to file: "+filename);
      //catch ioexception
    }catch(IOException e) {
      System.out.println("There is an IOException");
    }
  }
}
