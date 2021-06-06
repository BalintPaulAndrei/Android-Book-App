package com.example.tema3android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema3android.Fragments.FirstFragment;
import com.example.tema3android.R;
import com.example.tema3android.data.BookRepository;
import com.example.tema3android.models.dbEntities.Book;

import java.util.Random;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FirstFragment ff = new FirstFragment(fm);
        fm.beginTransaction().replace(R.id.frameLayout,ff).commit();

    }


}