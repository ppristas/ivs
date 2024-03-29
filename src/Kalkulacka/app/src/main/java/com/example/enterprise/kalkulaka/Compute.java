/**@package <com.example.enterprise.kalulaka></com.example.enterprise.kalulaka>*/
package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by mikla on 28.3.2017.
 */

/**
 * @class <Compute></Compute>
 * @brief vykonava vypocty z operatorov a operandov ulozene na zasobniku
 */
public class Compute {
    private Stack<Type> stack;
    public boolean Valid;

    /**
     * @fn Compute()
     * @brief konstruktor triedy Compute
     */
    public Compute(){
        this.stack = new Stack<Type>();
        Valid = true;
    }

    /**
     * @fn compute
     * @brief odkladanie poloziek na zasobnik
     * @param input polozky zo vstupu
     * @return cislo ako vysledok
     */
    public double copmute(Collection<Type> input){
        Type item;
        Type poped_item_1;
        Type poped_item_2;
        NumberType result_item;
        Iterator<Type> iterator = input.iterator();

        while(iterator.hasNext()){
            item = iterator.next();
            // item is operator
            if(item.isOperator()){
                if(item.Show_character().equals("!") || item.Show_character().equals("√")){
                    try {
                        poped_item_1 = stack.lastElement();
                        stack.pop();
                        double result = math_function(poped_item_1, item);
                        result_item = new NumberType(Double.toString(result));
                        stack.push(result_item);
                    }catch (EmptyStackException e){
                        Valid = false;
                        return 0.0;
                    }
                    catch(NoSuchElementException e){
                        Valid = false;
                        return 0.0;
                    }
                }
                else {
                    try{
                        poped_item_1 = stack.lastElement();
                        stack.pop();
                        poped_item_2 = stack.lastElement();
                        stack.pop();
                        double result = math_function(poped_item_2,poped_item_1,item);
                        result_item = new NumberType(Double.toString(result));
                        stack.push(result_item);
                    }catch (EmptyStackException e){
                        Valid = false;
                        return 0.0;
                    }
                    catch(NoSuchElementException e){
                        Valid = false;
                        return 0.0;
                    }


                }
            }
            // item is number
            else {
                stack.push(item);
            }
        }

        result_item = (NumberType) stack.lastElement();
        return result_item.returnNumber();
    }

    /**
     * @fn math_function()
     * @param num1 cislo_1
     * @param num2 cislo_2
     * @param operator operator(+-/*)
     * @return vysledok
     */
    protected double math_function(Type num1, Type num2, Type operator){

        NumberType number_1 = (NumberType) num1;
        NumberType number_2 = (NumberType) num2;


        if(operator.Show_character().equals("+")) {
            return number_1.returnNumber() + number_2.returnNumber();
        }
        else if(operator.Show_character().equals("-")) {
            return number_1.returnNumber() - number_2.returnNumber();
        }
        else if(operator.Show_character().equals("x")) {
            return number_1.returnNumber() * number_2.returnNumber();
        }
        else if(operator.Show_character().equals("/")) {
            return number_1.returnNumber() / number_2.returnNumber();
        }
        else if(operator.Show_character().equals("^")) {
            return exp(number_1.number, number_2.number);
        }

        return 0.0;

    }

    /**
     * @fn math_function()
     * @param num cislo
     * @param operator operator odmocnina alebo faktorial
     * @return vysledok
     */
    protected double math_function(Type num, Type operator){
        NumberType number = (NumberType) num;


        if(operator.Show_character().equals("!")) {
            double result = factorial(number.number);
            return result;
        }
        else if(operator.Show_character().equals("√")) {
            double result1 = calling_sqrt((number.number));
            return result1;
        }

        return 0.0;

    }

    /**
     * @fn factorial
     * @brief vypocet faktorialu
     * @param number cislo
     * @return vysledok
     */
    protected double factorial(double number){
        if(number == 0.0){
            return 0.0;
        }
        double sign = 1.0;
        sign = sign * number;
        number = Math.abs(number);
        double iterator = 1.0,result = 1.0;
        for(; iterator <= number; ++iterator){
            result *= iterator;
        }
        return result*sign;
    }

    /**
     * @fn calling_sqrt()
     * @brief volanie pre vypocet mocniny
     * @param input vstupne cislo
     * @return vysledok
     */
    public double calling_sqrt(double input){
        if(input < 0){
            Valid = false;
            return 0.0;
        }
        return (sqrt(input,input/2));
    }

    /**
     * @fn sqrt()
     * @brief vypocet mocniny
     * @param input vstupne cislo
     * @return vysledok
     */
    protected double sqrt(double input, double guess){

        if(input == 0.0){
            return 0.0;
        }
        double validation = input/guess;
        if((this.Validate(validation,guess)) == true){
            double result = (double)Math.round(guess*10000)/10000;
            return result;
        }
        else{
            return sqrt(input, make_better_guess(input,guess));
        }
    }

    /**
     * @fn Validate()
     * @brief overenie odhadu chyby
     * @param result vysledok
     * @param guess odhad
     * @return boolean hodnota v pripade spravneho odhadu
     */
    protected boolean Validate(double result, double guess){
        if(Math.abs(result - guess) < 0.001){
            return true;
        }
        else{
            return false;
        }

}

    /**
     * @fn make_better_guess()
     * @brief vytvorenie lepsieho odhadu
     * @param input vstupne cislo
     * @param old_guess predosly odhad
     * @return novy odhad
     */
    protected double make_better_guess(double input, double old_guess){
        return ((old_guess + input / old_guess) / 2);
    }

    /**
     * @fn exp()
     * @brief exponent
     * @param input pociatocny exponent
     * @param na cislo exponentu(mocniny)
     * @return cislo_exponentu
     */
    protected double exp(double input, double na){
        if(na == 0.0){
            return 1.0;
        }
        else {
            if(na < 0) {
                if (input < 0) {
                    return -1.0 / exp(input, na * (-1));
                } else {
                    return 1.0 / exp(input, na * (-1));
                }
            }
            else {
                double sum = input;
                for (double i = 1; i < na; i++) {
                    sum = sum * input;
                }
                return sum;
            }
        }
    }
}
