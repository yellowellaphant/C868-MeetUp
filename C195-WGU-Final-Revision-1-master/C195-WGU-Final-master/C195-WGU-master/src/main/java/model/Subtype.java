package model;

public class Subtype {

    private int subTypeID;
    private String subTypeName;
    //private int typeID;

    /**
     * Constructor for Subtype
     * @param subTypeID
     * @param subTypeName
     * @param typeID
     */
    public Subtype(int subTypeID, String subTypeName, int typeID) {

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


}
