package com.example.proba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.proba2.EXTRA_TEXT";
    public static final String EXTRA_BOOL = "com.example.proba2.EXTRA_BOOL";
    public static final String EXTRA_PROGRESS = "com.example.proba2.EXTRA_PROGRESS";
    //Zaimplementowanie statycznych stringów
    SeekBar seekBar;
    TextView textView;
    Button button;
    RadioGroup Tryby;
    RadioButton radioButton;
    CharSequence Id;
    Boolean wl;
    Switch Start;
    int progress1;
    //Stworzenie zmiennych i elementów z Toolboxa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seek_bar);
        textView = findViewById(R.id.text_view2);
        button = (Button) findViewById(R.id.button1);
        Tryby = findViewById(R.id.tryby);
        Start = findViewById(R.id.switch1);
        //Przypisanie zmiennych stworzonych wyżej do elementów z activity_main

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });
        //Po nacisnięciu przycisku ma wykonać funkcję openSecondActivity()
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int minimumValue = 60;
            int progressChanged = minimumValue;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressChanged = minimumValue+ progress;
                textView.setText(progressChanged+" °C");
                progress1=progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            //Zaimplementowanie suwaka; po przesuwaniu automatycznie
            //dopisuje odpowiedną temperaturę do pola tekstowego powyżej
        });
    }

    //Odpowiednie wczytanie danych z radioButton do zapisu
    public void checkButton(View v) {
        int radioId = Tryby.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    public void openSecondActivity() {

        wl = Start.isChecked();
        //zapisanie w jakiej pozycji jest przycisk start/stop
        int radioId = Tryby.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Id = radioButton.getText();
        //zapisanie nazwy radiobutton
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_TEXT, Id);
        intent.putExtra(EXTRA_BOOL, wl);
        intent.putExtra(EXTRA_PROGRESS, progress1);
        //fukncje zapisujące dane do Extra_stringów
        startActivity(intent);
        //otwarcie nowego activity
    }
}
