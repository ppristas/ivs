package com.example.enterprise.kalkulaka;

/**
 * Created by enterprise on 27.3.17.
 */

public class OperatorType implements Type
{

    public enum Operator
    {
        ADD,                //plus
        SUBTRACT,           //minus
        MULTIPLY,           //krat
        DIVIDE,             //deleno
        EXPONENTIAL,        //mocnina
        ROOT,               //odmocnina
        FACTORIAL,          //faktorial
        LEFT_BRACKET,       //lava zatvorka
        RIGHT_BRACKET,      //úprava zatvorka
        INVALID_OPERATOR
    };

    public String character;
    public Operator type;
    public int Priority;

    public OperatorType(Character operator)
    {
        character = String.valueOf(operator);

        if( operator.toString().equals("+"))
        {
            type = Operator.ADD;
            Priority = 1;
        }
        else if( operator.toString().equals("-"))
        {
            type = Operator.SUBTRACT;
            Priority = 1;
        }
        else if( operator.toString().equals("x"))
        {
            type = Operator.MULTIPLY;
            Priority = 2;
        }
        else if( operator.toString().equals("/"))
        {
            type = Operator.DIVIDE;
            Priority = 2;
        }
        else if( operator.toString().equals("^"))
        {
            type = Operator.EXPONENTIAL;
            Priority = 3;
        }
        else if( operator.toString().equals("√"))
        {
            type = Operator.ROOT;
            Priority = 3;
        }
        else if( operator.toString().equals("!"))
        {
            type = Operator.FACTORIAL;
            Priority = 3;
        }
        else if( operator.toString().equals("("))
        {
            type = Operator.LEFT_BRACKET;
            Priority = 0;
        }
        else if( operator.toString().equals(")"))
        {
            type = Operator.RIGHT_BRACKET;
            Priority = 0;
        }
        else
        {
            type = Operator.INVALID_OPERATOR;
            Priority = 0;
        }
    }

    public boolean isOperator(){
        return true;
    }

    public boolean hasLowerPriority(OperatorType op1){
        System.out.println(" hodnota op1 : " + op1.Priority);
        System.out.println(" hodnota this : " + this.Priority);
        if(this.Priority < op1.Priority){
            return false;
        }
        else {
            return true;
        }
    }

    public void Show_character(){
        System.out.println("Operator type: " + this.character);
    }

}
