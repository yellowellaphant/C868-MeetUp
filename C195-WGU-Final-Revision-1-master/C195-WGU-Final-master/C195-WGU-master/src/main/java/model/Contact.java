package model;

/**
 * This class creates a contact model
 *
 * @author Ella Upchurch
 */
public class Contact {

    private int contactID;
    private String contactName;
    private String email;

    /**
     * Constructor for Contact
     *
     * @param contactID contact ID
     * @param contactName contact Name
     * @param email contact email
     */
    public Contact(int contactID, String contactName, String email) {

        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Getter for contact id
     *
     * @return contactId
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * Setter for contact id
     *
     * @param contactId contact id
     */
    public void setContactId(int contactId) {
        this.contactID = contactId;
    }

    /**
     * Getter for contact name
     *
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for contact name
     *
     * @param contactName contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter for contact email
     *
     * @return contactEmail
     */
    public String getContactEmail() {
        return email;
    }

    /**
     * Setter for contact email
     *
     * @param contactEmail contact email
     */
    public void setContactEmail(String contactEmail) {
        this.email = contactEmail;
    }
    /**
     * Override to display contactName as String
     *
     * @return contactName
     */
    @Override
    public String toString() {return (contactName);}

}
