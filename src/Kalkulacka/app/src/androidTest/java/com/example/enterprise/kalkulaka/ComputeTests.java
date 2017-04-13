package com.example.enterprise.kalkulaka;


import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by mikla on 30.3.2017.
 */

public class ComputeTests {

    /*
    Testing add function, normal use case
     */
    @Test
    public void Add_Test_1(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("1.0");
        OperatorType operator = new OperatorType('+');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,4.0);

    }

    /*
    Testing add function, special use case, result should be negative
     */
    @Test
    public void Add_Test_2(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("-4.0");
        OperatorType operator = new OperatorType('+');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,-1.0);

    }

    /*
    Testing min function, result should be negative 1
     */
    @Test
    public void Min_Test_1() {
        NumberType number_1 = new NumberType("5.0");
        NumberType number_2 = new NumberType("6.0");
        OperatorType operator = new OperatorType('-');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,-1.0);
    }

    /*
    Testing min function,result should be positive 4
     */
    @Test
    public void Min_Test_2() {
        NumberType number_1 = new NumberType("7.0");
        NumberType number_2 = new NumberType("3.0");
        OperatorType operator = new OperatorType('-');
        Compute comp = new Compute();

        double result = comp.math_function(number_1, number_2, operator);
        assertEquals(result, 4.0);
    }

    /*
    Testing mul function, normal use case
     */
    @Test
    public void Mul_Test_1(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("5.0");
        OperatorType operator = new OperatorType('x');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,15.0);
    }

    /*
    Testing mul function, result should be zero
     */
    @Test
    public void Mul_Test_2(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("0.0");
        OperatorType operator = new OperatorType('x');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,0.0);
    }

    /*
    Testing div function, normal use case
     */
    @Test
    public void Div_Test_1(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("3.0");
        OperatorType operator = new OperatorType('/');
        Compute comp = new Compute();

        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,1.0);
    }

    /*
    Testing div function, special use case, result should be 0
     */
    @Test
    public void Div_Test_2(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("0.0");
        OperatorType operator = new OperatorType('/');
        Compute comp = new Compute();
        double result = comp.math_function(number_1,number_2,operator);
        double not_a_number = Float.POSITIVE_INFINITY;
        assertEquals(result,not_a_number);
    }

    /*
    Testing factorial function, normal use case
     */
    @Test
    public void Faktorial_Test_1(){
        NumberType number = new NumberType("3.0");
        OperatorType operator = new OperatorType('!');
        Compute comp = new Compute();
        double result = comp.math_function(number,operator);
        assertEquals(result,6.0);
    }

    /*
    Testing factorial function, special use case, result should be 0.0
     */
    @Test
    public void Faktorial_Test_2(){
        NumberType number = new NumberType("0.0");
        OperatorType operator = new OperatorType('!');
        Compute comp = new Compute();
        double result = comp.math_function(number,operator);
        assertEquals(result,0.0);
    }

    /*
    Testing  square function, normal use case
     */
    @Test
    public void SQR_Test_1(){
        NumberType number = new NumberType("9.0");
        OperatorType operator = new OperatorType('√');
        Compute comp = new Compute();
        double result = comp.math_function(number,operator);
        assertEquals(result,3.0);
    }

    /*
    Testing  square function, special use case, result should be 1.0
     */
    @Test
    public void SQR_Test_2(){
        NumberType number = new NumberType("0.0");
        OperatorType operator = new OperatorType('√');
        Compute comp = new Compute();
        double result = comp.math_function(number,operator);
        assertEquals(result,0.0);
    }

    /*
    Testing  exponent function, normal use case
     */
    @Test
    public void Exp_Test_1(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("2.0");
        OperatorType operator = new OperatorType('^');
        Compute comp = new Compute();
        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,9.0);
    }

    /*
    Testing  exponent function, special use case, result should be 1.0
     */
    @Test
    public void Exp_Test_2(){
        NumberType number_1 = new NumberType("3.0");
        NumberType number_2 = new NumberType("0.0");
        OperatorType operator = new OperatorType('^');
        Compute comp = new Compute();
        double result = comp.math_function(number_1,number_2,operator);
        assertEquals(result,1.0);
    }

}