// Name: Maitreyee Das Urmi
// Student ID: 501218269

import java.util.Arrays;
import java.util.Scanner;

// The city consists of a grid of 9 X 9 City Blocks

// Streets are east-west (1st street to 9th street)
// Avenues are north-south (1st avenue to 9th avenue)

// Example 1 of Interpreting an address:  "34 4th Street"
// A valid address *always* has 3 parts.
// Part 1: Street/Avenue residence numbers are always 2 digits (e.g. 34).
// Part 2: Must be 'n'th or 1st or 2nd or 3rd (e.g. where n => 1...9)
// Part 3: Must be "Street" or "Avenue" (case insensitive)

// Use the first digit of the residence number (e.g. 3 of the number 34) to determine the avenue.
// For distance calculation you need to identify the the specific city block - in this example 
// it is city block (3, 4) (3rd avenue and 4th street)

// Example 2 of Interpreting an address:  "51 7th Avenue"
// Use the first digit of the residence number (i.e. 5 of the number 51) to determine street.
// For distance calculation you need to identify the the specific city block - 
// in this example it is city block (7, 5) (7th avenue and 5th street)
//
// Distance in city blocks between (3, 4) and (7, 5) is then == 5 city blocks
// i.e. (7 - 3) + (5 - 4) 

public class CityMap
{

  

  // Checks for string consisting of all digits
  // An easier solution would use String method matches()
  private static boolean allDigits(String s)
  {
    // for (int i = 0; i < s.length(); i++)
    //   if (!Character.isDigit(s.charAt(i)))
    //     return false;
    // return  true;
    return s.matches("[0-9]*");
  }



  // Get all parts of address string
  // An easier solution would use String method split()
  // Other solutions are possible - you may replace this code if you wish
  private static String[] getParts(String address)
  {
    // String parts[] = new String[3];
    
    // if (address == null || address.length() == 0)
    // {
    //   parts = new String[0];
    //   return parts;
    // }
    // int numParts = 0;
    // Scanner sc = new Scanner(address);
    // while (sc.hasNext())
    // {
    //   if (numParts >= 3)
    //     parts = Arrays.copyOf(parts, parts.length+1);

    //   parts[numParts] = sc.next();
    //   numParts++;
    // }
    // if (numParts == 1)
    //   parts = Arrays.copyOf(parts, 1);
    // else if (numParts == 2)
    //   parts = Arrays.copyOf(parts, 2);
    // return parts;

    String[] parts = address.split(" ", 3);
    // String parts[] = new String[3];
    if (address.length() == 0 || address == null){
      parts = new String[0];
      
    }

    
    return parts;
    
  }

  // Checks for a valid address
  public static boolean validAddress(String address)
  {
    // Fill in the code
    // Make use of the helper methods above if you wish
    // There are quite a few error conditions to check for 
    // e.g. number of parts != 3
    // if (!allDigits(address)){
    //   System.out.println("Invalid type 1");
    //   return false;
    // }
    int[] block = {-1,-1};

    String[] parts = getParts(address);
    if (parts.length != 3){
      System.out.println("Invalid type 2");
      return false;
    }
    //Part 1
    if (!allDigits(parts[0])){
      System.out.println("Part 1 not all digits");
      return false;
    }

    if (parts[0].length() != 2 && parts[1].length() != 3){
      System.out.println("Invalid Address part 1 and 2");
      return false;
    }
    //Part 2
    if (!allDigits(parts[1].substring(0,1))){
      System.out.print("first letter of part 2 not digit");
      return false;
    }

    if (!parts[1].equals("1st") && !parts[1].equals("2nd") && !parts[1].equals("3rd") && !parts[1].substring(1).equals("th")){
      System.out.println("Part 2 invalid");
      return false;
    }
    //Part 3
    if (!parts[2].equalsIgnoreCase("street") && !parts[2].equalsIgnoreCase("avenue")){
      System.out.println("Not street or avenue");
      return false;
    }

    int num1 = Integer.parseInt(parts[0])/10;
    int num2 = Integer.parseInt(parts[1].substring(0,1));
    if (parts[2].equalsIgnoreCase("street")){
      block[0] = num1;
      block[1] = num2;
    }
    else{
      block[0] = num2;
      block[1] = num1;
    }

    

    // if (firstPart.length() != 2){
    //   System.out.println("Invalid type 3");
    //   return false;
    // }
    
    
    // if ((int)secondPart.charAt(0) < 0 || (int)secondPart.charAt(0) > 9){
    //   System.out.println("Invalid type 4");
    //   return false;
    // }

    // if (secondPart != "1st" || secondPart != "2nd" || secondPart != "3rd"){
    //   if (((int)secondPart.charAt(0) > 3) && ((int)secondPart.charAt(0) <= 9)){
    //     if (!secondPart.contains("th")){
    //       System.out.println("Invalid type 5");
    //       return false;
    //     }
    //   }
    // }
    // String suffix = secondPart.substring(1);
    // if (secondPart.length() != 3) return false;

    // if (!suffix.equals("th") && !secondPart.equals("1st") &&
    //     !secondPart.equals("2nd") && !secondPart.equals("3rd")) return false;

    // String digitStr = secondPart.substring(0, 1);
    // if (!allDigits(digitStr)) return false;
    // int num2 = Integer.parseInt(digitStr);
    // // As we have already ensured above that second part is of length 3 it means the number is one digit
    // if (num2 == 0) return false;

    

    // if (!thirdPart.equalsIgnoreCase("STREET") && !thirdPart.equalsIgnoreCase("AVENUE")){
    //   System.out.println("Invalid type 6");
    //   return false;
    // }
    
    // if (!allDigits(firstPart) || firstPart.length() !=2) return false;

    // int num1 = Integer.parseInt(firstPart)/10;
    // if (num1 == 0) return false;

    // if (street){
    //   block[0] = num2;
    //   block[1] = num1;
    // }
    // else{
    //   block[0] = num1;
    //   block[1] = num2;
    // }

    return true;
  }

