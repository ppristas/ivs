package com.example.enterprise.kalkulaka;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by enterprise on 27.3.17.
 */

public class Calculate
{

    public double Calculate(String example)
    {
        Parser parser = new Parser();
        PostToInfix convertor = new PostToInfix();

        Collection<Type> type = parser.Parse(example);
        Collection<Type> postfix = convertor.convert(type);

        Iterator<Type> It = postfix.iterator();
        while(It.hasNext()){
            It.next().Show_character();
        }

        Double a = 2.3;
        return a;
    }
}
