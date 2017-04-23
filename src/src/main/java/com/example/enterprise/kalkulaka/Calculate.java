/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by enterprise on 27.3.17.
 */

/**
 * @class <Calculate></Calculate>
 * @brief vypocet zadanych operacii na vstupe kalkulacky
 */
public class Calculate
{

    /**
     * @fn Calculate()
     * @brief prevod vstupu do Infixu
     * @param example input
     * @return vysledok/chyba
     */
    public String Calculate(String example)
    {


        Parser parser = new Parser();
        PostToInfix convertor = new PostToInfix();

        Collection<Type> type = parser.Parse(example);
        Collection<Type> postfix = convertor.convert(type);

        Iterator<Type> It = postfix.iterator();
        while(It.hasNext()){
            String st = It.next().Show_character();
            System.out.println("character : " + st);
        }

        Compute comp = new Compute();
        double result = comp.copmute(postfix);
        if(comp.Valid == false){
            return "invalid expression";
        }
        System.out.println("Result is : " + result);
        return Double.toString(result);


    }
}
