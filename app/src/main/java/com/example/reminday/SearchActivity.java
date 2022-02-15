package com.example.reminday;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

public class SearchActivity extends AppCompatActivity {
    //Initialize variable
    ListView listView;
    String[] name = {"Amir, 21.9.1999","Farah Hani, 24.9.1998","Najihatul Izzah, 25.6.1999","Firash Arshad, 4.5.1997","Aiman Hakim, 8.9.1999","Danish Haiqal, 7.6.1997","Anis Haziqah, 5.6.1999", "Iffah Farhana, 4.4.1999", "Hanis Natasya, 3.3.1999", "Farah Nabilah, 3.4.1999", "Aina Aisyah, 6.7.1997"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Assign variable
        listView = findViewById(R.id.list_view);

        arrayAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //display click item position in toast
                Toast.makeText(getApplicationContext(),arrayAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Initialize menu inflater
        MenuInflater menuInflater = getMenuInflater();
        //Inflate menu
        menuInflater.inflate(R.menu.menu_search,menu);
        //Initialize menu item
        MenuItem menuItem = menu.findItem(R.id.search_view);
        //Initialize search
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter array list
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}