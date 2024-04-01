package model;

import java.time.LocalDateTime;

/**
 * This class creates a country model
 *
 * @author Ella Upchurch
 */
public class Country {

    private int countryID;
    private String country;

    /**
     * Constructor for Country
     *
     * @param countryID
     * @param country
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public Country(int countryID, String country, LocalDateTime createDate, String createdBy,
                   LocalDateTime lastUpdate, String lastUpdatedBy) {

        this.countryID = countryID;
        this.country = country;
    }

    /**
     * Constructor for Country
     *
     * @param countryID
     * @param country
     */
    public Country(int countryID, String country) {

        this.countryID = countryID;
        this.country = country;
    }

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
     * The getter for country
     * @return country
     */
    public String getCountry() {return country;}

    /**
     * The setter for country
     * @param country
     */
    public void setCountry(String country) {this.country = country;}

    /**
     * Returns country name for display in combo box
     * @return country
     */
    @Override
    public String toString() {
        return country;
    }

}
