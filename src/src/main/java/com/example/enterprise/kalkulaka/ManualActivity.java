/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
package com.example.enterprise.kalkulaka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


/**
 * @class <ManualActivity></ManualActivity>
 * @brief aktivita reprezentujuca uzivatelsku prirucku
 */
public class ManualActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_layout);
    }

    /**
     * @fn onCreateOptionsMenu()
     * @brief vytvorenie menu
     * @param menu ponuka Menu
     * @return vytvorena ponuka pre Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_2, menu);
        return true;
    }

    /**
     * @fn onOptionsItemSelected()
     * @brief zobrazenie menu
     * @param item polozka ponuky Menu
     * @return polozka z ponuky Menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main_page)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}