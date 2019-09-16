import java.text.DecimalFormat;
import java.util.Random;

public class Ice {

    static public void main(String[] args) {

        int flag = Integer.valueOf(args[0]);

        int[] years = new int[]{1855,1856,1857,1858,1859,1860,1861,1862,1863,1864,1865,1866,1867,1868,1869,1870,1871,1872,1873,1874,1875,1876,1877,1878,1879,1880,1881,1882,1883,1884,1885,1886,1887,1888,1889,1890,1891,1892,1893,1894,1895,1896,1897,1898,1899,1900,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,1913,1914,1915,1916,1917,1918,1919,1920,1921,1922,1923,1924,1925,1926,1927,1928,1929,1930,1931,1932,1933,1934,1935,1936,1937,1938,1939,1940,1941,1942,1943,1944,1945,1946,1947,1948,1949,1950,1951,1952,1953,1954,1955,1956,1957,1958,1959,1960,1961,1962,1963,1964,1965,1966,1967,1968,1969,1970,1971,1972,1973,1974,1975,1976,1977,1978,1979,1980,1981,1982,1983,1984,1985,1986,1987,1988,1989,1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017};
        int[] data = new int[]{118,151,121,96,110,117,132,104,125,118,125,123,110,127,131,99,126,144,136,126,91,130,62,112,99,161,78,124,119,124,128,131,113,88,75,111,97,112,101,101,91,110,100,130,111,107,105,89,126,108,97,94,83,106,98,101,108,99,88,115,102,116,115,82,110,81,96,125,104,105,124,103,106,96,107,98,65,115,91,94,101,121,105,97,105,96,82,116,114,92,98,101,104,96,109,122,114,81,85,92,114,111,95,126,105,108,117,112,113,120,65,98,91,108,113,110,105,97,105,107,88,115,123,118,99,93,96,54,111,85,107,89,87,97,93,88,99,108,94,74,119,102,47,82,53,115,21,89,80,101,95,66,106,97,87,109,57,87,117,91,62,65,94};

        if(flag == 100){
            // Printing out the data set
            for(int i = 0; i < years.length; i++){
                System.out.println(years[i]+" "+data[i]);
            }
        }

        if(flag == 200){
            // Calculating the Sample Mean
            int total = 0;
            for(int i = 0; i < data.length; i++){
                total += data[i];
            }

            double sampleMean = (double)total/data.length;

            // Calculating the Sample Standard Deviation
            double total2 = 0;
            for(int i = 0; i < data.length; i++){
                total2 += Math.pow((data[i] - sampleMean),2);
            }

            double sampleSD = Math.sqrt(total2/(data.length-1));

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.println(data.length);
            System.out.println(df.format(sampleMean));
            System.out.println(df.format(sampleSD));
        }

        if(flag == 300){
            double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);

            // Calculating the Mean Squared Error
            double total = 0;
            for(int i = 0; i < data.length; i++){
                total += Math.pow((beta0 + years[i]*beta1 - data[i]),2);
            }

            double mse = total/data.length;

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.println(df.format(mse));
        }

        if(flag == 400){
            double beta0 = Double.valueOf(args[1]);
            double beta1 = Double.valueOf(args[2]);

            // Calculate the Partial Derivatives
            double total1 = 0;
            double total2 = 0;
            for(int i = 0; i < data.length; i++){
                total1 += (beta0 + years[i]*beta1 - data[i]);
                total2 += (beta0 + years[i]*beta1 - data[i])*years[i];
            }

            double d1 = ((double)2/data.length)*total1;
            double d2 = ((double)2/data.length)*total2;

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.println(df.format(d1));
            System.out.println(df.format(d2));
        }

