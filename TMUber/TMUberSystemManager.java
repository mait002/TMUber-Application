// Name: Maitreyee Das Urmi
// Student ID: 501218269

//import java.sql.Driver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.zip.InflaterInputStream;

/*
 * 
 * This class contains the main logic of the system.
 * 
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY)
 * 
 */
public class TMUberSystemManager
{
  //private ArrayList<User>   users;
  private Map<String, User> users;
  private ArrayList<Driver> drivers;

  private Queue<TMUberService>[] queues; 

  public double totalRevenue; // Total revenues accumulated via rides and deliveries
  
  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;
  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  //These variables are used to generate user account and driver ids
  int userAccountId = 9000;
  int driverId = 7000;
  @SuppressWarnings("unchecked")
  public TMUberSystemManager()
  {
    users   = new TreeMap<String, User>();
    
    drivers = new ArrayList<Driver>();
    
    queues = (Queue<TMUberService>[]) new LinkedList[4];
    for (int i = 0; i < queues.length; i++){
      queues[i] = new LinkedList<TMUberService>();
    }
    
    
    
    totalRevenue = 0;
  }

  // General string variable used to store an error message when something is invalid 
  // (e.g. user does not exist, invalid address etc.)  
  // The methods below will set this errMsg string and then return false
  // String errMsg = null;

  
  
  // Given user account id, find user in list of users
  // Return null if not found
  
  public Queue<TMUberService> queues(int i){
    if (i < 0 || i > queues.length){
      throw new InvalidInputException("Invalid Zone # ");
    }
    
    return queues[i];
      
    

  }
  public void setUsers(ArrayList<User> userList){
    
    for (User user : userList){
      if (!users.containsKey(user.getAccountId())){
        users.put(user.getAccountId(), user);
        
      }

      
    }
    
  }
  public void setDrivers(ArrayList<Driver> driverList){
    drivers = driverList;

  }
  public User getUser(String accountId)
  {
    // Fill in the code

    for (Map.Entry<String, User> user : users.entrySet()){
      if (user.getKey().equals(accountId)){
        return user.getValue();
      }
    }
    return null;
  }

  public Driver getDriver(String driverId){
    for (Driver driver : drivers){
      if (driver.getId().equals(driverId)){
        return driver;
      }
    }
    return null;
  }
  
  // Check for duplicate user
  private boolean userExists(User user)
  {
    // Fill in the code
    for (Map.Entry<String, User> u : users.entrySet()){
      if (u.getValue().equals(user)){
        return true;
      } 
    }
    return false;
  }
  
 // Check for duplicate driver
 private boolean driverExists(Driver driver)
 {
   // Fill in the code
  return drivers.contains(driver);
 }
  
  // Given a user, check if user ride/delivery request already exists in service requests
  private boolean existingRequest(TMUberService req)
  {
    // Fill in the code
    for (int i = 0; i < queues.length; i++){
      if (queues[i].contains(req)){
        return true;
      }

    }
    return false;
  }

  // Calculate the cost of a ride or of a delivery based on distance 
  private double getDeliveryCost(int distance)
  {
    return distance * DELIVERYRATE;
  }

  private double getRideCost(int distance)
  {
    return distance * RIDERATE;
  }

  // Go through all drivers and see if one is available
  // Choose the first available driver
  // Return null if no available driver
  private Driver getAvailableDriver()
  {
    // Fill in the code
    for (int i = 0; i < drivers.size(); i++){
      if (drivers.get(i).getStatus() == Driver.Status.AVAILABLE){
        return drivers.get(i);
        
      }
    }
    return null;
  }

  // Print Information (printInfo()) about all registered users in the system
  public void listAllUsers()
  {
    System.out.println();
    int i = 0;
    for (Map.Entry<String, User> user : users.entrySet())
    {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      user.getValue().printInfo();
      System.out.println(); 
      i++;
    }
  }

  // Print Information (printInfo()) about all registered drivers in the system
  public void listAllDrivers()
  {
    // Fill in the code
    System.out.println();
    // Goes through the list of drivers
    for (int i = 0; i < drivers.size(); i++)
    {
      // records the current index of the driver (as indexing initially begins from 0 in lists and arrays, we increment by 1)
      int index = i + 1;

      // Print the index which is left aligned, has a width of 2 followed by a '.'
      System.out.printf("%-2s. ", index);

      // Print info of the following driver
      drivers.get(i).printInfo();

      System.out.println();
      
      

    }

  }

