package com.example.enterprise.kalkulaka;

import android.content.DialogInterface;
import android.content.Intent;
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
                // set title
                alertDialogBuilder.setTitle("Generátor čísiel");
                // set custom dialog icon
                alertDialogBuilder.setView(dialogView);
                final EditText userInputMin = (EditText) dialogView.findViewById(R.id.et_inputMin);
                final EditText userInputMax = (EditText) dialogView.findViewById(R.id.et_inputMax);
                // set dialog message
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
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

                return true;
            }
        });

    }

    public void setEditAndTextView(String typeChar)
    {
        textView = (EditText) findViewById(R.id.textView);
        finalString = textView.getText().toString();

        finalString = finalString + typeChar;
        textView.setText(finalString);

        textView.setSelection(textView.getText().length());
    }

    public void deleteLastChar() {

        textView = (EditText) findViewById(R.id.textView);
        finalString = textView.getText().toString();

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
        }
    }

    public String setEditText(String character)
    {
        String returnString;
        //cislo alebo desatinna ciarka
        if( isCharacterNumber(character))
        {
            if ( editTextIfNumber.equals(""))
            {
                editTextIfNumber = character;
                returnString = character;
            }
            else
            {
                editTextIfNumber = editTextIfNumber + character;
                returnString = editTextIfNumber;
            }
        }
        //operator
        else
        {
            returnString = character;
            editTextIfNumber = "";
        }


        return returnString;
    }

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

    public Boolean isCharacterOperator(String isOper)
    {

        if ( isOper.equals("+") || isOper.equals("-") || isOper.equals("x") ||isOper.equals("/") ||isOper.equals("√") ||isOper.equals("^") ||isOper.equals("("))
        {
            return true;
        }
        else
            return false;

    }

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

    public void decBinHex()
    {
        Button btn = (Button) findViewById(R.id.buttonSustava);
        String btnText = btn.getText().toString();
        textView = (EditText) findViewById(R.id.textView);
        editText = (TextView) findViewById(R.id.editText);

        if ( btnText.equals("Dec"))
        {
            // z desatinnej do binarnej
            if(!editTextIfNumber.equals(""))
            {
                try
                {
                    editTextIfNumber = Integer.toString(Integer.valueOf(editTextIfNumber),2);
                    finalString = "";
                    textView.setText("");
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
        else if ( btnText.equals("Bin"))
        {
            // z binarnej do hexa
            if(!editTextIfNumber.equals(""))
            {
                try {
                    Integer binToHex = Integer.parseInt(editTextIfNumber,2);
                    editTextIfNumber = Integer.toString(binToHex,16);
                    finalString = "";
                    textView.setText("");
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
            //z hexa do binarnej
            if(!editTextIfNumber.equals(""))
            {
                try
                {
                    Integer hexaToDec = Integer.parseInt(editTextIfNumber,16);
                    editTextIfNumber = hexaToDec.toString();
                    finalString = "";
                    textView.setText("");
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
    }

    public static int randInt(int min, int max)
    {
        Random generator = new Random();
        int i = generator.nextInt(max -1) + min;
        return i;
    }

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

    public void sendToCalculate()
    {
        String result = calculate.Calculate(finalString);
        TextView resultView = (TextView) findViewById(R.id.editText);
        resultView.setText(result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(MainActivity.this, ManualActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}
