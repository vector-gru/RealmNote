package com.example.realmnote;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addNoteBtn = findViewById(R.id.button);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        //we initialize te realm first
        Realm.init(getApplicationContext());
        //we get on object of realm
        Realm realm = Realm.getDefaultInstance();

        // we get the results
        RealmResults<Note> noteList = realm.where(Note.class).findAll().sort("createdTime", Sort.DESCENDING);

        // we initialize our recycler view ui
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //we set it's layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),noteList);
        recyclerView.setAdapter(myAdapter);

        //we add a change listener to new notes on our list
        noteList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                // this will notify the adapter that it will refresh the view of
                myAdapter.notifyDataSetChanged();
            }
        });

    }



}