package com.example.emergencyapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    String _edittext, amot, travel;
    String NameNAME;
    private RecyclerView recyclerView;
    ImageView menubtn;
    int onCheck;
    private List<Friends> friendsList = new ArrayList<>();
    private Context context;
    private FriendsAdapter friendsAdapter;
    private Friends friends;
    TextInputLayout search_edit;
    ImageView searchImg;
    SearchView searchView;
    String name;
    private SharedPreferences sharedPreferences;
    private ActionBar toolbar;
    BottomNavigationView bNv;
    private static final String PEREF_NAME = "preffile";
    // Toolbar toolbar;
    //Toolbar toolbar00;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        // clientAdapter = new ClientAdapter(context, clientList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(friendsAdapter);
        bNv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        searchView = (SearchView) findViewById(R.id.searchView);
        CharSequence query = searchView.getQuery();
        //search_edit = (TextInputLayout)findViewById(R.id.name);
        //searchImg = (ImageView)findViewById(R.id.search);
        //toolbar00 = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        //setSupportActionBar(toolbar00);

        toolbar = getSupportActionBar();

        getTasks();

        sharedPreferences = getSharedPreferences(PEREF_NAME, MODE_PRIVATE);
        NameNAME = sharedPreferences.getString("value", " ");

        bNv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //toolbar.setTitle("EmergencyApp");
        // toolbar.setIcon(@);
      /*  searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = searchView.getQuery().toString();
                if (NameNAME.equals(query)){
                    Toast.makeText(getApplicationContext(), friends.getName_name() + friends.getPhone_phone() + friends.getMail_mail(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RecyclerViewActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (NameNAME.equals(query)) {

                    friends.getName_name();
                    //.getFilter().filter(query);
                    Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RecyclerViewActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Friends>> {
            @Override
            protected List<Friends> doInBackground(Void... voids) {
                List<Friends> friendsList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDataBase()
                        .Dao()
                        .getAll();
                return friendsList;
            }

            @Override
            protected void onPostExecute(List<Friends> friends) {
                super.onPostExecute(friends);
                FriendsAdapter adapter = new FriendsAdapter(RecyclerViewActivity.this, friends);
                recyclerView.setAdapter(adapter);
            }

        }
        GetTasks gt = new GetTasks();
        gt.execute();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(RecyclerViewActivity.this, HomePage.class);
                    startActivity(intent);
                    return true;
                case R.id.add:
                    Intent intent3 = new Intent(RecyclerViewActivity.this, Welcome_AddActivity.class);
                    startActivity(intent3);
                    return true;
                case R.id.setting:
                    //toolbar.setTitle("Settings");
                    // test.setText("Settings");
                    Toast.makeText(getApplicationContext(), "Person", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


    private void User() {
        class UserTasks extends AsyncTask<Void, Void, Friends> {
            @Override
            protected Friends doInBackground(Void... voids) {
                Friends friends = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDataBase()
                        .Dao()
                        .findNewName(NameNAME);

                return friends;
            }

            @Override
            protected void onPostExecute(Friends friends) {
                super.onPostExecute(friends);
                finish();
                if (friends != null) {
                   // Intent intent = new Intent(LoginActivity.this, HomePage.class);
                    //startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }
}

                // Toast.makeText(getApplicationContext(), friends.getMyName() + friends.getMyPass(), Toast.LENGTH_SHORT).show();
        /*    }

        }
        UserTasks ut = new UserTasks();
        ut.execute();
    }
}*/




   /* private void Menu() {
        PopupMenu p = new PopupMenu(HomePage.this, menubtn);
        p.getMenuInflater().inflate(R.menu.menu3, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        Intent intent = new Intent(HomePage.this, AddFriends.class);
                        startActivity(intent);
                        return true;
                    case R.id.view:
                        Intent intent3 = new Intent(HomePage.this, RecyclerViewActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.about:
                        // Intent intent4 = new Intent(MainActivity2.this, AboutPolicy.class);
                        // startActivity(intent4);
                        return true;
                    default:
                        return true;
                }
            }
        });
        p.show();
    }*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu5, menu);
        MenuItem mSearch = menu.findItem(R.id.appSearchBar);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/



