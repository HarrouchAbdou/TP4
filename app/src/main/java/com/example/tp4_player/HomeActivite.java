package com.example.tp4_player;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivite extends AppCompatActivity implements View.OnClickListener{
    private Button demarer_music,arreter_music,quitter_app,list_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demarer_music=(Button) findViewById(R.id.Demarer);
        arreter_music=(Button) findViewById(R.id.Arreter);
        list_music=(Button) findViewById(R.id.Liste);
        quitter_app=(Button) findViewById(R.id.Quitter);

        demarer_music.setOnClickListener(this);
        arreter_music.setOnClickListener(this);
        list_music.setOnClickListener(this);
        quitter_app.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==demarer_music){
            Intent intent = new Intent(this,ServiceMusique.class);
            intent.putExtra("nom_music", "music1");
            startService(intent);
        }else if(view==arreter_music) {
            stopService(new Intent(this,ServiceMusique.class));
        }
        else if(view==quitter_app) {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Confirmer");
            alert.setMessage("Voulez-vous surement quitter ?");
            alert.setCancelable(false);
            alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(HomeActivite.this,"Vous avez refuse de quitter",Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alertDialog=alert.create();
            alertDialog.show();

        }else if(view==list_music) {
            Intent intent = new Intent(this, MyPlayList.class);
            startActivity(intent);
        }


    }
}