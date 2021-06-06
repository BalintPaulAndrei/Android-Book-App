package com.example.tema3android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3android.R;
import com.example.tema3android.models.dbEntities.Book;
import com.example.tema3android.models.interfaces.OnItemClickListener;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.BookViewHolder> {

    ArrayList<Book> books;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(ArrayList<Book>books, OnItemClickListener oicl)
    {
        this.books = books;
        this.onItemClickListener = oicl;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_cell,parent,false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder,int position)
    {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }


    class BookViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView author;
        private TextView description;
        private View view;
        private Button deleteButton;

        public  BookViewHolder(View view)
        {
            super(view);
            title = view.findViewById(R.id.Title);
            author = view.findViewById(R.id.Author);
            description = view.findViewById(R.id.Description);
            deleteButton = view.findViewById(R.id.Button);
            this.view = view;
        }

        public void bind(Book book)
        {
            title.setText(book.title);
            author.setText(book.author);
            description.setText(book.description);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onClick(book);
                }
            });


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onClick(book,view);
                }
            });
        }

    }
}
