package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by mikla on 28.3.2017.
 */

public class PostToInfix {

    private Stack<OperatorType> Stack;
    private Vector<Type> output;
    public boolean Valid ;

    /*
        Constructor of PostToInfix class
        @Param -
        @Return -
     */
    public PostToInfix(){
        Stack = new Stack<OperatorType>();
        output = new Vector<Type>();
        Valid = true;
    }

    /*
        Method to convert infix numeric array to postfix
        @Param - Collection<Type> input
        @Return - Collection<Type> output
     */
    public Collection<Type> convert(Collection<Type> input){

        Iterator<Type> it = input.iterator();
        while(it.hasNext()){
            Type item = it.next();

            //case if the item is operator
            if(item.isOperator()){
                OperatorType operator = (OperatorType) item;
                //before we put item on stack, we need to check
                // if there is already item with same priority or higher priority

                // stack is emtpy, so we just put the item there
                if(Stack.size() == 0){
                    Stack.add(operator);
                }
                else {

                    // we need to pop stack until we´ve got to the first (
                    if(operator.character.equals(")")){
                        OperatorType poped_element = Stack.pop();
                        while(!poped_element.character.equals("(")){
                            output.add(poped_element);
                            poped_element = Stack.pop();
                        }
                    }
                    // new element has higher priority than the last one
                    else if(operator.hasLowerPriority(Stack.lastElement())){
                        Stack.add(operator);
                    }
                    else{
                        // we pop the last element from stack and push there the new one
                        OperatorType poped_element = Stack.pop();
                        output.add(poped_element);
                        Stack.add(operator);
                    }
                }
            }
            else{
                //if the item is number, we put it on the output
                output.add(item);
            }
        }

        // stack is still not empty
        if(!Stack.empty()){
            while(!Stack.empty()){
                Type poped_item = Stack.pop();
                output.add(poped_item);
            }
        }
        return output;
    }

}