        if(flag == 500){
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]);
            double beta0 = 0;
            double beta1 = 0;

            for(int i = 0; i < t; i++){
                // Calculate the Partial Derivatives
                double total1 = 0;
                double total2 = 0;
                for(int j = 0; j < data.length; j++){
                    total1 += (beta0 + years[j]*beta1 - data[j]);
                    total2 += (beta0 + years[j]*beta1 - data[j])*years[j];
                }

                double d1 = ((double)2/data.length)*total1;
                double d2 = ((double)2/data.length)*total2;

                beta0 = beta0 - eta*d1;
                beta1 = beta1 - eta*d2;

                // Calculate the Mean Squared Error
                double total = 0;
                for(int j = 0; j < data.length; j++){
                    total += Math.pow((beta0 + years[j]*beta1 - data[j]),2);
                }

                double mse = total/data.length;

                DecimalFormat df = new DecimalFormat("0.##");

                System.out.println((i+1)+" "+df.format(beta0)+" "+df.format(beta1)+" "+df.format(mse));
            }
        }

        if(flag == 600){
            int total1 = 0;
            int total2 = 0;
            for(int i = 0; i < data.length; i++){
                total1 += years[i];
                total2 += data[i];
            }

            double yearsMean = (double)total1/data.length;
            double dataMean = (double)total2/data.length;

            double numerator = 0;
            double denominator = 0;
            for(int i = 0; i < data.length; i++){
                numerator += ((years[i] - yearsMean)*(data[i] - dataMean));
                denominator += Math.pow(years[i] - yearsMean,2);
            }

            double beta1 = numerator/denominator;
            double beta0 = dataMean - beta1*yearsMean;

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.println(df.format(beta0)+" "+df.format(beta1));
        }

        if(flag == 700){
            double year = Double.valueOf(args[1]);

            // Calculate beta0 and beta1
            int total1 = 0;
            int total2 = 0;
            for(int i = 0; i < data.length; i++){
                total1 += years[i];
                total2 += data[i];
            }

            double yearsMean = (double)total1/data.length;
            double dataMean = (double)total2/data.length;

            double numerator = 0;
            double denominator = 0;
            for(int i = 0; i < data.length; i++){
                numerator += ((years[i] - yearsMean)*(data[i] - dataMean));
                denominator += Math.pow(years[i] - yearsMean,2);
            }

            double beta1 = numerator/denominator;
            double beta0 = dataMean - beta1*yearsMean;

            // Prediction Equation is y = beta0 + beta1*x
            double prediction = beta0 + beta1*year;

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.println(df.format(prediction));
        }

        if(flag == 800){
            int total = 0;
            for(int i = 0; i < data.length; i++){
                total += years[i];
            }

            double yearsMean = (double)total/data.length;
            double totalSD = 0;

            for(int i = 0; i < data.length; i++){
                totalSD += Math.pow(years[i] - yearsMean,2);
            }

            double yearsSD = Math.sqrt(totalSD/(data.length-1));

            // Proceed exactly as when FLAG = 500
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]);
            double beta0 = 0;
            double beta1 = 0;

            for(int i = 0; i < t; i++) {
                // Calculate the Partial Derivatives
                double total1 = 0;
                double total2 = 0;
                for (int j = 0; j < data.length; j++) {
                    total1 += (beta0 + ((years[j] - yearsMean)/yearsSD) * beta1 - data[j]);
                    total2 += (beta0 + ((years[j] - yearsMean)/yearsSD) * beta1 - data[j]) * ((years[j] - yearsMean)/yearsSD);
                }

                double d1 = ((double) 2 / data.length) * total1;
                double d2 = ((double) 2 / data.length) * total2;

                beta0 = beta0 - eta * d1;
                beta1 = beta1 - eta * d2;

                // Calculate the Mean Squared Error
                double total3 = 0;
                for (int j = 0; j < data.length; j++) {
                    total3 += Math.pow((beta0 + ((years[j] - yearsMean)/yearsSD) * beta1 - data[j]), 2);
                }

                double mse = total3 / data.length;

                DecimalFormat df = new DecimalFormat("0.##");

                System.out.println((i + 1) + " " + df.format(beta0) + " " + df.format(beta1) + " " + df.format(mse));
            }
        }

        if(flag == 900) {
            int total = 0;
            for (int i = 0; i < data.length; i++) {
                total += years[i];
            }

            double yearsMean = (double) total / data.length;
            double totalSD = 0;

            for (int i = 0; i < data.length; i++) {
                totalSD += Math.pow(years[i] - yearsMean, 2);
            }

            double yearsSD = Math.sqrt(totalSD / (data.length - 1));

            // Proceed exactly as when FLAG = 500
            double eta = Double.valueOf(args[1]);
            double t = Double.valueOf(args[2]);
            double beta0 = 0;
            double beta1 = 0;

            for (int i = 0; i < t; i++) {
                // Calculate the Partial Derivatives
                double total1 = 0;
                double total2 = 0;

                int rnd = new Random().nextInt(data.length);
                int currentYear = years[rnd];
                int currentData = data[rnd];

                total1 += (beta0 + ((currentYear - yearsMean) / yearsSD) * beta1 - currentData);
                total2 += (beta0 + ((currentYear - yearsMean) / yearsSD) * beta1 - currentData) * ((currentYear - yearsMean) / yearsSD);

                double d1 = 2 * total1;
                double d2 = 2 * total2;

                beta0 = beta0 - eta * d1;
                beta1 = beta1 - eta * d2;

                // Calculate the Mean Squared Error
                double total3 = 0;
                for (int j = 0; j < data.length; j++) {
                    total3 += Math.pow((beta0 + ((years[j] - yearsMean) / yearsSD) * beta1 - data[j]), 2);
                }

                double mse = total3 / data.length;

                DecimalFormat df = new DecimalFormat("0.##");

                System.out.println((i + 1) + " " + df.format(beta0) + " " + df.format(beta1) + " " + df.format(mse));

            }
        }
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
