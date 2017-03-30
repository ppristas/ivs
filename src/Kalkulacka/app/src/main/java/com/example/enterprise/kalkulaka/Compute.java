package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by mikla on 28.3.2017.
 */

public class Compute {
    private int result;
    private Stack<Type> stack;


    /*
        consturcotor of class Compute
     */
    public Compute(){
        this.result = 1;
        this.stack = new Stack<Type>();
    }

    public int copmute(Collection<Type> input){
        Type item;
        Type poped_item;
        Iterator<Type> iterator = input.iterator();

        while(iterator.hasNext()){
            item = iterator.next();
            // item is operator
            if(item.isOperator()){
                if(item.Show_character() == "!" || item.Show_character() == "√"){
                    poped_item = stack.lastElement();


                }
                else {

                }
            }
            // item is number
            else {
                stack.push(item);
            }
        }


        // just temporary
        return 0;
    }

    protected double math_function(Type num1, Type num2, Type operator){

        NumberType number_1 = (NumberType) num1;
        NumberType number_2 = (NumberType) num2;

        switch (operator.Show_character()){
            case "+":
                return number_1.returnNumber() + number_2.returnNumber();

            case "-":
                return number_1.returnNumber() - number_2.returnNumber();

            case "*":
                return number_1.returnNumber() * number_2.returnNumber();

            case "/":
                return number_1.returnNumber() / number_2.returnNumber();

            case "^":
                return number_1.returnNumber() / number_2.returnNumber();

            default:
                return 0.0;
        }
    }

    protected double math_function(Type num, Type operator){
        NumberType number = (NumberType) num;

        switch (operator.Show_character()){
            case "!":
                double result = 0;
                return result;
            case "√":
                double result1 = 0;
                return result1;
            default:
                return 0.0;
        }
    }
}
