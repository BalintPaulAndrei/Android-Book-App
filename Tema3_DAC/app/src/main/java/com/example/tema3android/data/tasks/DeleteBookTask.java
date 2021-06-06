package com.example.tema3android.data.tasks;

import android.os.AsyncTask;

import com.example.tema3android.data.BookDataBase;
import com.example.tema3android.data.BookRepository;
import com.example.tema3android.models.dbEntities.Book;

public class DeleteBookTask extends AsyncTask<Book,Void,Void> {

    private BookDataBase db;
    private BookRepository.OnSuccesListener listener;

    public DeleteBookTask(BookDataBase db, BookRepository.OnSuccesListener listener)
    {
        this.db = db;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Book... books) {
        db.bookDAO().delete(books[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }



}
