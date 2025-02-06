package com.example.databaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String KEY_DBNAME="userdb";
    public static final String KEY_TABLE="User";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_AGE="age";
    public static final int DB_VERSION=1;

    public DatabaseHelper(Context context) {
        super(context, KEY_DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,age INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertUser(UserData user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,user.getName());
        values.put(KEY_EMAIL,user.getEmail());
        values.put(KEY_AGE,user.getAge());
        long id = database.insert(KEY_TABLE,null,values);
        return id;
    }
    public ArrayList<UserData> getAllUsers(){
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<UserData> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                UserData user = new UserData();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setAge(cursor.getInt(3));
                user.setImgid(R.drawable.user);
                users.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }
    public int deleteUser(int id){
        SQLiteDatabase database = getWritableDatabase();
        int count = database.delete(KEY_TABLE,"id=?",new String[]{String.valueOf(id)});
        return count;
    }
    public int updateUser(UserData user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,user.getName());
        values.put(KEY_EMAIL,user.getEmail());
        values.put(KEY_AGE,user.getAge());
        int count = database.update(KEY_TABLE,values,"id=?",new String[]{String.valueOf(user.getId())});
        return count;
    }
    public ArrayList<UserData> searchUser(String name) {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<UserData> userlist = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM User WHERE name LIKE?", new String[]{"%"+name+"%"});
        if(cursor.moveToFirst()){
            do{
                UserData user = new UserData();
                user.setImgid(R.drawable.user);
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setAge(cursor.getInt(3));
                userlist.add(user);
            }while (cursor.moveToNext());
        }
        return userlist;
    }
}
