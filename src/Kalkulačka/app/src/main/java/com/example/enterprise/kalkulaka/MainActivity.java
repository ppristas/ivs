package com.example.enterprise.kalkulaka;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    public TextView textView;
    public EditText editText;
    public String finalString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        finalString = "";
    }

    public void setEditAndTextView(String typeChar)
    {
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(typeChar);

        finalString = finalString + typeChar;
        textView.setText(finalString);
    }

    public void deleteLastChar()
    {
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        if ( finalString.length() != 0 )
        {
            finalString = finalString.substring(0,finalString.length()-1);
            textView.setText(finalString);
            editText.setText("");
        }

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

        return bracket;
    }

    public Boolean isCharacterOperator(String isOper)
    {

        if ( isOper.equals("+") || isOper.equals("-") || isOper.equals("*") ||isOper.equals("/") ||isOper.equals("√") ||isOper.equals("^") ||isOper.equals("("))
        {
            return true;
        }
        else
            return false;

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
                String bracket = leftOrRightBracket();
                setEditAndTextView(bracket);break;
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