  // Computes the city block coordinates from an address string
  // returns an int array of size 2. e.g. [3, 4] 
  // where 3 is the avenue and 4 the street
  // See comments at the top for a more detailed explanation
  public static int[] getCityBlock(String address)
  {
    int[] block = {-1, -1};
    
    

    // Fill in the code
    String[] parts = getParts(address);

    

    // if (thirdPart.equalsIgnoreCase("STREET")){
    //   int avenue = Integer.parseInt(firstPart)/10;
    //   int street = Integer.parseInt(secondPart.substring(0,1));
    //   block = new int[] {avenue, street}; 
    // }
    // else if (thirdPart.equalsIgnoreCase("AVENUE")){
    //   int avenue = (int)secondPart.charAt(0);
    //   int street = (int) firstPart.charAt(0);
    //   block = new int[] {avenue, street};
    // }
    if (!validAddress(address)){
      System.out.println("get city block says not valid address");
      return null;
    }

    int num1 = Integer.parseInt(parts[0])/10;
    int num2 = Integer.parseInt(parts[1].substring(0,1));
    if (parts[2].equalsIgnoreCase("street")){
      block[0] = num1;
      block[1] = num2;
    }
    else{
      block[0] = num2;
      block[1] = num1;
    }
    
    return block;
  }
  
  // Calculates the distance in city blocks between the 'from' address and 'to' address
  // Hint: be careful not to generate negative distances
  
  // This skeleton version generates a random distance
  // If you do not want to attempt this method, you may use this default code
  public static int getDistance(String from, String to)
  {
    // Fill in the code or use this default code below. If you use
    // the default code then you are not eligible for any marks for this part
    int[] fromBlock = {0,0};
    int[] toBlock = {0,0};

    if (validAddress(from) && validAddress(to)){
      fromBlock = getCityBlock(from);
      toBlock = getCityBlock(to);
    }

    int distance = Math.abs(toBlock[0] - fromBlock[0]) + Math.abs(toBlock[1] - fromBlock[1]);
    return Math.abs(distance);
    // Math.random() generates random number from 0.0 to 0.999
    // Hence, Math.random()*17 will be from 0.0 to 16.999
    // double doubleRandomNumber = Math.random() * 17;
    // // cast the double to whole number
    // int randomNumber = (int)doubleRandomNumber;
    // return (randomNumber);
  }
  public static int getCityZone(String address){
    int zone = -1;
    if (!validAddress(address)) return -1;

    int[] block = getCityBlock(address);
    int avenue = block[0];
    int street = block[1];

    String[] parts = getParts(address);

    if ((avenue >= 1 && avenue <=5) 
    && (street >=6 && street <= 9)){
      zone = 0;

    }
    else if ((avenue >= 6 && avenue <= 9)
    && (street >= 6 && street <= 9)){
      zone = 1;
    }
    else if ((avenue >= 6 && avenue <=9)
    && (street >= 1 && street <= 5)){
      zone = 2;
    }
    else if ((avenue >= 1 && avenue <= 5) 
    && (street >= 1 && street <=5)){
      zone = 3;
    }
    return zone;
    
  }
}

