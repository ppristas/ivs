/**
 * Tato aplikacia je rozsirenou verziou kalkulacky do druheho projektu IVS.
 * Zahrna klasicke pocty a taktiez vypocet smerodatnej odchylky.
 *
 * @author Peter Miklanek, Juraj Medvec, Peter Pristas
 * @version 1.0
 * @since 15.4.2017
 */

/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
package com.example.enterprise.kalkulaka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;

/**@class <MainActivity></MainActivity>
 * @brief Hlavna aktivita aplikacie zobrazujuca UI kalulacky
 */
public class MainActivity extends AppCompatActivity
{

    public EditText textView;
    public TextView editText;
    public String finalString;
    public String editTextIfNumber;
    public Integer min;
    public Integer max;
    private EditText etOutputMin;
    private EditText etOutputMax;
    private Calculate calculate;
    public Integer leftBracket;
    public Integer rightBracket;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        finalString = "";
        editTextIfNumber = "";
        etOutputMin = (EditText) findViewById(R.id.et_inputMin);
        etOutputMax = (EditText) findViewById(R.id.et_inputMax);
        calculate = new Calculate();
        leftBracket = 0;
        rightBracket = 0;

        min = 1;
        max = 999999;

        ImageButton generate = (ImageButton) findViewById(R.id.buttonGenerator);
        generate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View dialogView = li.inflate(R.layout.custom_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                /** @param alertDialogBuilder.setTitle nastavuje titulok "Generator cisel"*/
                alertDialogBuilder.setTitle("Generátor čísiel");
                /** @param alertDialogBuilder.setView nastavi upravenu ikonu pre dialog*/
                alertDialogBuilder.setView(dialogView);
                final EditText userInputMin = (EditText) dialogView.findViewById(R.id.et_inputMin);
                final EditText userInputMax = (EditText) dialogView.findViewById(R.id.et_inputMax);
                /** @param alertDialogBuilder.setCancelable nastavi spravu dialogu*/
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        String minStr = userInputMin.getText().toString();
                                        String maxStr = userInputMax.getText().toString();

                                        if( !minStr.equals("") || !maxStr.equals(""))
                                        {
                                            try{
                                                max = Integer.valueOf(maxStr);
                                                min = Integer.valueOf(minStr);

                                            }catch (Exception e){}
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        dialog.cancel();
                                    }
                                });
                /** @param alertDialog.create() vytvori upozornenie pre dialog*/
                AlertDialog alertDialog = alertDialogBuilder.create();
                /** @param alertDialog.show() zobrazi upozornenie */
                alertDialog.show();

                return true;
            }
        });

    }

    /** @fn setEditAndTextView(String typeChar)
     *  @brief nastavenie textu na zobrazenie
     *  @param typeChar retazec znakov
     */
    public void setEditAndTextView(String typeChar)
    {
        textView = (EditText) findViewById(R.id.textView);
        editText = (TextView) findViewById(R.id.editText);
        finalString = textView.getText().toString();
        Button btn = (Button) findViewById(R.id.buttonSustava);

        if( !editText.getText().toString().equals("") && btn.getText().toString().equals("Dec"))
        {
            finalString = editText.getText().toString();
            editText.setText("");
        }
        else
        {
            editText.setText("");
        }

        finalString = finalString + typeChar;
        textView.setText(finalString);

        textView.setSelection(textView.getText().length());
    }

    /** @fn deleteLastChar()
     *  @brief vymazanie posledneho znaku textu
     */
    public void deleteLastChar()
    {
        textView = (EditText) findViewById(R.id.textView);
        textView.setSelection(textView.getText().length());
        editText = (TextView) findViewById(R.id.editText);
        finalString = textView.getText().toString();
        Button btn = (Button) findViewById(R.id.buttonSustava);

        if ( !editText.getText().toString().equals(""))
        {
            editText.setText("");
            textView.setText("");
            btn.setText("Dec");
        }
        else {

            if (finalString.length() != 0) {
                Character c = finalString.charAt(finalString.length() - 1);

                String bracket = String.valueOf(c);

                if (bracket.equals("(")) {
                    leftBracket--;
                }

                if (bracket.equals(")")) {
                    rightBracket--;
                }

                finalString = finalString.substring(0, finalString.length() - 1);
                textView.setText(finalString);
                textView.setSelection(textView.getText().length());
            }
        }
    }

    /**
     * @fn leftOrRightBracket()
     * @brief funkcia pre zistenie, ktora zatvorka ma nasledovat
     * @return prava/lava zatvorka
     */
    public String leftOrRightBracket()
    {
        String bracket = "";

        if ( finalString.equals(""))
        {
            bracket = "(";
        }
        else if ( !finalString.equals(""))
        {
            StringBuilder sb = new StringBuilder(finalString);
            String sub;
            if ( sb.length() == 1)
            {
                sub = sb.substring(0, sb.length());

                if ( isCharacterOperator(sub))
                {
                    bracket = "(";
                }
                else
                {
                    bracket = ")";
                }
            }
            else
            {
                sub = sb.substring(sb.length() - 1, sb.length());

                if ( isCharacterOperator(sub))
                {
                    bracket = "(";
                }
                else
                {
                    bracket = ")";
                }
            }
        }

        if (bracket.equals("("))
        {
            leftBracket++;
        }
        else
            rightBracket++;

        if( bracket.equals(")"))
        {
            if( rightBracket > leftBracket)
            {
                bracket = "";
                rightBracket--;
            }
        }

        return bracket;
    }

    //zistenie znaku ci je operator

    /**
     * @fn isCharacterOperator()
     * @brief zistenie znaku ci je operator
     * @param isOper dany znak
     * @return vrati bool hodnotu ci je dany znak operator
     */
    public Boolean isCharacterOperator(String isOper)
    {

        if ( isOper.equals("+") || isOper.equals("-") || isOper.equals("x") ||isOper.equals("/") ||isOper.equals("√") ||isOper.equals("^") ||isOper.equals("("))
        {
            return true;
        }
        else
            return false;

    }

    /**
     * @fn isCharacterNumber
     * @brief zistenie znaku ci je cislo
     * @param isOper dany znak
     * @return vrati bool hodnotu ci je dany znak cislo
     */
    public Boolean isCharacterNumber(String isOper)
    {

        if ( isOper.equals("0") || isOper.equals("1") || isOper.equals("2") ||isOper.equals("3") ||isOper.equals("4") ||isOper.equals("5") ||isOper.equals("6")
                || isOper.equals("7") || isOper.equals("8") ||isOper.equals("9") ||isOper.equals(".") )
        {
            return true;
        }
        else
            return false;

    }

    /**
     * @fn decBinHex()
     * @brief prevod do decimalnej, binarnej a sestnastkovej sustavy
     */
    public void decBinHex()
    {
        Boolean gen = true;
        Button btn = (Button) findViewById(R.id.buttonSustava);
        String btnText = btn.getText().toString();
        textView = (EditText) findViewById(R.id.textView);
        editText = (TextView) findViewById(R.id.editText);

        editTextIfNumber = editText.getText().toString();

        try
        {
            if( !btn.getText().toString().equals("Hex"))
            {
                Double a = Double.valueOf(editTextIfNumber);
                Integer b = a.intValue();
                editTextIfNumber = b.toString();
            }
        }
        catch (Exception e)
        {
            gen = false;
        }

        if( gen == true)
        {
            /** @fn btnText.equals("Dec"))
             *  @brief prevod z desiatkovej do binarnej sustavy
             */
            if ( btnText.equals("Dec"))
            {
                if(!editTextIfNumber.equals(""))
                {
                    try
                    {
                        editTextIfNumber = Integer.toString(Integer.valueOf(editTextIfNumber),2);
                        editText.setText(editTextIfNumber);
                        btn.setText("Bin");
                    }
                    catch ( Exception e)
                    {
                        btn.setText("Bin");
                    }
                }
                else
                    btn.setText("Bin");
            }
            /**
             * @fn btnText.equals("Bin"))
             * @brief prevod z binarnej do sestnastkovej sustavy
             */
            else if ( btnText.equals("Bin"))
            {
                if(!editTextIfNumber.equals(""))
                {
                    try {
                        Integer binToHex = Integer.parseInt(editTextIfNumber,2);
                        editTextIfNumber = Integer.toString(binToHex,16);
                        editText.setText(editTextIfNumber);
                        btn.setText("Hex");
                    }
                    catch ( Exception e)
                    {
                        btn.setText("Hex");

                    }
                }
                else
                    btn.setText("Hex");

            }
            else
            {
                /**
                 * @brief prevod zo sestnastkovej do binarnej sustavy
                 */
                if(!editTextIfNumber.equals(""))
                {

                    try
                    {
                        Long hexaToDec = Long.parseLong(editTextIfNumber, 16);
                        editTextIfNumber = hexaToDec.toString();
                        editText.setText(editTextIfNumber);
                        btn.setText("Dec");

                    }
                    catch ( Exception e)
                    {
                        btn.setText("Dec");

                    }
                }
                else
                    btn.setText("Dec");
            }

        }

    }

    /**
     * @fn generator()
     * @brief Generovanie cisla
     * @param gen generovane cislo
     */
    public void generator(Integer gen)
    {
        textView = (EditText) findViewById(R.id.textView);

        if ( isCharacterNumber(""))
        {
            finalString = finalString + gen.toString();
            textView.setText(finalString);
        }
        else
        {

            finalString = finalString + gen.toString();
            textView.setText(finalString);
        }
        textView.setSelection(textView.getText().length());

    }

    /**
     * @fn randInt()
     * @brief pomocna funkcia pre generovanie cisla
     * @param min minimum
     * @param max maximum
     * @return vratene vygenerovane cislo
     */
    public static int randInt(int min, int max)
    {
        Random generator = new Random();
        int i = generator.nextInt(max -1) + min;
        return i;
    }

    /**
     * @fn ButtonOnClick
     * @brief funkcia reprezentujuca konecny automat pre rozhodnutie stlaceneho tlacidla
     * @param v View parameter
     */
    public void ButtonOnClick(View v) {
        switch (v.getId()) {
            case R.id.number0:
                setEditAndTextView("0");break;
            case R.id.number1:
                setEditAndTextView("1");break;
            case R.id.number2:
                setEditAndTextView("2");break;
            case R.id.number3:
                setEditAndTextView("3");break;
            case R.id.number4:
                setEditAndTextView("4");break;
            case R.id.number5:
                setEditAndTextView("5");break;
            case R.id.number6:
                setEditAndTextView("6");break;
            case R.id.number7:
                setEditAndTextView("7");break;
            case R.id.number8:
                setEditAndTextView("8");break;
            case R.id.number9:
                setEditAndTextView("9");break;
            case R.id.buttonPlus:
                setEditAndTextView("+");break;
            case R.id.buttonMinus:
                setEditAndTextView("-");break;
            case R.id.buttonMultiple:
                setEditAndTextView("x");break;
            case R.id.buttonDevide:
                setEditAndTextView("/");break;
            case R.id.buttonEqual:
                sendToCalculate();break;
            case R.id.buttonPoint:
                setEditAndTextView(".");break;
            case R.id.buttonPercentage:
                String bracket = leftOrRightBracket();
                setEditAndTextView(bracket);break;
            case R.id.buttonOdmocnina:
                setEditAndTextView("√");break;
            case R.id.buttonMocnina:
                setEditAndTextView("^");break;
            case R.id.buttonSustava:
                decBinHex(); break;
            case R.id.buttonFaktorial:
                setEditAndTextView("!");break;
            case R.id.buttonGenerator:
                generator(randInt( min, max));break;
            case R.id.buttonDelete:
                deleteLastChar();break;
        }
    }

    /**
     * @fn sendToCalculate()
     * @brief funkcia pre vypocet prikladu
     */
    public void sendToCalculate()
    {
        String result = calculate.Calculate(finalString);
        TextView resultView = (TextView) findViewById(R.id.editText);
        resultView.setTextColor(Color.parseColor("#3cbd5e"));
        resultView.setText(result);
    }

    /**
     * @fn onCreateOptionsMenu()
     * @brief vytvorenie Menu, vlozi polozky do listy akcii
     * @param menu ponuka Menu
     * @return vytvori menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.to_do, menu);
        return true;
    }

    /**
     * @fn onOptionsItemSelected()
     * @brief Zobrazenie menu
     * @param item polozka ktora ma byt zobrazena
     * @return polozka na zobrazenie
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(MainActivity.this, ManualActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_profiling)
        {
            Intent intent = new Intent(MainActivity.this, ProfilingActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}
