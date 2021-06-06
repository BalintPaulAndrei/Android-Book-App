package com.example.tema3android.data;

import com.example.tema3android.data.tasks.DeleteBookTask;
import com.example.tema3android.data.tasks.GetAllBooksTask;
import com.example.tema3android.data.tasks.InsertBookTask;
import com.example.tema3android.data.tasks.UpdateBookTask;
import com.example.tema3android.models.ApplicationController;
import com.example.tema3android.models.dbEntities.Book;

import java.util.List;

public class BookRepository {

    private BookDataBase db;

    public static interface OnSuccesListener {

        void onSuccess();
    }

    public static interface OnInsertBookListener {

        void onSuccess();
    }

    public static  interface OnGetAllBooksListener {

        void onSuccess(List<Book> books);
    }



    public BookRepository()
    {
        db = ApplicationController.getDatabase();
    }

    public void insertBook(Book book, OnInsertBookListener listener)
    {
        new InsertBookTask(db,listener).execute(book);
    }

    public void getAllBooks(BookRepository.OnGetAllBooksListener listener)
    {
        new GetAllBooksTask(db,listener).execute();
    }

    public void updateBook(Book book, OnSuccesListener listener)
    {
        new UpdateBookTask(db,listener).execute(book);
    }

    public void deleteBook(Book book,OnSuccesListener listener)
    {
        new DeleteBookTask(db,listener).execute(book);
    }



}
