package com.example.tema3android.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema3android.R;
import com.example.tema3android.adapters.MyAdapter;
import com.example.tema3android.data.BookRepository;
import com.example.tema3android.models.ApplicationController;
import com.example.tema3android.models.dbEntities.Book;
import com.example.tema3android.models.interfaces.OnItemClickListener;

import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentManager ft;

    private Button addButton;
    private EditText title;
    private EditText author;
    private EditText description;

    private View view;

    private BookRepository bookRepository = new BookRepository();

    private RecyclerView recyclerView;

    private ArrayList<Book> allBooks = new ArrayList<Book>();

    MyAdapter adapter = new MyAdapter(allBooks, new OnItemClickListener() {
        @Override
        public void onClick(Book book) {
            //to delete logic
            bookRepository.deleteBook(book, new BookRepository.OnSuccesListener() {
                @Override
                public void onSuccess() {
                    allBooks.remove(book);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(Book book, View view) {
           // FirstFragment ff = new FirstFragment();
          //  int commit = ApplicationController.getInstance().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,ff).commit();

            ft.beginTransaction().replace(R.id.frameLayout,new SecondFragment(book)).addToBackStack("2ndFragment").commit();
        }
    });


    public FirstFragment()
    {

    }

    public FirstFragment(FragmentManager ft) {
        this.ft = ft;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_first, container, false);
        setupViews();
        return view;
    }
    private void setupViews()
    {
        addButton = view.findViewById(R.id.addUpdate);
        title = view.findViewById(R.id.title_edit);
        author = view.findViewById(R.id.author_edit);
        description = view.findViewById(R.id.description_edit);
        recyclerView = view.findViewById(R.id.data_recycler);
        addButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // Toast.makeText(ApplicationController.getInstance(),"Hello",Toast.LENGTH_SHORT).show();
                insertBook();
            }
        });
        GetAllBooks();
        adapter.notifyDataSetChanged();
        setUpRecyclerView();
    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationController.getInstance());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void GetAllBooks()
    {
        bookRepository.getAllBooks(new BookRepository.OnGetAllBooksListener() {
            @Override
            public void onSuccess(List<Book> books) {
                for(Book book : books){
                    allBooks.add(book);
                }
            }
        });
    }

    private void insertBook()
    {
        String TITLE = title.getText().toString();
        String AUTHOR = author.getText().toString();
        String DESCRIPTION = description.getText().toString();
        if(TITLE.isEmpty() || AUTHOR.isEmpty() || DESCRIPTION.isEmpty())
            Toast.makeText(ApplicationController.getInstance(), "Title, Author and description cannot be empty!",Toast.LENGTH_SHORT).show();
        else
        {
            boolean toUpdate = false;
            for(Book book : allBooks)
            {
                if(book.title.equals(TITLE) && book.author.equals(AUTHOR))
                {
                    toUpdate = true;
                    allBooks.remove(book);
                    break;
                }
            }
            if(toUpdate)
            {
                Book updatedBook = new Book(TITLE, AUTHOR, DESCRIPTION);
                bookRepository.updateBook(updatedBook, new BookRepository.OnSuccesListener() {
                    @Override
                    public void onSuccess() {
                        allBooks.add(updatedBook);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
            else {
                Book toInsert = new Book(TITLE, AUTHOR, DESCRIPTION);
                bookRepository.insertBook(toInsert,
                        new BookRepository.OnInsertBookListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(ApplicationController.getInstance(), "Succesful insert", Toast.LENGTH_SHORT).show();
                                allBooks.add(toInsert);
                                adapter.notifyDataSetChanged();
                            }
                        }
                );
            }

            title.setText("");
            title.clearFocus();

            author.setText("");
            author.clearFocus();

            description.setText("");
            description.clearFocus();
        }
    }
}