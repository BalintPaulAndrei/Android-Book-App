package com.example.tema3android.models;

import android.app.Application;

import androidx.room.Room;

import com.example.tema3android.data.BookDataBase;
import com.example.tema3android.models.dbEntities.Book;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    private static BookDataBase db;

    private static final String dbName = "BooksDatabase";

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        setupDatabase();
    }

    private void setupDatabase()
    {
        db = Room.databaseBuilder(getApplicationContext(), BookDataBase.class, dbName).fallbackToDestructiveMigration().build();
    }

    public static BookDataBase getDatabase()
    {
        return db;
    }

    public static ApplicationController getInstance()
    {
        return  instance;
    }

}
