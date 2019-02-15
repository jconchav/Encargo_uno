package com.juanconcha.encargo_uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private Button btnSharing;
    private ImageButton btnConfirm;

    //otros valores
    private String name;
    private int age;
    private int typeOfMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //agregar icono a la Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_formulario);

        //titulo de la Action Bar
        getSupportActionBar().setTitle("Formulario");
        //activar flecha ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recoger el nombre del activuty anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            age = bundle.getInt("age");
            typeOfMessage = bundle.getInt("option");
        }

        //instancias de la UI
        btnSharing = (Button) findViewById(R.id.buttonSharing);
        btnConfirm = (ImageButton) findViewById(R.id.btnConfirm);


        //evento del boton confirmar

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThirdActivity.this, createMessage(name, age, typeOfMessage), Toast.LENGTH_LONG).show();
                btnSharing.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.INVISIBLE);
            }
        });


        //evento boton compartir
        btnSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, createMessage(name, age, typeOfMessage));
                startActivity(intent);
            }
        });

    }
        private String createMessage(String name, int age, int typeOfMessage){
            if(typeOfMessage == SecondActivity.HELLO_OPTION){
            return "Hola " + name + ", Cómo llevas esos " + age + "años? #Formulario";
        }else{
                return "Espero verte pronto " + name + ", antes que cumplas " + (age + 1) + " .. #Formulario";
    }


    }
}
