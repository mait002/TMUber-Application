// Name: Maitreyee Das Urmi
// Student ID: 501218269

/*
 * 
 * This class simulates a car driver in a simple uber app 
 * 
 * Everything has been done for you except the equals() method
 */

import java.util.Objects;

public class Driver
{
  private String id;
  private String name;
  private String carModel;
  private String licensePlate;
  private double wallet;
  private String type;
  
  public static enum Status {AVAILABLE, DRIVING};
  private Status status;
  private TMUberService service;
  private String address;
  private int zone;
    
  
  public Driver(String id, String name, String carModel, String licensePlate, String address, TMUberService service, int zone)
  {
    this.id = id;
    this.name = name;
    this.carModel = carModel;
    this.licensePlate = licensePlate;
    this.status = Status.AVAILABLE;
    this.wallet = 0;
    this.type = "";
    this.address = address;
    this.service = service;
    this.zone = zone;
    
    
  }
  // Print Information about a driver
  public void printInfo()
  {
    System.out.printf("Id: %-3s Name: %-15s Car Model: %-15s License Plate: %-10s Wallet: %2.2f Status: %-15s Address: %-15s Zone: %-15s", 
                      id, name, carModel, licensePlate, wallet, status, address, zone);
  }
  // Getters and Setters
  public TMUberService getService(){
    return service;
  }
  public void setService(TMUberService service){
    this.service = service;
  }
  public int getZone(){
    return zone;
  }
  public void setZone(String address){
    this.zone = CityMap.getCityZone(address);
  }

  public String getAddress(){
    return address;
  }
  public void setAddress(String address){
    this.address = address;
  }
  public String getType()
  {
    return type;
  }
  public void setType(String type)
  {
    this.type = type;
  }
  public String getId()
  {
    return id;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getCarModel()
  {
    return carModel;
  }
  public void setCarModel(String carModel)
  {
    this.carModel = carModel;
  }
  public String getLicensePlate()
  {
    return licensePlate;
  }
  public void setLicensePlate(String licensePlate)
  {
    this.licensePlate = licensePlate;
  }
  public Status getStatus()
  {
    return status;
  }
  public void setStatus(Status status)
  {
    this.status = status;
  }
  public double getWallet()
  {
    return wallet;
  }
  public void setWallet(double wallet)
  {
    this.wallet = wallet;
  }
  
  /*
   * Two drivers are equal if they have the same name and license plates.
   * This method is overriding the inherited method in superclass Object
   * 
   * Fill in the code 
   */
  public boolean equals(Object other)
  {
    if (this == other){
      return true;
    }

    if (other instanceof Driver)
    {
      Driver otherDriver = (Driver) other;
      return Objects.equals(this.name, otherDriver.name) && Objects.equals(this.licensePlate, otherDriver.licensePlate);

    }
    return false;
  }
  
  // A driver earns a fee for every ride or delivery
  public void pay(double fee)
  {
    wallet += fee;
  }
}
