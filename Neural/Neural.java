import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Neural {

    public static void main(String[] args){
        int flag = Integer.valueOf(args[0]);
        double w1 = Double.valueOf(args[1]);
        double w2 = Double.valueOf(args[2]);
        double w3 = Double.valueOf(args[3]);
        double w4 = Double.valueOf(args[4]);
        double w5 = Double.valueOf(args[5]);
        double w6 = Double.valueOf(args[6]);
        double w7 = Double.valueOf(args[7]);
        double w8 = Double.valueOf(args[8]);
        double w9 = Double.valueOf(args[9]);


        if(flag == 100){
            double x1 = Double.valueOf(args[10]);
            double x2 = Double.valueOf(args[11]);

            double uA = w1 + w2*x1 + w3*x2;
            double vA = Math.max(uA,0);

            double uB = w4 + x1*w5 + x2*w6;
            double vB = Math.max(uB,0);

            double uC = w7 + vA*w8 + vB*w9;
            double vC = 1/(1 + Math.exp(-uC));

            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(df.format(uA) + " " + df.format(vA) + " " + df.format(uB) + " " + df.format(vB) + " " + df.format(uC) + " " + df.format(vC));
        }

        if(flag == 200){
            double x1 = Double.valueOf(args[10]);
            double x2 = Double.valueOf(args[11]);

            double uA = w1 + w2*x1 + w3*x2;
            double vA = Math.max(uA,0);

            double uB = w4 + x1*w5 + x2*w6;
            double vB = Math.max(uB,0);

            double uC = w7 + vA*w8 + vB*w9;
            double vC = 1/(1 + Math.exp(-uC));

            double y = Double.valueOf(args[12]);

            double e = 0.5*Math.pow(vC - y,2);
            double dedv = vC - y;
            double dedu = dedv*(Math.exp(-uC)/Math.pow(1 + Math.exp(-uC),2));

            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(df.format(e) + " " + df.format(dedv) + " " + df.format(dedu));
        }

        if(flag == 300){
            double x1 = Double.valueOf(args[10]);
            double x2 = Double.valueOf(args[11]);

            double uA = w1 + w2*x1 + w3*x2;
            double vA = Math.max(uA,0);

            double uB = w4 + x1*w5 + x2*w6;
            double vB = Math.max(uB,0);

            double uC = w7 + vA*w8 + vB*w9;
            double vC = 1/(1 + Math.exp(-uC));

            double y = Double.valueOf(args[12]);

            double dedv = vC - y;
            double dedu = dedv*(Math.exp(-uC)/Math.pow(1 + Math.exp(-uC),2));

            double dvA = dedu*w8;
            double dvduA = 0;
            if(uA >= 0){
                dvduA = 1;
            }
            double duA = dvA*dvduA;

            double dvB = dedu*w9;
            double dvduB = 0;
            if(uB >= 0){
                dvduB = 1;
            }
            double duB = dvB*dvduB;

            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(df.format(dvA) + " " + df.format(duA)+ " " + df.format(dvB) + " " + df.format(duB));

        }

        if(flag == 400){
            double x1 = Double.valueOf(args[10]);
            double x2 = Double.valueOf(args[11]);

            double uA = w1 + w2*x1 + w3*x2;
            double vA = Math.max(uA,0);

            double uB = w4 + x1*w5 + x2*w6;
            double vB = Math.max(uB,0);

            double uC = w7 + vA*w8 + vB*w9;
            double vC = 1/(1 + Math.exp(-uC));

            double y = Double.valueOf(args[12]);

            double dedv = vC - y;
            double dedu = dedv*(Math.exp(-uC)/Math.pow(1 + Math.exp(-uC),2));

            double dvA = dedu*w8;
            double dvduA = 0;
            if(uA >= 0){
                dvduA = 1;
            }
            double duA = dvA*dvduA;

            double dvB = dedu*w9;
            double dvduB = 0;
            if(uB >= 0){
                dvduB = 1;
            }
            double duB = dvB*dvduB;

            double dw1 = duA;
            double dw2 = x1*duA;
            double dw3 = x2*duA;
            double dw4 = duB;
            double dw5 = x1*duB;
            double dw6 = x2*duB;
            double dw7 = dedu;
            double dw8 = vA*dedu;
            double dw9 = vB*dedu;

            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(df.format(dw1) + " " + df.format(dw2)+ " " + df.format(dw3) + " " + df.format(dw4)
                    + " " + df.format(dw5) + " " + df.format(dw6) + " " + df.format(dw7) + " " + df.format(dw8) + " " + df.format(dw9));
        }

        if(flag == 500){
            double x1 = Double.valueOf(args[10]);
            double x2 = Double.valueOf(args[11]);

            double uA = w1 + w2*x1 + w3*x2;
            double vA = Math.max(uA,0);

            double uB = w4 + x1*w5 + x2*w6;
            double vB = Math.max(uB,0);

            double uC = w7 + vA*w8 + vB*w9;
            double vC = 1/(1 + Math.exp(-uC));

            double y = Double.valueOf(args[12]);

            double e = 0.5*Math.pow(vC - y,2);
            double dedv = vC - y;
            double dedu = dedv*(Math.exp(-uC)/Math.pow(1 + Math.exp(-uC),2));

            double dvA = dedu*w8;
            double dvduA = 0;
            if(uA >= 0){
                dvduA = 1;
            }
            double duA = dvA*dvduA;

            double dvB = dedu*w9;
            double dvduB = 0;
            if(uB >= 0){
                dvduB = 1;
            }
            double duB = dvB*dvduB;

            double dw1 = duA; double dw2 = x1*duA; double dw3 = x2*duA;
            double dw4 = duB; double dw5 = x1*duB; double dw6 = x2*duB;
            double dw7 = dedu; double dw8 = vA*dedu; double dw9 = vB*dedu;

            double n = Double.valueOf(args[13]);

            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(df.format(w1) + " " + df.format(w2)+ " " + df.format(w3) + " " + df.format(w4)
                    + " " + df.format(w5) + " " + df.format(w6) + " " + df.format(w7) + " " + df.format(w8) + " " + df.format(w9));
            System.out.println(df.format(e));

            w1 = w1 - n*dw1; w2 = w2 - n*dw2; w3 = w3 - n*dw3;
            w4 = w4 - n*dw4; w5 = w5 - n*dw5; w6 = w6 - n*dw6;
            w7 = w7 - n*dw7; w8 = w8 - n*dw8; w9 = w9 - n*dw9;

            uA = w1 + w2*x1 + w3*x2;
            vA = Math.max(uA,0);

            uB = w4 + x1*w5 + x2*w6;
            vB = Math.max(uB,0);

            uC = w7 + vA*w8 + vB*w9;
            vC = 1/(1 + Math.exp(-uC));

            e = 0.5*Math.pow(vC - y,2);

            System.out.println(df.format(w1) + " " + df.format(w2)+ " " + df.format(w3) + " " + df.format(w4)
                    + " " + df.format(w5) + " " + df.format(w6) + " " + df.format(w7) + " " + df.format(w8) + " " + df.format(w9));
            System.out.println(df.format(e));
        }

        if(flag == 600){
            double n = Double.valueOf(args[10]);
            ArrayList<double[]> data = readTraining(); // get data from the file
            ArrayList<double[]> testData = readEval();

            for(int i = 0; i < data.size(); i++){
                double[] currentData = data.get(i);
                double x1 = currentData[0];
                double x2 = currentData[1];
                double y = currentData[2];

                System.out.println(x1 + " " + x2 + " " + y);

                // beginning of previous algorithm
                double uA = w1 + w2*x1 + w3*x2;
                double vA = Math.max(uA,0);

                double uB = w4 + x1*w5 + x2*w6;
                double vB = Math.max(uB,0);

                double uC = w7 + vA*w8 + vB*w9;
                double vC = 1/(1 + Math.exp(-uC));

                double dedv = vC - y;
                double dedu = dedv*(Math.exp(-uC)/Math.pow(1 + Math.exp(-uC),2));

                double dvA = dedu*w8;
                double dvduA = 0;
                if(uA >= 0){
                    dvduA = 1;
                }
                double duA = dvA*dvduA;

                double dvB = dedu*w9;
                double dvduB = 0;
                if(uB >= 0){
                    dvduB = 1;
                }
                double duB = dvB*dvduB;

                double dw1 = duA; double dw2 = x1*duA; double dw3 = x2*duA;
                double dw4 = duB; double dw5 = x1*duB; double dw6 = x2*duB;
                double dw7 = dedu; double dw8 = vA*dedu; double dw9 = vB*dedu;

                DecimalFormat df = new DecimalFormat("#.00000");

                w1 = w1 - n*dw1; w2 = w2 - n*dw2; w3 = w3 - n*dw3;
                w4 = w4 - n*dw4; w5 = w5 - n*dw5; w6 = w6 - n*dw6;
                w7 = w7 - n*dw7; w8 = w8 - n*dw8; w9 = w9 - n*dw9;

                uA = w1 + w2*x1 + w3*x2;
                vA = Math.max(uA,0);

                uB = w4 + x1*w5 + x2*w6;
                vB = Math.max(uB,0);

                uC = w7 + vA*w8 + vB*w9;
                vC = 1/(1 + Math.exp(-uC));

                double e = 0;

                for(int j = 0; j < testData.size(); j++){
                    double[] currentTest = testData.get(j);
                    double xt1 = currentTest[0];
                    double xt2 = currentTest[1];
                    double yt = currentTest[2];

                    double utA = w1 + w2*xt1 + w3*xt2;
                    double vtA = Math.max(utA,0);

                    double utB = w4 + xt1*w5 + xt2*w6;
                    double vtB = Math.max(utB,0);

                    double utC = w7 + vtA*w8 + vtB*w9;
                    double vtC = 1/(1 + Math.exp(-utC));

                    e += 0.5*Math.pow(vtC - yt,2);
                }

                System.out.println(df.format(w1) + " " + df.format(w2)+ " " + df.format(w3) + " " + df.format(w4)
                        + " " + df.format(w5) + " " + df.format(w6) + " " + df.format(w7) + " " + df.format(w8) + " " + df.format(w9));
                System.out.println(df.format(e));
            }


        }

        if(flag == 700){
            double n = Double.valueOf(args[10]);
            double t = Double.valueOf(args[11]);
            ArrayList<double[]> data = readTraining(); // get data from the file
            ArrayList<double[]> testData = readEval();
            double e = 0;

            for(int k = 0; k < t; k++) {
                for (int i = 0; i < data.size(); i++) {
                    double[] currentData = data.get(i);
                    double x1 = currentData[0];
                    double x2 = currentData[1];
                    double y = currentData[2];

                    // beginning of previous algorithm
                    double uA = w1 + w2 * x1 + w3 * x2;
                    double vA = Math.max(uA, 0);

                    double uB = w4 + x1 * w5 + x2 * w6;
                    double vB = Math.max(uB, 0);

                    double uC = w7 + vA * w8 + vB * w9;
                    double vC = 1 / (1 + Math.exp(-uC));

                    double dedv = vC - y;
                    double dedu = dedv * (Math.exp(-uC) / Math.pow(1 + Math.exp(-uC), 2));

                    double dvA = dedu * w8;
                    double dvduA = 0;
                    if (uA >= 0) {
                        dvduA = 1;
                    }
                    double duA = dvA * dvduA;

                    double dvB = dedu * w9;
                    double dvduB = 0;
                    if (uB >= 0) {
                        dvduB = 1;
                    }
                    double duB = dvB * dvduB;

                    double dw1 = duA;
                    double dw2 = x1 * duA;
                    double dw3 = x2 * duA;
                    double dw4 = duB;
                    double dw5 = x1 * duB;
                    double dw6 = x2 * duB;
                    double dw7 = dedu;
                    double dw8 = vA * dedu;
                    double dw9 = vB * dedu;

                    w1 = w1 - n * dw1;
                    w2 = w2 - n * dw2;
                    w3 = w3 - n * dw3;
                    w4 = w4 - n * dw4;
                    w5 = w5 - n * dw5;
                    w6 = w6 - n * dw6;
                    w7 = w7 - n * dw7;
                    w8 = w8 - n * dw8;
                    w9 = w9 - n * dw9;

                    uA = w1 + w2 * x1 + w3 * x2;
                    vA = Math.max(uA, 0);

                    uB = w4 + x1 * w5 + x2 * w6;
                    vB = Math.max(uB, 0);

                    uC = w7 + vA * w8 + vB * w9;
                    vC = 1 / (1 + Math.exp(-uC));

                    e = 0;

                    for (int j = 0; j < testData.size(); j++) {
                        double[] currentTest = testData.get(j);
                        double xt1 = currentTest[0];
                        double xt2 = currentTest[1];
                        double yt = currentTest[2];

                        double utA = w1 + w2 * xt1 + w3 * xt2;
                        double vtA = Math.max(utA, 0);

                        double utB = w4 + xt1 * w5 + xt2 * w6;
                        double vtB = Math.max(utB, 0);

                        double utC = w7 + vtA * w8 + vtB * w9;
                        double vtC = 1 / (1 + Math.exp(-utC));

                        e += 0.5 * Math.pow(vtC - yt, 2);
                    }
                }
                DecimalFormat df = new DecimalFormat("#.00000");

                System.out.println(df.format(w1) + " " + df.format(w2) + " " + df.format(w3) + " " + df.format(w4)
                        + " " + df.format(w5) + " " + df.format(w6) + " " + df.format(w7) + " " + df.format(w8) + " " + df.format(w9));
                System.out.println(df.format(e));
            }
        }

        if(flag == 800){
            double n = Double.valueOf(args[10]);
            double t = Double.valueOf(args[11]);
            ArrayList<double[]> data = readTraining(); // get data from the file
            ArrayList<double[]> testData = readEval();
            double e = 0;
            double priorE = Double.MAX_VALUE;
            int epochs = 0;

            for(int k = 0; k < t; k++) {
                for (int i = 0; i < data.size(); i++) {
                    double[] currentData = data.get(i);
                    double x1 = currentData[0];
                    double x2 = currentData[1];
                    double y = currentData[2];

                    // beginning of previous algorithm
                    double uA = w1 + w2 * x1 + w3 * x2;
                    double vA = Math.max(uA, 0);

                    double uB = w4 + x1 * w5 + x2 * w6;
                    double vB = Math.max(uB, 0);

                    double uC = w7 + vA * w8 + vB * w9;
                    double vC = 1 / (1 + Math.exp(-uC));

                    double dedv = vC - y;
                    double dedu = dedv * (Math.exp(-uC) / Math.pow(1 + Math.exp(-uC), 2));

                    double dvA = dedu * w8;
                    double dvduA = 0;
                    if (uA >= 0) {
                        dvduA = 1;
                    }
                    double duA = dvA * dvduA;

                    double dvB = dedu * w9;
                    double dvduB = 0;
                    if (uB >= 0) {
                        dvduB = 1;
                    }
                    double duB = dvB * dvduB;

                    double dw1 = duA;
                    double dw2 = x1 * duA;
                    double dw3 = x2 * duA;
                    double dw4 = duB;
                    double dw5 = x1 * duB;
                    double dw6 = x2 * duB;
                    double dw7 = dedu;
                    double dw8 = vA * dedu;
                    double dw9 = vB * dedu;

                    w1 = w1 - n * dw1;
                    w2 = w2 - n * dw2;
                    w3 = w3 - n * dw3;
                    w4 = w4 - n * dw4;
                    w5 = w5 - n * dw5;
                    w6 = w6 - n * dw6;
                    w7 = w7 - n * dw7;
                    w8 = w8 - n * dw8;
                    w9 = w9 - n * dw9;

                    uA = w1 + w2 * x1 + w3 * x2;
                    vA = Math.max(uA, 0);

                    uB = w4 + x1 * w5 + x2 * w6;
                    vB = Math.max(uB, 0);

                    uC = w7 + vA * w8 + vB * w9;
                    vC = 1 / (1 + Math.exp(-uC));

                    e = 0;

                    for (int j = 0; j < testData.size(); j++) {
                        double[] currentTest = testData.get(j);
                        double xt1 = currentTest[0];
                        double xt2 = currentTest[1];
                        double yt = currentTest[2];

                        double utA = w1 + w2 * xt1 + w3 * xt2;
                        double vtA = Math.max(utA, 0);

                        double utB = w4 + xt1 * w5 + xt2 * w6;
                        double vtB = Math.max(utB, 0);

                        double utC = w7 + vtA * w8 + vtB * w9;
                        double vtC = 1 / (1 + Math.exp(-utC));

                        e += 0.5 * Math.pow(vtC - yt, 2);
                    }
                }

                epochs++;

                if(e > priorE){
                    break;
                }
                priorE = e;
            }
            DecimalFormat df = new DecimalFormat("#.00000");

            System.out.println(epochs);
            System.out.println(df.format(w1) + " " + df.format(w2) + " " + df.format(w3) + " " + df.format(w4)
                    + " " + df.format(w5) + " " + df.format(w6) + " " + df.format(w7) + " " + df.format(w8) + " " + df.format(w9));
            System.out.println(df.format(e));

            ArrayList<double[]> finalData = readTest();

            double accuracy = 0;

            for(int i = 0; i < finalData.size(); i++){
                double[] currentFinal = finalData.get(i);
                double xf1 = currentFinal[0];
                double xf2 = currentFinal[1];
                double yf = currentFinal[2];

                double ufA = w1 + w2 * xf1 + w3 * xf2;
                double vfA = Math.max(ufA, 0);

                double ufB = w4 + xf1 * w5 + xf2 * w6;
                double vfB = Math.max(ufB, 0);

                double ufC = w7 + vfA * w8 + vfB * w9;
                double vfC = 1 / (1 + Math.exp(-ufC));

                if((vfC >= 0.5 && yf == 1) || (vfC < 0.5 && yf == 0)){
                    accuracy++;
                }
            }
            System.out.println(df.format(accuracy/finalData.size()));
        }
    }

    private static ArrayList<double[]> readTraining(){
        ArrayList<double[]> training = new ArrayList<double[]>();
        try{
            File f = new File("hw2_midterm_A_train.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                if(sc.hasNextDouble()){
                    double[] d = new double[3];
                    d[0] = sc.nextDouble();
                    d[1] = sc.nextDouble();
                    d[2] = sc.nextDouble();
                    training.add(d);
                }
                else{
                    sc.nextLine();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return training;
    }

    private static ArrayList<double[]> readEval(){
        ArrayList<double[]> eval = new ArrayList<double[]>();
        try{
            File f = new File("hw2_midterm_A_eval.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                if(sc.hasNextDouble()){
                    double[] d = new double[3];
                    d[0] = sc.nextDouble();
                    d[1] = sc.nextDouble();
                    d[2] = sc.nextDouble();
                    eval.add(d);
                }
                else{
                    sc.nextLine();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return eval;
    }

    private static ArrayList<double[]> readTest(){
        ArrayList<double[]> test = new ArrayList<double[]>();
        try{
            File f = new File("hw2_midterm_A_test.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                if(sc.hasNextDouble()){
                    double[] d = new double[3];
                    d[0] = sc.nextDouble();
                    d[1] = sc.nextDouble();
                    d[2] = sc.nextDouble();
                    test.add(d);
                }
                else{
                    sc.nextLine();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return test;
    }
}
