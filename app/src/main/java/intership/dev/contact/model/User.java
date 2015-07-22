package intership.dev.contact.model;

import android.graphics.Bitmap;

/**
 * This class is used to control the information of a user
 * Created by hodachop93 on 21/07/2015.
 */
public class User {
    //Avatar of a user
    private Bitmap mAvatar;

    //Fullname of a user
    private String mUserName;

    //Information of a user
    private String mDescription;

    //Determine btnDelete is selected or not
    private boolean isDelete;

    //Default constructor
    public User() {
    }

    /**
     * Create a new user with information
     *
     * @param mAvatar      The avatar of a user
     * @param mUserName    The fullname of a user
     * @param mDescription Some information of a user
     */
    public User(Bitmap mAvatar, String mUserName, String mDescription) {
        this.mAvatar = mAvatar;
        this.mUserName = mUserName;
        this.mDescription = mDescription;
        isDelete = false;
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

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isDelete() {
        return isDelete;
    }
}
