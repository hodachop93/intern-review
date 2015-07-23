package intership.dev.contact.utils;

/**
 * Created by hodachop93 on 23/07/2015.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import intership.dev.contact.model.User;


/**
 * Created by hodachop93 on 7/22/2015.
 */
public class ContactDBHelper extends SQLiteOpenHelper {
    //Database name
    public static final String DATABASE = "Contacts";

    //Table name
    public static final String TABLE = "Users";

    //Database version
    public static int VERSiON = 1;
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_DESCRIPTION = "description";

    public ContactDBHelper(Context context) {
        super(context, DATABASE, null, VERSiON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " ( " + KEY_ID
                + " INTEGER PRIMARY KEY , " + KEY_USERNAME + " TEXT, "
                + KEY_DESCRIPTION + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * Get a list of users from database
     *
     * @return All users was read from database
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        //Select All query
        String selectQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setDescription(cursor.getString(2));

                users.add(user);
            } while (cursor.moveToNext());
        }

        if (db.isOpen()) {
            db.close();
        }
        return users;
    }

    /**
     * Add a user to database
     * @param user The user will be added to database
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_DESCRIPTION, user.getDescription());

        db.insert(TABLE, null, values);
        if (db.isOpen()) {
            db.close();
        }
    }

    /**
     * Update information of a user in database
     *
     * @param user The user will be affected
     * @return The number of row affected
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_DESCRIPTION, user.getDescription());

        // updating row
        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }


}
