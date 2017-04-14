package com.example.enterprise.kalkulaka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfilingActivity extends AppCompatActivity
{

    private StandardDeviations dev = new StandardDeviations();
    List<Double> list = new ArrayList<>();
    String text = "";
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiling_activity_layout);
        try
        {
            InputStreamReader reader = new InputStreamReader(getAssets().open("test.txt"));
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if(character == ','){
                    list.add(Double.parseDouble(text));
                    text = "";
                    continue;
                }
                text += character;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Chyba pri načítaní súboru test.txt",Toast.LENGTH_LONG).show();
        }

        Iterator<Double> It = list.iterator();
        while(It.hasNext()){
            Double st = It.next();
            System.out.println("character : " + st.toString());
        }


    }

    //zobrazi demo výsledok aký mu pošleme
    public void showDemo(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonDemo:
                //sem dať ukážku profilingu
                result = "Sem dať Výsledok";
                break;
            case R.id.buttonProfiling:
                result =  Double.toString(dev.comp(list));
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilingActivity.this);
        builder.setTitle("Výsledok smerodatnej odchylky").setMessage(result)
                .setCancelable(false)
                .setPositiveButton("Zavrieť", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id)
                    {

                    }
                });

            AlertDialog alert = builder.create();
            alert.show();
    }



    //vymaže text
    public void deleteEditText(View v)
    {
        EditText editText = (EditText) findViewById(R.id.editTextProfiling);
        editText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_3, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main_page)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}