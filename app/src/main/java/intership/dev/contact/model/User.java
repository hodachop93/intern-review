package intership.dev.contact.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * This class is used to control the information of a user
 * Created by hodachop93 on 21/07/2015.
 */
public class User implements Serializable {
    //User ID
    private int mID;

    //Avatar of a user
    private Bitmap mAvatar;

    //Fullname of a user
    private String mUserName;

    //Information of a user
    private String mDescription;

    //Determine btnDelete is selected or not
    private boolean isDelete;

    //The id of image used set for avatar
    private int mIdAvatar;

    //Default constructor
    public User() {
    }

    /**
     * Create a new user with information
     *
     * @param mID          The ID of a user
     * @param mAvatar      The avatar of a user
     * @param mUserName    The fullname of a user
     * @param mDescription Some information of a user
     * @param mIdAvatar    The id of image used set for avatar
     */
    public User(int mID, Bitmap mAvatar, String mUserName, String mDescription, int mIdAvatar) {
        this.mID = mID;
        this.mAvatar = mAvatar;
        this.mUserName = mUserName;
        this.mDescription = mDescription;
        this.mIdAvatar = mIdAvatar;

        isDelete = false;
    }


    public int getId() {
        return mID;
    }

    public void setId(int id) {
        this.mID = id;
    }

    public Bitmap getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Bitmap mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getIdAvatar() {
        return mIdAvatar;
    }

    public void setIdAvatar(int mIdAvatar) {
        this.mIdAvatar = mIdAvatar;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isDelete() {
        return isDelete;
    }
}
