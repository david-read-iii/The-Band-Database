package com.davidread.thebanddatabase;

/**
 * {@link Band} is a model class for a single band with properties for id, name, and description.
 */
public class Band {

    /**
     * Int for the unique id of the band.
     */
    private int mId;

    /**
     * {@link String} for the name of the band.
     */
    private String mName;

    /**
     * {@link String} for the description of the band.
     */
    private String mDescription;

    /**
     * Constructs a new {@link Band} with default and null member variables.
     */
    public Band() {
    }

    /**
     * Constructs a new {@link Band} with the given member variables.
     *
     * @param id          Unique id of the band.
     * @param name        Name of the band.
     * @param description Description of the band.
     */
    public Band(int id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
    }

    /**
     * Get the id for this band.
     */
    public int getId() {
        return mId;
    }

    /**
     * Set the id for this band.
     */
    public void setId(int id) {
        this.mId = id;
    }

    /**
     * Get the name for this band.
     */
    public String getName() {
        return mName;
    }

    /**
     * Set the name for this band.
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Get the description for this band.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Set the description for this band.
     */
    public void setDescription(String description) {
        this.mDescription = description;
    }
}
