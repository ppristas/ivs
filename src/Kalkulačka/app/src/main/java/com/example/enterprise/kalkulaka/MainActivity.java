package com.example.enterprise.kalkulaka;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button Plus;
    public Button Minus;
    public Button Divide;
    public Button Multiple;
    public Button Equals;
    public Button Percentage;

    public Button Mocnina;
    public Button Odmocnina;
    public Button Sustava;
    public Button Factorial;

    public Double First; // uchovanie prvej hodnoty zadanej tlacitkom
    public Double Last; // uchovanie druhej hodnoty / vysledku

    public TextView textView;
    public EditText editText;

    public StringBuilder stringBuilder = new StringBuilder();
    public StringBuilder deleteStringBuilder = new StringBuilder();
    public String finalString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        /*Button Zero = (Button) findViewById(R.id.number0);
        Button One = (Button) findViewById(R.id.number1);
        Button Two = (Button) findViewById(R.id.number2);
        Button Three = (Button) findViewById(R.id.number3);
        Button Four = (Button) findViewById(R.id.number4);
        Button Five = (Button) findViewById(R.id.number5);
        Button Six = (Button) findViewById(R.id.number6);
        Button Seven = (Button) findViewById(R.id.number7);
        Button Eight = (Button) findViewById(R.id.number8);
        Button Nine = (Button) findViewById(R.id.number9);*/

    }

    public void setEditAndTextView(String typeChar)
    {
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(typeChar);

        if (typeChar.equals("+") || typeChar.equals("-") || typeChar.equals("*") || typeChar.equals("/") || typeChar.equals("="))
        {
            stringBuilder.append(" ");
            stringBuilder.append(typeChar);
            stringBuilder.append(" ");
        }
        else
            stringBuilder.append(typeChar);

        finalString = stringBuilder.toString();
        textView.setText(finalString);
    }

    public void deleteLastChar()
    {
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        String lastChar = finalString.substring(finalString.length()-1);

        finalString = finalString.substring(0,finalString.length()-1);
        stringBuilder = stringBuilder.deleteCharAt(finalString.length());

        if ( lastChar.equals(" "))
        {
            finalString = finalString.substring(0,finalString.length()-1);
            stringBuilder = stringBuilder.deleteCharAt(finalString.length());
            finalString = finalString.substring(0,finalString.length()-1);
            stringBuilder = stringBuilder.deleteCharAt(finalString.length());
        }

        textView.setText(finalString);
        editText.setText("");

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
                setEditAndTextView("*");break;
            case R.id.buttonDevide:
                setEditAndTextView("/");break;
            case R.id.buttonEqual:
                setEditAndTextView("=");break;
            case R.id.buttonPoint:
                setEditAndTextView(",");break;
            case R.id.buttonPercentage:
                setEditAndTextView("%");break;
            case R.id.buttonOdmocnina:
                setEditAndTextView("√");break;
            case R.id.buttonMocnina:
                setEditAndTextView("^");break;
            case R.id.buttonSustava:
                break;
            case R.id.buttonFaktorial:
                setEditAndTextView("!");break;
            case R.id.buttonDelete:
                deleteLastChar();break;
        }
    }

}
