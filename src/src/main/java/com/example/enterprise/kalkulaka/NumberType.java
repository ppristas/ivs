/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
package com.example.enterprise.kalkulaka;

/**
 * Created by enterprise on 27.3.17.
 */

/**
 * @class <NumberType></NumberType>
 * @brief konvertovanie cisla/operatora do retazca/double typu
 */
public class NumberType implements Type
{
    public Double number;

    public NumberType(String num)
    {
        number = Double.parseDouble(num);
    }

    public double returnNumber()

    {
        return number;
    }

    /**
     * @fn numberToString()
     * @brief prekonvertovanie cisla do retazca
     * @return cislo typu retazec
     */
    public String numberToString()
    {
        String numToStr;
        numToStr = ((Integer) number.intValue()).toString();
        return  numToStr;
    }

    public void setNumber(double number){
        this.number = number;
    }

    public boolean isOperator()
    {
        return false;
    }

    public String Show_character(){
        return this.number.toString();
    }

}
