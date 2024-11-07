// Name: Maitreyee Das Urmi
// Student ID: 501218269

import java.io.FileNotFoundException;
import java.io.IOException;                                                                                                                                                                  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Command-line based Uber App 

// This system supports "ride sharing" service and a delivery service

public class TMUberUI
{
  public static void main(String[] args)
  {
    // Create the System Manager - the main system code is in here 

    TMUberSystemManager tmuber = new TMUberSystemManager();
    
    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine())
    {
      String action = scanner.nextLine();
      
      if (action == null || action.equals("")) 
      {
        System.out.print("\n>");
        continue;
      }
      // Quit the App
      else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
      
        return;
      
      // Print all the registered drivers
      else if (action.equalsIgnoreCase("DRIVERS"))  // List all drivers
      {
        tmuber.listAllDrivers(); 
      }
      // Print all the registered users
      else if (action.equalsIgnoreCase("USERS"))  // List all users
      {
        tmuber.listAllUsers(); 
      }
      // Print all current ride requests or delivery requests
      else if (action.equalsIgnoreCase("REQUESTS"))  // List all requests
      {
        tmuber.listAllServiceRequests(); 
      }
      // Register a new driver
      else if (action.equalsIgnoreCase("REGDRIVER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine().strip();
        }
        String carModel = "";
        System.out.print("Car Model: ");
        if (scanner.hasNextLine())
        {
          carModel = scanner.nextLine().strip();
        }
        String license = "";
        System.out.print("Car License: ");
        if (scanner.hasNextLine())
        {
          license = scanner.nextLine().strip();
        }
        String address = "";
        System.out.println("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine().strip();
        }
        try
        {
          tmuber.registerNewDriver(name, carModel, license, address);
          System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s Address: %-15s", name, carModel, license, address);
        }
        catch (InvalidInputException e)
        {
        System.out.println(e.getMessage());
        
        }
        catch (DriverAlreadyExistsException e)
        {
         System.out.println(e.getMessage());
        }
      }
      // Register a new user
      else if (action.equalsIgnoreCase("REGUSER")) 
      {
        String name = "";
        System.out.print("Name: ");
        if (scanner.hasNextLine())
        {
          name = scanner.nextLine().strip();
        }
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine().strip();
        }
        double wallet = 0.0;
        System.out.print("Wallet: ");
        if (scanner.hasNextDouble())
        {
          wallet = scanner.nextDouble();
          scanner.nextLine(); // consume nl!! Only needed when mixing strings and int/double
        }
        try
        {
          tmuber.registerNewUser(name, address, wallet);
          System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
        }
        catch (InvalidInputException e)
        {
        System.out.println(e.getMessage());
        
        }
        catch (InsufficientAmountException e)
        {
         System.out.println(e.getMessage());
        }
    	catch (UserAlreadyExistsException e)
	{
	System.out.println(e.getMessage());
	}
        
      }
      // Request a ride
      else if (action.equalsIgnoreCase("REQRIDE")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestRide() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)

        // Asks for user account id
        
