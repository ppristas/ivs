package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by enterprise on 27.3.17.
 */

// trieda reprezentujuca parser
public class Parser
{
    Vector<Type> element = new Vector<Type>();
    Boolean signedNumber;

    //parser kalkulacky
    public Collection<Type> Parse(String s)
    {
        StringBuilder sb = new StringBuilder();
        signedNumber = false;

        for(int i = 0; i < s.length(); i++)
        {
            Character c = s.charAt(i);

            System.out.println("Test : " + c);

            if(isCharacterNumber(c))
            {
                sb.append(c);
            }

            if(i + 1 < s.length())
            {
                Character d = s.charAt(i + 1);

                if(isCharacterOperator(d) && sb.length() > 0)
                {
                    if (signedNumber)
                    {
                        element.add( new NumberType( "-" + sb.toString()));
                    }
                    else
                        element.add( new NumberType(sb.toString()));

                    sb.setLength(0);
                    signedNumber = false;

                }
            }

            if(isCharacterOperator(c))
            {
                String minus = String.valueOf(c);

                if ( i == 0)
                {
                    if (minus.equals("-"))
                    {
                        signedNumber = true;
                    }
                    else
                    {
                        element.add( new OperatorType(c) );
                    }
                }
                else
                {
                    Character min = s.charAt(i-1);
                    String leftBracket = String.valueOf(min);

                    if ( (minus.equals("-") && leftBracket.equals("(")))
                    {
                        signedNumber = true;
                    }
                    else
                    {
                        element.add( new OperatorType(c) );
                    }
                }
            }
        }

        if(sb.length() > 0)
        {
            element.add(new NumberType(sb.toString()));
        }

        return element;
    }

// ci je znak operator
    public Boolean isCharacterOperator( Character c)
    {
        String isOper = String.valueOf(c);

        if ( isOper.equals("+") || isOper.equals("-") || isOper.equals("x") ||isOper.equals("/") ||isOper.equals("√") ||isOper.equals("^")
             ||isOper.equals("(") || isOper.equals(")") || isOper.equals("!") )
        {
            return true;
        }
        else
            return false;
    }

    // ci je znak cislo
    public Boolean isCharacterNumber(Character c)
    {

        String isNumber = String.valueOf(c);

        if ( isNumber.equals("0") || isNumber.equals("1") || isNumber.equals("2") ||isNumber.equals("3") ||isNumber.equals("4") ||isNumber.equals("5")
             ||isNumber.equals("6") || isNumber.equals("7") || isNumber.equals("8") ||isNumber.equals("9") ||isNumber.equals(".") )
        {
            return true;
        }
        else
            return false;

    }
}