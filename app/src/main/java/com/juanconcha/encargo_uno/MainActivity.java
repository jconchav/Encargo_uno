package com.juanconcha.encargo_uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser;
    private Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //agregar icono a la Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_formulario);

        //titulo de la Action Bar
        getSupportActionBar().setTitle("Formulario");


        editTextUser = (EditText)findViewById(R.id.editTextNameUser);

        btnSecond = (Button)findViewById(R.id.buttonSecond);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextUser.getText().toString();

                if(name != null && !name.isEmpty()){

                    Intent intentSecond = new Intent(MainActivity.this, SecondActivity.class);
                    intentSecond.putExtra("name",name);
                    startActivity(intentSecond);
                }else{
                    Toast.makeText(MainActivity.this, "The name is madatory, please type it", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
