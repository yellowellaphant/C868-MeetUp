package model;

/**
 * Creates a type of meeting, External
 * @author Ella Upchurch
 */
public class External extends Type{

    int subTypeID;

    /**
     * constructs an instance of External
     * @param typeID typeID
     * @param typeName typeName
     * @param subTypeID subTypeID
     */
    public External(int typeID, String typeName, int subTypeID) {
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
