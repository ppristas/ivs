package com.example.enterprise.kalkulaka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mikla on 14.4.2017.
 */

public class StandardDeviations {


    private Compute comp;
    public StandardDeviations(){
        comp = new Compute();
    }

    public double comp(List<Double> input){
        double sum = 0.0;
        double N = input.size();
        double priemer = Priemer(input);
        for(int i = 0; i < N; i++){
            double temp = input.get(i) - priemer;
            sum += comp.exp(temp,2.0);
        }
        System.out.println("Vypocet prebieh : " + sum);
        double tsum = ((1/N)*sum);
        System.out.println("Vypocet prebieh : " + N);
        return comp.sqrt(tsum,tsum/2);
    }

    public double Priemer(List<Double> input){
        double sum = 0.0;
        for(int i = 0; i < input.size(); i++){
            sum += input.get(i);
        }
        System.out.println("Priemer je : " + sum/input.size());
        return sum/input.size();
    }




}