  // Print Information (printInfo()) about all current service requests
  public void listAllServiceRequests()
  {
    // Fill in the code
    System.out.println();
    int count = 1;
    for (int i = 0; i < queues.length; i++){
      System.out.println();
      System.out.println("ZONE " + i);
      System.out.println("======");
      
       
      for (int j = 0; j < queues[i].size(); j++){
        int index = j + count;
        
        System.out.println();
        System.out.print(index + ". ");
        for (int m = 0; m < 60; m++){
        System.out.print("-");
        
        }
        TMUberService service = queues[i].peek();
        service.printInfo();
        System.out.println();
      }
      count = queues[i].size() + 1;
      
    }
    
  }

  // Add a new user to the system
  public void registerNewUser(String name, String address, double wallet)
  {
    // Fill in the code. Before creating a new user, check paramters for validity
    // See the assignment document for list of possible erros that might apply
    // Write the code like (for example):
    // if (address is *not* valid)
    // {
    //    set errMsg string variable to "Invalid Address "
    //    return false
    // }
    // If all parameter checks pass then create and add new user to array list users
    // Make sure you check if this user doesn't already exist!

    

    // Check if user name is valid
    if (name.equals("") || name == null)
    {
      throw new InvalidInputException("Invalid User Name " + name);
    }

    // check if user address is valid
    if (!CityMap.validAddress(address))
    {
      throw new InvalidInputException("Invalid User Address " + address);
    }

    // check is money in wallet is valid
    if (wallet < 0)
    {
      throw new InsufficientAmountException("Insufficient Funds ");
    }

    // create a new user
    ArrayList<User> valueSet = new ArrayList<User>();
    for (String key : users.keySet()){
      valueSet.add(users.get(key));
    }
    String accountId = TMUberRegistered.generateUserAccountId(valueSet);

    User user = new User(accountId, name, address, wallet);

    // check is user already exists
    if (userExists(user)){
      throw new UserAlreadyExistsException("User Already Exists in the System");
    }

    // add new user to the users list
    users.put(accountId, user);
    
  }

