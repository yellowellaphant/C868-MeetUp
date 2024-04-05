package model;

/**
 * creates abstract class, Type
 * @author Ella Upchurch
 */
public abstract class Type {

    private int typeID;
    private String typeName;

    /**
     * Constructs an instance of Type
     * @param typeID type id
     * @param typeName type name
     */
    public Type(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    /**
     * getter for typeID
     * @return typeID
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * setter for typeID
     * @param typeID type ID
     */
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    /**
     * getter for typeName
     * @return typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * setter for type name
     * @param typeName type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}
