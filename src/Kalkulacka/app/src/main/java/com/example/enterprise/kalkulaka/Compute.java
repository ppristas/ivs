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
        constructor of class Compute
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
                return exp(number_1.number,number_2.number);

            default:
                return 0.0;
        }
    }

    protected double math_function(Type num, Type operator){
        NumberType number = (NumberType) num;

        switch (operator.Show_character()){
            case "!":
                double result = factorial(number.number);
                return result;
            case "√":
                double result1 = calling_sqrt((number.number));
                return result1;
            default:
                return 0.0;
        }
    }

    protected double factorial(double number){
        if(number == 0.0){
            return 0.0;
        }
        double iterator = 1.0,result = 1.0;
        for(; iterator <= number; ++iterator){
            result *= iterator;
        }
        return result;
    }

    public double calling_sqrt(double input){
        return (sqrt(input,input/2));
    }

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

    protected boolean Validate(double result, double guess){
        if(Math.abs(result - guess) < 0.001){
            return true;
        }
        else{
            return false;
        }

}

    protected double make_better_guess(double input, double old_guess){
        return ((old_guess + input / old_guess) / 2);
    }

    protected double exp(double input, double na){
        if(na == 0.0){
            return 1.0;
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
