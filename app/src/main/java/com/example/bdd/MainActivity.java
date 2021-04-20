package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "preferences_file";
    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapter mAdapter;
    List<Planete> planetes;
    PlaneteDao planeteDao;

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        CoordinatorLayout mcoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        tv = findViewById(R.id.tv);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").allowMainThreadQueries().build();

        planeteDao = db.planeteDao();

        loadData(planeteDao);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterPlaneteFragment fragment = new AjouterPlaneteFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.conteneur_fragment,fragment).commit();

            }
        });
    }

    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                planetes = planeteDao.getAll();

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        //tv.setText("Il y a [" + planetes.size() + "] Planètes dans la base de données" );
                        for (int i =0; i< planetes.size();i++) {
                            Toast.makeText(MainActivity.this, "Planete = " + planetes.get(i).getNom(), Toast.LENGTH_SHORT).show();
                            mAdapter = new MonRecyclerViewAdapter((ArrayList<Planete>) planetes);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });

            }
        }).start();

    }

    private void initData(PlaneteDao planeteDao) {

        ArrayList<Planete> planetes = new ArrayList<>();

        planetes.add(new Planete("Mercure",4900));
        planetes.add(new Planete("Venus",12000));
        planetes.add(new Planete("Terre",12800));
        planetes.add(new Planete("Mars",6800));
        planetes.add(new Planete("Jupiter",144000));
        planetes.add(new Planete("Saturne",120000));
        planetes.add(new Planete("Uranus",52000));
        planetes.add(new Planete("Neptune",50000));
        planetes.add(new Planete("Pluton",2300));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }

    private List<Planete> getDataSource() {
        return planetes;
    }

    public void insert(Planete planete) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        planeteDao.insert(planete);
                    }
                });

            }
        }).start();
    }

    public void restart(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}