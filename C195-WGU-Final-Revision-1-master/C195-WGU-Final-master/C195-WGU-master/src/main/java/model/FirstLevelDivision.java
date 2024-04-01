package model;

import java.time.LocalDateTime;

/**
 * This class creates a First-Level Division model
 *
 * @author Ella Upchurch
 */
public class FirstLevelDivision {

    private int divisionID;
    private String division;
    private int countryID;

    /**
     * Constructs a new instance of FirstLevelDivision
     *
     * @param divisionID
     * @param division
     */
    public FirstLevelDivision(int divisionID, String division) {
        this.divisionID = divisionID;
        this.division =division;
    }
    /**
     * Constructor for FirstLevelDivision
     *
     * @param divisionID
     * @param division
     * @param countryID
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public FirstLevelDivision(int divisionID, String division, int countryID, LocalDateTime createDate,
                              String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {

        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * The getter for division ID
     * @return division ID
     */
    public int getDivisionID() {return divisionID;}

    /**
     * The getter for division ID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {this.divisionID = divisionID;}

    /**
     * The getter for division
     * @return division
     */
    public String getDivision() {return division;}
    /**
     * The setter for division
     * @param division
     */
    public void setDivision(String division) {this.division = division;}

    /**
     * The getter for country ID
     * @return country ID
     */
    public int getCountryID() {return countryID;}

    /**
     * The setter for country ID
     * @param countryID
     */
    public void setCountryID(int countryID) {this.countryID = countryID;}

    /**
    * Returns division name for display in combo box
    * @return division
    */
    @Override
    public String toString() {
        return division;
    }

}
