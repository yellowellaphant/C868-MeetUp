package model;

/**
 * Creates a type of meeting, Internal
 * @author Ella Upchurch
 */
public class Internal extends Type{

    int subTypeID;

    /**
     * Constructs and instance of Internal
     * @param typeID typeID
     * @param typeName typeName
     * @param subTypeID subTypeID
     */
    public Internal(int typeID, String typeName, int subTypeID) {
        super(typeID, typeName);

        this.subTypeID = subTypeID;

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
     * @param subTypeID subTypeID
     */
    public void setSubTypeID(int subTypeID) {
        this.subTypeID = subTypeID;
    }

}
