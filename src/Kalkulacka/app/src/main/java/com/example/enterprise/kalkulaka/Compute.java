package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by mikla on 28.3.2017.
 */

public class Compute {
    private int result;
    private Stack<Type> stack;
    public boolean Valid;


    /*
        constructor of class Compute
     */
    public Compute(){
        this.result = 1;
        this.stack = new Stack<Type>();
        Valid = true;
    }

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
                    }catch (EmptyStackException exception){
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
                    }catch (EmptyStackException exception){
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

    protected double factorial(double number){
        if(number == 0.0){
            return 0.0;
        }
        double iterator = 1.0,result = 1.0;
        for(; iterator <= number; ++iterator){
            result *= iterator;
            System.out.println("DEBUG");
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
