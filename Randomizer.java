import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer{
  public static void main(String args[])
  {
    String[] solutionArray = { "add", "ado", "age", "ago", "aid", "ail", "aim", "air", "and", "any", "ape", "apt", "arc", "are", "ark", "arm", "art", "ash", "ask", "auk", "awe", "awl", "aye", "bad", "bag", "ban", "bat", "bee", "boa", "ear", "eel", "eft", "far", "fat", "fit", "lee", "oaf", "rat", "tar", "tie", };

    shuffleArray(solutionArray);
    for (int i = 0; i < solutionArray.length; i++)
    {
      System.out.print("\""+solutionArray[i]+"\"" + " ");
    }
    System.out.println();
    
  }

  // Implementing Fisher–Yates shuffle
  static void shuffleArray(String[] ar)
  {

    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      String a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
}