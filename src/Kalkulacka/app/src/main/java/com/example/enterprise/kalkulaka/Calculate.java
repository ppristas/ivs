package com.example.enterprise.kalkulaka;

import java.util.Collection;

/**
 * Created by enterprise on 27.3.17.
 */

public class Calculate
{
    public double Calculate(String example)
    {
        Parser parser = new Parser();

        Collection<Type> type = parser.Parse(example);

        Double a = 2.3;
        return a;
    }
}
