package model;

import java.time.LocalDateTime;

/**
 * This class creates a User model
 *
 * @author Ella Upchurch
 */
public class User {

    private int userID;
    private String username;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * This method constructs the user object
     *
     * @param userID user ID
     * @param username user's username
     * @param password user's password
     * @param createDate account creation date
     * @param createdBy account creator
     * @param lastUpdate date account was last updated
     * @param lastUpdatedBy who last update the account
     */
    public User(int userID, String username, String password, LocalDateTime createDate, String createdBy,
                LocalDateTime lastUpdate, String lastUpdatedBy) {

        this.userID = userID;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;

    }

    /**
     * This method constructs a user object with 2 arguments
     *
     * @param userID
     * @param username
     */
    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    /**
     * The getter for user ID
     * @return user ID
     */
    public int getUserID() {return userID;}
    /**
     * The setter for user ID
     * @param userID
     */
    public void setUserID(int userID) {this.userID = userID;}

    /**
     * The getter for username
     * @return username
     */

    public String getUsername() {return username;}
    /**
     * The setter for username
     * @param username
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * The getter for user password
     * @return user password
     */
    public String getPassword() {return username;}
    /**
     * The setter for user password
     * @param user password
     */
    public void setPassword(String user) {this.password = password;}

    /**
     * The getter for creation date
     * @return creation date
     */
    public LocalDateTime getCreateDate() {return createDate;}
    /**
     * The setter for creation date
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate;}

    /**
     * The getter for account creator
     * @return created by
     */
    public String getCreatedBy() {return createdBy;}
    /**
     * The setter for account creator
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}

    /**
     * The getter for last update
     * @return last updated
     */
    public LocalDateTime getLastUpdate() {return lastUpdate;}
    /**
     * The setter for last update
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {this.lastUpdate = lastUpdate;}

    /**
     * The getter for last updated by
     * @return last updated by
     */
    public String getLastUpdatedBy() {return lastUpdatedBy;}
    /**
     * The setter for last updated by
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {this.lastUpdatedBy = lastUpdatedBy;}

    @Override
    public String toString() {return (username);}

}
