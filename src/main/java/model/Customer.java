package model;

import java.time.LocalDateTime;

/**
 * This class creates the Customer model
 *
 * @author Ella Upchurch
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerDivisionID;
    private String customerDivisionName;
    private int customerCountryID;
    private String customerCountryName;

    //add ObservableList?

    /**
     * Constructs new instance of Customer
     *
     * @param customerID
     * @param customerName
     */
    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    /**Constructs a new instance of Customer
     *
     * @param customerID ID number of customer
     * @param customerName name of customer
     * @param address address of customer
     * @param postalCode postal code of customer
     * @param phone phone number of customer
     * @param createdBy creator of customer profile
     * @param lastUpdatedBy who update the customer profile most recently
     * @param customerDivisionID customer's division ID
     * @param customerDivisionName customer's division name
     * @param customerCountryID customer's country ID
     * @param customerCountryName customer's country name
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone,
                    String createdBy, String lastUpdatedBy, int customerDivisionID, String customerDivisionName,
                    int customerCountryID, String customerCountryName) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerDivisionID = customerDivisionID;
        this.customerDivisionName = customerDivisionName;
        this.customerCountryID = customerCountryID;
        this.customerCountryName = customerCountryName;
    }
    /*public Customer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime createDate,
                    String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerDivisionID, String customerDivisionName,
                    int customerCountryID, String customerCountryName){

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerDivisionID = customerDivisionID;
        this.customerDivisionName = customerDivisionName;
        this.customerCountryID = customerCountryID;
        this.customerCountryName = customerCountryName;
    }*/

    /**
     * The getter for customer ID
     * @return ID of customer
     */
    public int getCustomerID() {return customerID;}
    /**
     * The setter for customer ID
     * @param customerID
     */
    public void setCustomerID(int customerID) {this.customerID = customerID;}

    /**
     * The getter for customer name
     * @return customer name
     */
    public String getCustomerName() {return customerName;}
    /**
     * The setter for customer name
     * @param customerName
     */
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    /**
     * The getter for customer address
     * @return customer address
     */
    public String getAddress() {return address;}
    /**
     * The setter for customer address
     * @param address
     */
    public void setAddress(String address) {this.address = address;}

    /**
     * The getter for customer postal code
     * @return customer postal code
     */
    public String getPostalCode() {return postalCode;}
    /**
     * The setter for customer postal code
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    /**
     * The getter for customer phone number
     * @return phone number
     */
    public String getPhone() {return phone;}
    /**
     * The setter for customer phone number
     * @param phone
     */
    public void setPhone(String phone) {this.phone = phone;}

    /**
     * The getter for customer profile created date
     * @return date created
     */
    public LocalDateTime getCreateDate() {return createDate;}
    /**
     * The setter for customer profile created date
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate;}

    /**
     * The getter for created by
     * @return creator of customer profile
     */
    public String getCreatedBy() {return createdBy;}
    /**
     * The setter for created by
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}

    /**
     * The getter for last update
     * @return last time profile was updated
     */
    public LocalDateTime getLastUpdate() {return lastUpdate;}
    /**
     * The setter for last update
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {this.lastUpdate = lastUpdate;}

    /**
     * The getter for last updated by
     * @return who last updated the profile
     */
    public String getLastUpdatedBy() {return lastUpdatedBy;}
    /**
     * The setter for last updated by
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {this.lastUpdatedBy = lastUpdatedBy;}

    /**
     * The getter for division ID
     * @return customerDivisionID
     */
    public int getCustomerDivisionID() {return customerDivisionID;}
    /**
     * The setter for division ID
     * @param customerDivisionID
     */
    public void setCustomerDivisionID(int customerDivisionID) {this.customerDivisionID = customerDivisionID;}

    /**
     * The getter for customer's division name
     * @return customerDivisionName
     */
    public String getCustomerDivisionName() {return customerDivisionName;}
    /**
     * The setter for customer's division name
     * @param customerDivisionName
     */
    public void setCustomerDivisionName(String customerDivisionName) {this.customerCountryName = customerDivisionName;}

    /**
     * The getter for customer's country ID
     * @return customerCountryID
     */
    public int getCustomerCountryID() {return customerCountryID;}
    /**
     * The setter for customer's country ID
     * @param customerCountryID
     */
    public void setCustomerCountryID(int customerCountryID) {this.customerCountryID = customerCountryID;}

    /**
     * The getter for customer's country name
     * @return customerCountryName
     */
    public String getCustomerCountryName() {return customerCountryName;}
    /**
     * The getter for customer's country name
     * @param customerCountryName
     */
    public void setCustomerCountryName(String customerCountryName){this.customerCountryName = customerCountryName;}

    @Override
    public String toString() {return (customerName);}





}
