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

    String character;
    Operator type;

    public OperatorType(Character operator)
    {
        character = String.valueOf(operator);

        if( operator.equals("+"))
        {
            type = Operator.ADD;
        }
        else if( operator.equals("-"))
        {
            type = Operator.SUBTRACT;
        }
        else if( operator.equals("x"))
        {
            type = Operator.MULTIPLY;
        }
        else if( operator.equals("/"))
        {
            type = Operator.DIVIDE;
        }
        else if( operator.equals("^"))
        {
            type = Operator.EXPONENTIAL;
        }
        else if( operator.equals("√"))
        {
            type = Operator.ROOT;
        }
        else if( operator.equals("!"))
        {
            type = Operator.FACTORIAL;
        }
        else if( operator.equals("("))
        {
            type = Operator.LEFT_BRACKET;
        }
        else if( operator.equals(")"))
        {
            type = Operator.RIGHT_BRACKET;
        }
        else
        {
            type = Operator.INVALID_OPERATOR;
        }
    }

}
