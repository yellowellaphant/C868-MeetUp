package model;

public class Subtype {

    private int subTypeID;
    private String subTypeName;
    private int typeID;

    /**
     * Constructor for Subtype
     * @param subTypeID subTypeID
     * @param subTypeName subTypeName
     * @param typeID typeID
     */
    public Subtype(int subTypeID, String subTypeName, int typeID) {

        this.subTypeID = subTypeID;
        this.subTypeName = subTypeName;

    }

    /**
     * Constructor for Subtype
     * @param subTypeID subTypeID
     * @param subTypeName subTypeName
     */
    public Subtype(int subTypeID, String subTypeName) {

        this.subTypeID = subTypeID;
        this.subTypeName = subTypeName;

    }

    /**
     * getter for subTypeID
     * @return subTypeID
     */
    public int getSubTypeID() {
        return subTypeID;
    }

    /**
     * setter for subtype ID
     * @param subTypeID subtypeID
     */
    public void setSubTypeID(int subTypeID) {
        this.subTypeID = subTypeID;
    }

    /**
     * getter for subTypeName
     * @return subTypeName
     */
    public String getSubTypeName() {
        return subTypeName;
    }

    /**
     * setter for subtype name
     * @param subTypeName subTypeName
     */
    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    /**
     * returns string representation of subtypeName
     * @return getSubtypeName
     */
    @Override
    public String toString() {return getSubTypeName();}


}
