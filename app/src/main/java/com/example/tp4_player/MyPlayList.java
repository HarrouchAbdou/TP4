package com.example.tp4_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MyPlayList extends AppCompatActivity {
    private ListView list_music_view;
    private Button arreter_music_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play_list);
        String songs[]={"music1","music2","music3"};
        list_music_view= findViewById(R.id.list_id);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,songs);
        list_music_view.setAdapter(arrayAdapter);

        list_music_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String p = parent.getItemAtPosition(pos).toString();
            }
        });

        registerForContextMenu(list_music_view);
        arreter_music_menu=(Button) findViewById(R.id.stop_music_list);
        arreter_music_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                   stop();
                }

        });

    }


    public  void stop(){
        stopService(new Intent(this,ServiceMusique.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choisir une action :");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    //Obtenir le titre de la chanson objet du long clic
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String title = list_music_view.getItemAtPosition(info.position).toString();
        switch (item.getItemId()) {
            case R.id.stop_music_list:
                // do something useful
                stopService(new Intent(this,ServiceMusique.class));
                return true;

            case R.id.play:
                // do something interesting
                Intent intent = new Intent(this,ServiceMusique.class);
                intent.putExtra("nom_music", title);
                startService(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}