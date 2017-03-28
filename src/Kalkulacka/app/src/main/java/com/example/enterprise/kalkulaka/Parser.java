package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by enterprise on 27.3.17.
 */

public class Parser
{
    Vector<Type> element = new Vector<Type>();

    public Collection<Type> Parse(String s)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++)
        {
            Character c = s.charAt(i);

            if(isCharacterNumber(c))
            {
                sb.append(c);
            }

            if(i + 1 < s.length())
            {
                Character d = s.charAt(i + 1);

                if(isCharacterOperator(d) && sb.length() > 0)
                {
                    element.add( new NumberType(sb.toString()));
                    sb.setLength(0);
                }
            }

            if(isCharacterOperator(c))
            {
                element.add( new OperatorType(c) );
            }
        }

        if(sb.length() > 0)
        {
            element.add(new NumberType(sb.toString()));
        }

        return element;
    }


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