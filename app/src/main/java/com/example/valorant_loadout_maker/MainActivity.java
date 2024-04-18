package com.example.valorant_loadout_maker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valorant_loadout_maker.Database.LoadoutModel;
import com.example.valorant_loadout_maker.Database.ValoAdapter;
import com.example.valorant_loadout_maker.Database.ValoDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {
    private RecyclerView tasksRecyclerView;
    private ValoAdapter valoAdapter;
    private FloatingActionButton fab;
    private List<LoadoutModel> loadoutList;
    private ValoDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Starting Database
        db = new ValoDBHelper(this);
        db.openDatabase();

        // Initializing process'
        loadoutList = new ArrayList<>();
        tasksRecyclerView = findViewById(R.id.gv_loadouts);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        valoAdapter = new ValoAdapter(db, this);
        tasksRecyclerView.setAdapter(valoAdapter);
        fab = findViewById(R.id.button_add_task);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(valoAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        // Collecting loadouts from database
        loadoutList = db.getAllLoadouts();
        Collections.reverse(loadoutList);
        valoAdapter.setLoadouts(loadoutList);

        btn_go_to_agents();
        btn_go_to_weapons();
        fab.setOnClickListener(v -> AddNewLoadout.newInstance().show(getSupportFragmentManager(), AddNewLoadout.TAG));
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        loadoutList = db.getAllLoadouts();
        Collections.reverse(loadoutList);
        valoAdapter.setLoadouts(loadoutList);
        valoAdapter.notifyDataSetChanged();
    }

    private void btn_go_to_agents() {
        Button button = findViewById(R.id.btn_agents);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgentActivity.class);
            startActivity(intent);
        });
    }

    private void btn_go_to_weapons() {
        Button button = findViewById(R.id.btn_weapons);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WeaponActivity.class);
            startActivity(intent);
        });
    }
}