package com.example.tema3android.data.tasks;

import android.os.AsyncTask;

import com.example.tema3android.data.BookDataBase;
import com.example.tema3android.data.BookRepository;
import com.example.tema3android.models.dbEntities.Book;

import java.util.ArrayList;
import java.util.List;

public class GetAllBooksTask extends AsyncTask<Void,Void, List<Book>> {

    private BookDataBase db;
    private BookRepository.OnGetAllBooksListener listener;

    public GetAllBooksTask(BookDataBase db, BookRepository.OnGetAllBooksListener  listener)
    {
        this.db = db;
        this.listener = listener;
    }

    @Override
    protected List<Book> doInBackground(Void... voids) {
        return db.bookDAO().getAllBooks();
//        return null;
    }

    @Override
    protected void onPostExecute(List<Book> books)
    {
        super.onPostExecute(books);
        listener.onSuccess(books);
    }

}