        String accountId = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine())
        {
          accountId = scanner.nextLine().strip();
        }

        // Asks for from address
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine().strip();
        }
        

        // Asks for to address
        
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine().strip();
        }
        
        
        // Checks if a ride can be requested by calling the requestRide() method  
        try
        {
          tmuber.requestRide(accountId, from, to);
          System.out.printf("RIDE for: %-15s From: %-15s To: %-15s", tmuber.getUser(accountId).getName(), from, to);

        }
        catch (UserNotFoundException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (InvalidInputException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (InsufficientAmountException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (RequestAlreadyExistsException e)
        {
        System.out.println(e.getMessage());
        
        }
	
	
        
      }
      // Request a food delivery
      else if (action.equalsIgnoreCase("REQDLVY")) 
      {
        // Get the following information from the user (on separate lines)
        // Then use the TMUberSystemManager requestDelivery() method properly to make a ride request
        // "User Account Id: "      (string)
        // "From Address: "         (string)
        // "To Address: "           (string)
        // "Restaurant: "           (string)
        // "Food Order #: "         (string)

        // Asks for account id
        
        String accountId = "";
        System.out.print("User Account Id: ");
        if (scanner.hasNextLine())
        {
          accountId = scanner.nextLine().strip();
        }

        // Asks for from address
        String from = "";
        System.out.print("From Address: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine().strip();
        }
        

        // Asks for to address
        
        String to = "";
        System.out.print("To Address: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine().strip();
        }
        // Asks for restaurant name
        
        String restaurant = "";
        System.out.print("Restaurant: ");
        if (scanner.hasNextLine())
        {
          restaurant = scanner.nextLine().strip();
        }

        //Asks for food order number
        
        String foodOrder = "";
        System.out.print("Food Order #: ");
        if (scanner.hasNextLine())
        {
          foodOrder = scanner.nextLine().strip();
        }
        // Checks if a delivery request can be made by calling the requestDelivery() method
        try 
        {
          tmuber.requestDelivery(accountId, from, to, restaurant, foodOrder);
          System.out.printf("DELIVERY for: %-15s From: %-15s To: %-15s Restaurant: %-15s Food Oder #: %-15s", tmuber.getUser(accountId).getName(), from, to, restaurant, foodOrder);

        }
        catch (UserNotFoundException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (InvalidInputException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (InsufficientAmountException e)
        {
        System.out.println(e.getMessage());
        
        }
	catch (RequestAlreadyExistsException e)
        {
        System.out.println(e.getMessage());
        
        }
	
        
      }
      // Sort users by name
      else if (action.equalsIgnoreCase("SORTBYNAME")) 
      {
        ArrayList<User> sorted = tmuber.sortByUserName();
        System.out.println();
        int index = 1;
        for (User user : sorted){
          System.out.printf("%-2s. ", index);
          user.printInfo();
          System.out.println();
          index++;
        }
      }
      // Sort users by number of ride they have had
      else if (action.equalsIgnoreCase("SORTBYWALLET")) 
      {
        ArrayList<User> sorted = tmuber.sortByWallet();
        System.out.println();
        int index = 1;
        for (User user : sorted){
          System.out.printf("%-2s. ", index);
          user.printInfo();
          System.out.println();
          index++;
        }
      }
      
      // Cancel a current service (ride or delivery) request
      else if (action.equalsIgnoreCase("CANCELREQ")) 
      {
        int request = -1;
        System.out.print("Request #: ");
        if (scanner.hasNextInt())
        {
          request = scanner.nextInt();
          scanner.nextLine(); // consume nl character
        }
        int zone = 0;
        System.out.print("Zone #: ");
        if (scanner.hasNextInt())
        {
          zone = scanner.nextInt();
          scanner.nextLine();  // consume nl character
        }
        try
        {
          tmuber.cancelServiceRequest(request,zone);
          System.out.println("Service Request # " + request + " at Zone " + zone + " is Cancelled");

        }
        catch (InvalidInputException e)
        {
          System.out.println(e.getMessage());
        }
      
      }
      // Drop-off the user or the food delivery to the destination address
      else if (action.equalsIgnoreCase("DROPOFF")) 
      {
        String driverId = "";
        System.out.print("Driver ID: ");
        if (scanner.hasNextLine())
        {
          driverId = scanner.nextLine().strip();
	        

          
        }
        try
        {
          tmuber.dropOff(driverId);
          // System.out.println("Successful Drop Off - Service request #" +  " complete");

        }
        catch (DriverNotFound e)
        {
          System.out.println(e.getMessage());
        }
        
      }
      // Get the Current Total Revenues
      else if (action.equalsIgnoreCase("REVENUES")) 
      {
        System.out.println("Total Revenue: " + tmuber.totalRevenue);
      }
      // Unit Test of Valid City Address 
      else if (action.equalsIgnoreCase("ADDR")) 
      {
        String address = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          address = scanner.nextLine().strip();
        }
        System.out.print(address);
        if (CityMap.validAddress(address))
        {
          System.out.println("\nValid Address"); 
        }
        else
        {
          System.out.println("\nBad Address"); 
        }
      }
      // Unit Test of CityMap Distance Method
      else if (action.equalsIgnoreCase("DIST")) 
      {
        String from = "";
        System.out.print("From: ");
        if (scanner.hasNextLine())
        {
          from = scanner.nextLine().strip();
        }
        String to = "";
        System.out.print("To: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine().strip();
        }
        System.out.print("\nFrom: " + from + " To: " + to);
        System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
      }
      //pickup
      else if (action.equalsIgnoreCase("PICKUP"))
      {
        String driverId = "";
        System.out.print("Driver ID: ");
        if (scanner.hasNextLine())
        {
          driverId = scanner.nextLine().strip();
        }
        try
        {
          tmuber.pickup(driverId);
          
        }
        catch (EmtpyQueueException e)
        {
          System.out.println(e.getMessage());
        }
      }
      //Load users
      else if (action.equalsIgnoreCase("LOADUSERS"))
      {
        boolean retry = true;
        while (retry)
        {
          String filename = "";
          System.out.print("Filename: ");
          if (scanner.hasNextLine())
          {
            filename = scanner.nextLine().strip();
          }
          try
          {
            ArrayList<User> userList = TMUberRegistered.loadPreregisteredUsers(filename);
            tmuber.setUsers(userList);
            System.out.println("Users Loaded");
            retry = false;
            
          }
          catch (FileNotFoundException e)
          {
            System.out.println("Users File: " + filename + " Not Found");

          }
          catch (IOException e)
          {
            System.out.println("Invalid user list " + e.getMessage());
            retry = false;
            System.exit(0);
          }

        }
        
        
      }
      //Load drivers
      else if (action.equalsIgnoreCase("LOADDRIVERS"))
      {
        boolean retry = true;
        while (retry)
        {
          String filename = "";
          System.out.print("Filename: ");
          if (scanner.hasNextLine())
          {
            filename = scanner.nextLine().strip();
          }
          try
          {
            ArrayList<Driver> driverList = TMUberRegistered.loadPreregisteredDrivers(filename);
            System.out.println("Drivers Loaded");
            tmuber.setDrivers(driverList);
            retry = false;
          }
          
          
          catch (FileNotFoundException e)
          {
            System.out.println("Drivers File: " + filename + " Not Found");

          }
          catch (IOException e)
          {
            System.out.println("Invalid driver list " + e.getMessage());
            retry = false;
            System.exit(0);
          }

        }
        
        
      }
      // Drive to
      else if (action.equalsIgnoreCase("DRIVETO"))
      {
        String driverId = "";
        System.out.print("Driver ID: ");
        if (scanner.hasNextLine())
        {
          driverId = scanner.nextLine().strip();
        }
        String to = "";
        System.out.print("Address: ");
        if (scanner.hasNextLine())
        {
          to = scanner.nextLine().strip();
        }
        try
        {
        tmuber.driveTo(driverId, to);
        }
        catch (DriverNotFound e)
        {
          System.out.println(e.getMessage());
        }
	catch (InvalidInputException e)
        {
          System.out.println(e.getMessage());
        }

      }
    
      System.out.print("\n>");
    }
  }
}

