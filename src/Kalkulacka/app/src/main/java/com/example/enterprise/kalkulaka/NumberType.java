package com.example.enterprise.kalkulaka;

/**
 * Created by enterprise on 27.3.17.
 */

public class NumberType implements Type
{
    Double number;

    public NumberType(String num)
    {
        number = Double.parseDouble(num);
    }

    public Double returnNumber()

    {
        return number;
    }

    public String numberToString()
    {
        String numToStr;
        numToStr = ((Integer) number.intValue()).toString();
        return  numToStr;
    }

    public boolean isOperator()
    {
        return false;
    }

    public String Show_character(){
        return this.number.toString();
    }

}