  // Add a new driver to the system
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address)
  {
    // Fill in the code - see the assignment document for error conditions
    // that might apply. See comments above in registerNewUser

    

    if (name.equals("") || name == null)
    {
      throw new InvalidInputException("Invalid Driver name " + name);
    }

    // check if car model is valid
    if (carModel.equals("") || carModel == null){
      throw new InvalidInputException("Invalid Car Model " + carModel);
    }

    // check if car licence plate
    if (carLicencePlate.equals("") || carLicencePlate == null){
      throw new InvalidInputException("Invalid Car License Plate " + carModel);
    }

    if (!CityMap.validAddress(address) || address.equals("") || address == null){
      throw new InvalidInputException("Invalid Driver Address " + address);

    }
    int zone = CityMap.getCityZone(address);
    String driverId = TMUberRegistered.generateDriverId(drivers);
    Driver driver = new Driver(driverId, name, carModel, carLicencePlate, address, null, zone);

    // check is driver exists
    if (driverExists(driver)){
      throw new DriverAlreadyExistsException("Driver Already Exists in the System");
    }
    // add new driver to the list of drivers
    drivers.add(driver);
    
  }

  // Request a ride. User wallet will be reduced when drop off happens
  public void requestRide(String accountId, String from, String to)
  {
    // Check for valid parameters
	  // Use the account id to find the user object in the list of users
    // Get the distance for this ride
    // Note: distance must be > 1 city block!
    // Find an available driver
    // Create the TMUberRide object
    // Check if existing ride request for this user - only one ride request per user at a time!
    // Change driver status
    // Add the ride request to the list of requests
    // Increment the number of rides for this user

    
    User user = getUser(accountId);
    if (user == null){
      throw new UserNotFoundException("User Account Not Found " + accountId);
    }

    if (!CityMap.validAddress(from)){
      throw new InvalidInputException("Invalid From Address: " + from);
    }
    if (!CityMap.validAddress(to)){
      throw new InvalidInputException("Invalid To Address: " + to);
    }
    
    int distance = CityMap.getDistance(from, to);

    if (distance <= 1){
      throw new InsufficientAmountException("Insufficient Travel Distance ");
    }


    double cost = getRideCost(distance);
    if (user.getWallet() < cost){
      throw new InsufficientAmountException("Insufficient Funds");
    }

    TMUberRide ride = new TMUberRide(from, to, user, distance, cost);

    if (existingRequest(ride)){
      
      throw new RequestAlreadyExistsException("Ride Request Already Exists");
    }
    int fromZone = CityMap.getCityZone(from);
    queues[fromZone].add(ride);
    
    user.addRide();
    
  }

  // Request a food delivery. User wallet will be reduced when drop off happens
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId)
  {
    // See the comments above and use them as a guide
    // For deliveries, an existing delivery has the same user, restaurant and food order id
    // Increment the number of deliveries the user has had
    User user = getUser(accountId);
    if (user == null){
      throw new UserNotFoundException("User Account Not Found " + accountId);
    }
    

    if (restaurant == null || restaurant.equals("")){
      throw new InvalidInputException("Invalid Restaurant Name " + restaurant);
    }

    if (foodOrderId == null || foodOrderId.equals("")){
      throw new InvalidInputException("Invalid Food Order Id " + foodOrderId);
    }

    //User user = getUser(accountId);

    if (!CityMap.validAddress(from)){
      throw new InvalidInputException("Invalid From Address: " + from);
    }
    if (!CityMap.validAddress(to)){
      throw new InvalidInputException("Invalid To Address: " + to);
    }

    int distance = CityMap.getDistance(from, to);

    if (distance <= 1){
      System.out.println(distance);
      throw new InsufficientAmountException("Insufficient Travel Distance");
    }

    

    double cost = getDeliveryCost(distance);
    if (user.getWallet() < cost){
      throw new InsufficientAmountException("Insufficient Funds");
    }

    TMUberDelivery delivery = new TMUberDelivery(from, to, user, distance, cost, restaurant, foodOrderId);

    if (existingRequest(delivery)){
      
      throw new RequestAlreadyExistsException("Delivery Request Already Exists");
    }
    int fromZone = CityMap.getCityZone(from);
    queues[fromZone].add(delivery);
    user.addDelivery();
    
  
    
  }


  // Cancel an existing service request. 
  // parameter int request is the index in the serviceRequests array list
  public void cancelServiceRequest(int request, int zone)
  {
    // Check if valid request #
    // Remove request from list
    // Also decrement number of rides or number of deliveries for this user
    // since this ride/delivery wasn't completed
    if (request <= 0){
      throw new InvalidInputException("Invalid Request # " + request);
    }
    int reqIndex = request - 1;
    int count = 0;
    TMUberService service = null;
    for (TMUberService s : queues[zone]){
      if (count == reqIndex){
        service = s;
      }
      count++;
    }
    User user = service.getUser();
    if (service.getServiceType().equals("DELIVERY")){
      user.removeDelivery();
    }
    else{
      user.removeRide();
    }
    for (int i = 0; i < queues[zone].size(); i++){
      if (i == reqIndex){
        queues[zone].remove();
      }
    }

    }
    //queues[zone].remove(reqIndex);
    
  
  
  
  // Drop off a ride or a delivery. This completes a service.
  // parameter request is the index in the serviceRequests array list
  public void dropOff(String driverId)
  {
    // See above method for guidance
    // Get the cost for the service and add to total revenues
    // Pay the driver
    // Deduct driver fee from total revenues
    // Change driver status
    // Deduct cost of service from user

    Driver driver = getDriver(driverId);
    if (driver.getStatus() != Driver.Status.DRIVING){
      throw new DriverNotFound("Invalid Driver ID: " + driverId);
    }
    TMUberService service = driver.getService();

    
    double cost = service.getCost();
    User user = service.getUser();
    totalRevenue += cost;
    
    double fee = cost * PAYRATE;
    driver.pay(fee);
    totalRevenue -= fee;
    user.payForService(cost);
    driver.setStatus(Driver.Status.AVAILABLE);
    driver.setService(null);
    driver.setAddress(service.getTo());
    
    driver.setZone(service.getTo());
    System.out.println("Driver " + driverId + " Dropping Off");

  
    
  }


  // Sort users by name
  // Then list all users
  
  public ArrayList<User> sortByUserName()
  {
    ArrayList<User> list = new ArrayList<User>(users.values());

    list.sort(new NameComparator());
    return list;
    // Collections.sort(list, new NameComparator());
    // listAllUsers();
  }

  // Helper class for method sortByUserName
  private class NameComparator implements Comparator<User>
  {
    public int compare(User u1, User u2){
      return u1.getName().compareTo(u2.getName()) ;
    }
  }

  // Sort users by number amount in wallet
  // Then ist all users
  public ArrayList<User> sortByWallet()
  {
    ArrayList<User> list = new ArrayList<User>(users.values());
    
    
    
    list.sort(new UserWalletComparator());
    return list;
  }
  // Helper class for use by sortByWallet
  private class UserWalletComparator implements Comparator<User>
  {
    public int compare(User u1, User u2){
      return Double.compare(u1.getWallet(), u2.getWallet());
    }
  
  }

  
  

  public void pickup(String driverId){
    Driver driver = getDriver(driverId);
    int zone = CityMap.getCityZone(driver.getAddress());
    if (queues[zone].isEmpty()){
      throw new EmtpyQueueException("No Service Request in Zone " + zone);

    }
    TMUberService service = queues[zone].remove();
    //TMUberService service1 = new TMUberService(driver.getAddress(), service.getTo(), service.getUser(), service.getDistance(), service.getCost(), service.getServiceType());
    driver.setService(service);
    driver.setStatus(Driver.Status.DRIVING);
    String address = service.getFrom();
    driver.setAddress(address);
    System.out.println("Driver " + driverId + " Picking Up in Zone " + zone);

  }

  public void driveTo(String driverId, String address){
    Driver driver = getDriver(driverId);
    if (driver == null){
      throw new DriverNotFound("Invalid Driver ID " + driverId);
    }
    if (driver.getStatus() == Driver.Status.DRIVING){
      throw new DriverNotFound("Driver is Not Available");
    }
    if (!CityMap.validAddress(address)){
      throw new InvalidInputException("Invalid Address " + address);
    }
    int zone = CityMap.getCityZone(address);
    driver.setAddress(address);
    driver.setZone(address);
    System.out.println("Driver " + driverId + " Now in Zone " + zone);
  }

  

}
 class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(){}
  public UserNotFoundException(String message){
    super(message);
  }

}
 class UserAlreadyExistsException extends RuntimeException{
  public UserAlreadyExistsException(){}
  public UserAlreadyExistsException(String message){
    super(message);
  }

}
 class InvalidInputException extends RuntimeException{
  public InvalidInputException(){}
  public InvalidInputException(String message){
    super(message);
  }
}
 class InsufficientAmountException extends RuntimeException{
  public InsufficientAmountException(){}
  public InsufficientAmountException(String message){
    super(message);
  }
}
 class DriverNotFound extends RuntimeException{
  public DriverNotFound(){}
  public DriverNotFound(String message){
    super(message);
  }
}
 class DriverAlreadyExistsException extends RuntimeException{
  public DriverAlreadyExistsException(){}
  public DriverAlreadyExistsException(String message){
    super(message);
  }

}
 class RequestAlreadyExistsException extends RuntimeException{
  public RequestAlreadyExistsException(){}
  public RequestAlreadyExistsException(String message){
    super(message);
  }

}
 class UserDoesNotExistsException extends RuntimeException{
  public UserDoesNotExistsException(){}
  public UserDoesNotExistsException(String message){
    super(message);
  }
}
 class DriverDoesNotExistsException extends RuntimeException{
  public DriverDoesNotExistsException(){}
  public DriverDoesNotExistsException(String message){
    super(message);
  }
}
class EmtpyQueueException extends RuntimeException{
  public EmtpyQueueException(){}
  public EmtpyQueueException(String message){
    super(message);
  }
}