package com.example.tema3android.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    public Book(String title,String author,String description)
    {
        this.title = title;
        this.author = author;
        this.description = description;
    }


    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name= "title")
    public String title;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "description")
    public String description;
}
