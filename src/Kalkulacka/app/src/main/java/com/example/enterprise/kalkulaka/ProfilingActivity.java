/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
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

/**
 * @class <profilingActivity></profilingActivity>
 * @brief aktivita reprezentujuca vypocet smerodatnej odchylky
 */
public class ProfilingActivity extends AppCompatActivity
{

    private StandardDeviations dev;
    List<Double> list;
    String text = "";
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiling_activity_layout);
    }

    /**
     * @fn readFromTxt()
     * @brief nacitanie suboru a ulozenie do zoznamu
     * @param txt text
     */
    public void readFromTxt(String txt)
    {
        try
        {
            InputStreamReader reader = new InputStreamReader(getAssets().open(txt));
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

    /**
     * @fn readEditText()
     * @brief nacita vstup a ulozi do listu
     * @return
     */
    public Boolean readEditText()
    {
        EditText editText = (EditText) findViewById(R.id.editTextProfiling);
        String text = editText.getText().toString() + "$";
        String tmp = "";
        System.out.println("-----> " + text.length());
        Boolean ret = true;

        for (int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);

            if(c == ',' || c == '$')
            {
                try
                {
                    list.add(Double.parseDouble(tmp));
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Chyba pri načítaní vstupu. @nProsím, skontrolujte si zadaný vstup",Toast.LENGTH_LONG).show();
                    ret = false;
                    break;
                }
                tmp = "";
            }
            else
            {
                tmp += c;
            }
        }

        Iterator<Double> It = list.iterator();
        while(It.hasNext())
        {
            Double st = It.next();
            System.out.println("character : " + st.toString());
        }

        return ret;
    }

    /**
     * @fn showDemo()
     * @brief zobrazenie demo-vysledku aky mu je zaslany
     * @param v parameter View
     */
    public void showDemo(View v)
    {
        result = "";
        text = "";
        dev = new StandardDeviations();
        list = new ArrayList<>();

        switch (v.getId())
        {
            case R.id.buttonDemo1:
                readFromTxt("test.txt");
                result =  Double.toString(dev.comp(list));
                showAlertDialog(result, "\n10 hodnôt");
                break;
            case R.id.buttonDemo2:
                readFromTxt("test2.txt");
                result =  Double.toString(dev.comp(list));
                showAlertDialog(result, "\n100 hodnôt");
                break;
            case R.id.buttonDemo3:
                readFromTxt("test3.txt");
                result =  Double.toString(dev.comp(list));
                showAlertDialog(result, "\n1000 hodnôt");
                break;
            case R.id.buttonProfiling:
                if ( readEditText())
                {
                    result = Double.toString(dev.comp(list));
                    showAlertDialog(result, "");
                }
                break;
        }
    }

    /**
     * @fn showAlertDialog()
     * @brief zobrazi upozornenie dialogu
     * @param result vysledok
     * @param count pocet
     */
    public void showAlertDialog(String result, String count)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilingActivity.this);
        builder.setTitle("Výsledok smerodatnej odchylky" + count).setMessage(result)
                .setCancelable(true)
                .setPositiveButton("Zavrieť", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {}
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * @fn deleteEditText()
     * @brief vymaze text
     * @param v parameter View
     */
    public void deleteEditText(View v)
    {
        EditText editText = (EditText) findViewById(R.id.editTextProfiling);
        editText.setText("");
    }

    /**
     * @fn onCreateOptionsMenu()
     * @brief vytvori menu
     * @param menu zobrazenie ponuky Menu
     * @return zobrazenie ponuky
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_3, menu);
        return true;
    }

    /**
     * @fn onOptionsItemSelected()
     * @brief spustenie menu
     * @param item polozka menu
     * @return zobrazenie polozky
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main_page)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}