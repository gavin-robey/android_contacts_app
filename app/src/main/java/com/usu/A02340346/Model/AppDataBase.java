package com.usu.A02340346.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ContactModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
