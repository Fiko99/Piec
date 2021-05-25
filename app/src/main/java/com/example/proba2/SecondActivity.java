package com.example.proba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView praca, wentylatory, wegiel, moc, energia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        praca = findViewById(R.id.praca);
        wentylatory = findViewById(R.id.wenty);
        wegiel = findViewById(R.id.wegiel);
        moc = findViewById(R.id.moc);
        energia = findViewById(R.id.ener);
        int a, predkosc;
        //implementacja Toolboxa oraz zmiennych pomocniczych
        Intent intent = getIntent();
        final String Id1 = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        //wyczytanie zmiennej z EXTRA
        String s1 = getResources().getString(R.string.wyda);
        String s2 = getResources().getString(R.string.norm);
        //String s3 = getResources().getString(R.string.oszcz);
        //wczytanie trybów z strings.xml

        Boolean wl1 = intent.getBooleanExtra(MainActivity.EXTRA_BOOL, true);
        int progress1 = intent.getIntExtra(MainActivity.EXTRA_PROGRESS,0);
        //wyczytanie zmiennej z EXTRA

        if ((a = Id1.compareTo(s1)) == 0) {predkosc=1000;}
        else if ((a = Id1.compareTo(s2)) == 0) {predkosc=500;}
        else {predkosc=250;}
        //funkcja ustawiająca prędkość wiatraka w zależności od trybu

        int kilogramy = ObliczIloscWegla(progress1);
        //obliczenie ile potrzeba węgla dla odpowiedniej temperatury

        int MJ = kilogramy * 30;
        //obliczenie mocy zgromaczonej w piecu

        int energia = (predkosc*40)/1000;
        //obliczenie energii zużywanej z wiatraków (energa zużwana przez mikrokontroler
        // oraz podajnik jest pomijalnie mała względem wentylatorów)

        if(wl1) {
            praca.setText(Id1);
            wentylatory.setText( predkosc + " obr/min");
            wegiel.setText(kilogramy + " kg/h");
            moc.setText(MJ + " W");
            this.energia.setText(energia + " Wh");
        }
        else
        {
            praca.setText(getResources().getString(R.string.koniec));
        }
        //warunek sprawdzający czy silnik jest włączony czy nie, jeżeli tak to wyświetla odpowienie parametry
    }
    int ObliczIloscWegla(int x)
    {
        if (x == 0 ){x = 20; return x;}
        double a = x;
        a=a*0.25;
        x = (int) a;
        return 20+x;
    }
    //funkcja obliczająca ilość węgla na godzine
}