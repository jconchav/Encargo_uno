package com.juanconcha.encargo_uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private RadioButton rdBtnHello;
    private RadioButton rdBtnBye;

    private SeekBar seek_bar;
    private TextView textViewAge;
    private Button btnThird;

    //validaciones y otros
    private String name = "";
    private int age = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;


    //para compartir
    public static final int HELLO_OPTION = 1;
    public static final int BYE_OPTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //agregar icono a la Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_formulario);

        //titulo de la Action Bar
        getSupportActionBar().setTitle("Formulario");
        //activar flecha ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recoger nombre usuario de la pantalla anterior
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            name = bundle.getString("name");
        }


        //instacias de los elementos UI
        rdBtnHello = (RadioButton)findViewById(R.id.radioHello);
        rdBtnBye = (RadioButton)findViewById(R.id.radioBye);

        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        //captura evento de la seekBar
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //aunque no lo sebreescribamos con alguna funcionalidad, OnSeekChangedListener es una Interfaz
                //y como interfaz que es, necesitamos sobreescribir todos sus metodos anque los dejemos vacíos
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //declaramos las restricciones de edad en el evento en el que el usuario suelta la seekBar
                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if(age > MAX_AGE){
                    btnThird.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is : " + MAX_AGE + "years old. ", Toast.LENGTH_LONG).show();
                }else if(age < MIN_AGE){
                    btnThird.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is : " + MIN_AGE + "years old. ", Toast.LENGTH_LONG).show();
                }else {
                    btnThird.setVisibility(View.VISIBLE);
                }


            }
        });


        textViewAge = (TextView)findViewById(R.id.textViewYears);



        btnThird = (Button)findViewById(R.id.buttonThird);

        //evento para pasar de pantalla y enviar datos
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThird = new Intent(SecondActivity.this, ThirdActivity.class);
                intentThird.putExtra("name", name);
                intentThird.putExtra("age", age);

                //si el boton de saludo esta activo, option valdra 1, si no 2
                int option = (rdBtnHello.isChecked()) ? HELLO_OPTION : BYE_OPTION;
                intentThird.putExtra("option", option);
                startActivity(intentThird);
            }
        });
    }
}
