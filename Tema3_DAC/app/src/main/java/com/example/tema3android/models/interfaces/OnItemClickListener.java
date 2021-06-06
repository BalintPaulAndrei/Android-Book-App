package com.example.tema3android.models.interfaces;

import android.view.View;

import com.example.tema3android.models.dbEntities.Book;

public interface OnItemClickListener {
    public void onClick(Book book);
    public void onClick(Book book, View view);
}
