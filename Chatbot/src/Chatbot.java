import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static List<String> vocabulary;

    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    private static void readVocabulary() {
        File file = new File("vocabulary.txt");
        vocabulary = new ArrayList<String>();
        vocabulary.add("OOV"); // 0 -> OOV

        try (Scanner fin = new Scanner(file)) {
            while (fin.hasNextLine()) {
                vocabulary.add(fin.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();

        int flag = Integer.valueOf(args[0]);

        if(flag == 100){
            int w = Integer.valueOf(args[1]);
            int count = 0;
            //TODO count occurence of w
            for(int i = 0; i < corpus.size(); i++){
                if(corpus.get(i) == w){
                    count++;
                }
            }

            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //TODO generate
            int count = 0;
            ArrayList<Double> unigram = new ArrayList<>();
            // create Unigram Language Model
            readVocabulary();
            for(int i = 0; i < vocabulary.size(); i++){
                for(int j = 0; j < corpus.size(); j++){
                    if(corpus.get(j) == i) {
                        count++;
                    }
                }
                double p = count/(double)corpus.size();
                unigram.add(p);
                count = 0;
            }
            // create v intervals
            ArrayList<double[]> intervals = new ArrayList<>();
            // create interval 0
            double r0 = unigram.get(0);
            double[] seg0 = new double[2];
            seg0[0] = 0;
            seg0[1] = r0;
            intervals.add(seg0);
            // create other intervals
            for(int i = 1; i < vocabulary.size(); i++){
                double l = 0;
                double r = 0;
                for(int j = 0; j <= i; j++){
                    double p = unigram.get(j);
                    if(p != 0){
                        r += p;
                        if(j != i){
                            l += p;
                        }
                    }
                }
                double[] seg = new double[2];
                seg[0] = l;
                seg[1] = r;
                intervals.add(seg);
            }
            double r = (double)n1/(double)n2;

            int index = 0;
            for(int i = 0; i < intervals.size(); i++){
                double[] seg = intervals.get(i);
                if(r >= seg[0] && r <= seg[1]){
                    index = i;
                }
            }
            double[] seg = intervals.get(index);
            System.out.println(index);
            System.out.println(String.format("%.7f",seg[0]));
            System.out.println(String.format("%.7f",seg[1]));
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            // Create Bigram Language Model
            readVocabulary();
            // find c(h,w)
            for(int i = 0; i < corpus.size(); i++) {
                if (i != corpus.size() - 1) {
                    if (corpus.get(i) == h && corpus.get(i + 1) == w) {
                        count++;
                    }
                }
            }
            // find c(h,u)
            for(int i = 0; i < vocabulary.size(); i++){
                for(int j = 0; j < corpus.size(); j++){
                    if (corpus.get(j) == h && corpus.get(j+1) == i){
                        words_after_h.add(i);
                    }
                }
            }
            //output
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            //TODO
            readVocabulary();
            ArrayList<double[]> intervals = new ArrayList<>();
            // create interval 0
            double[] seg0 = new double[2];
            seg0[0] = 0;
            // beginning of method from Part 2
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<>();
            // find c(h,0)
            for(int i = 0; i < corpus.size(); i++) {
                if (i != corpus.size() - 1) {
                    if (corpus.get(i) == h && corpus.get(i + 1) == 0) {
                        count++;
                    }
                }
            }
            // find c(h,u)
            for(int i = 0; i < vocabulary.size(); i++){
                for(int j = 0; j < corpus.size(); j++){
                    if (corpus.get(j) == h && corpus.get(j+1) == i){
                        words_after_h.add(i);
                    }
                }
            }
            // end of method from Part 2
            seg0[1] = count/(double)words_after_h.size();
            intervals.add(seg0);
            // create other intervals
            ArrayList<Double> bigram = new ArrayList<>();
            for(int k = 0; k < vocabulary.size(); k++) {
                count = 0;
                // find c(h,w)
                for (int i = 0; i < corpus.size(); i++) {
                    if (i != corpus.size() - 1) {
                        if (corpus.get(i) == h && corpus.get(i + 1) == k) {
                            count++;
                        }
                    }
                }
                bigram.add(count/(double)words_after_h.size());
            }

            for(int i = 1; i < vocabulary.size(); i++){
                double l = 0;
                double r = 0;
                for(int j = 0; j <= i; j++){
                    double p = bigram.get(j);
                    if(p != 0) {
                        r += p;
                        if (j != i) {
                            l += p;
                        }
                    }
                }
                double[] seg = new double[2];
                seg[0] = l;
                seg[1] = r;
                intervals.add(seg);
            }
            double r = (double)n1/(double)n2;

            int index = 0;
            for(int i = 0; i < intervals.size(); i++){
                double[] seg = intervals.get(i);
                if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                    index = i;
                    break;
                }
            }
            double[] seg = intervals.get(index);
            System.out.println(index);
            System.out.println(String.format("%.7f",seg[0]));
            System.out.println(String.format("%.7f",seg[1]));
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO
            // Create Trigram Language Model
            readVocabulary();
            // find c(h,w)
            for(int i = 0; i < corpus.size(); i++) {
                if (i != corpus.size() - 2) {
                    if (corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == w) {
                        count++;
                    }
                }
            }
            // find c(h,u)
            for(int i = 0; i < vocabulary.size(); i++){
                for(int j = 0; j < corpus.size(); j++){
                    if (corpus.get(j) == h1 && corpus.get(j+1) == h2 && corpus.get(j+2) == i){
                        words_after_h1h2.add(i);
                    }
                }
            }
            //output
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            readVocabulary();
            ArrayList<double[]> intervals = new ArrayList<>();
            // create interval 0
            double[] seg0 = new double[2];
            seg0[0] = 0;
            // beginning of method from Part 2
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<>();
            // find c(h,0)
            for(int i = 0; i < corpus.size(); i++) {
                if (i != corpus.size() - 2) {
                    if (corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == 0) {
                        count++;
                    }
                }
            }
            // find c(h,u)
            for(int i = 0; i < vocabulary.size(); i++){
                for(int j = 0; j < corpus.size(); j++){
                    if (corpus.get(j) == h1 && corpus.get(j+1) == h2 && corpus.get(j+2) == i){
                        words_after_h1h2.add(i);
                    }
                }
            }
            // end of method from Part 2
            if(words_after_h1h2.size() == 0){
                System.out.println("undefined");
                return;
            }
            seg0[1] = count/(double)words_after_h1h2.size();
            intervals.add(seg0);
            // create other intervals
            ArrayList<Double> trigram = new ArrayList<>();
            for(int k = 0; k < vocabulary.size(); k++) {
                count = 0;
                // find c(h,w)
                for (int i = 0; i < corpus.size(); i++) {
                    if (i != corpus.size() - 2) {
                        if (corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == k) {
                            count++;
                        }
                    }
                }
                trigram.add(count/(double)words_after_h1h2.size());
            }

            for(int i = 1; i < vocabulary.size(); i++){
                double l = 0;
                double r = 0;
                for(int j = 0; j <= i; j++){
                    double p = trigram.get(j);
                    if(p != 0) {
                        r += p;
                        if (j != i) {
                            l += p;
                        }
                    }
                }
                double[] seg = new double[2];
                seg[0] = l;
                seg[1] = r;
                intervals.add(seg);
            }
            double r = (double)n1/(double)n2;

            int index = 0;
            for(int i = 0; i < intervals.size(); i++){
                double[] seg = intervals.get(i);
                if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                    index = i;
                    break;
                }
            }
            double[] seg = intervals.get(index);
            System.out.println(index);
            System.out.println(String.format("%.7f",seg[0]));
            System.out.println(String.format("%.7f",seg[1]));
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;
            readVocabulary();
            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                // beginning of Unigram Language Model
                int count = 0;
                ArrayList<Double> unigram = new ArrayList<>();
                // create Unigram Language Model

                for(int i = 0; i < vocabulary.size(); i++){
                    for(int j = 0; j < corpus.size(); j++){
                        if(corpus.get(j) == i) {
                            count++;
                        }
                    }
                    double p = count/(double)corpus.size();
                    unigram.add(p);
                    count = 0;
                }
                // create v intervals
                ArrayList<double[]> intervals = new ArrayList<>();
                // create interval 0
                double r0 = unigram.get(0);
                double[] seg0 = new double[2];
                seg0[0] = 0;
                seg0[1] = r0;
                intervals.add(seg0);
                // create other intervals
                for(int i = 1; i < vocabulary.size(); i++){
                    double l = 0;
                    double ri = 0;
                    for(int j = 0; j <= i; j++){
                        double p = unigram.get(j);
                        if(p != 0){
                            ri += p;
                            if(j != i){
                                l += p;
                            }
                        }
                    }
                    double[] seg = new double[2];
                    seg[0] = l;
                    seg[1] = ri;
                    intervals.add(seg);
                }

                int index = 0;
                for(int i = 0; i < intervals.size(); i++){
                    double[] seg = intervals.get(i);
                    if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                        index = i;
                        break;
                    }
                }
                h1 = index;
                // end of Unigram Language Model
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                intervals.clear();
                // create interval 0
                seg0[0] = 0;
                // beginning of method from Part 2
                count = 0;
                ArrayList<Integer> words_after_h = new ArrayList<>();
                // find c(h,0)
                for(int i = 0; i < corpus.size(); i++) {
                    if (i != corpus.size() - 1) {
                        if (corpus.get(i) == h1 && corpus.get(i + 1) == 0) {
                            count++;
                        }
                    }
                }
                // find c(h,u)
                for(int i = 0; i < vocabulary.size(); i++){
                    for(int j = 0; j < corpus.size(); j++){
                        if (corpus.get(j) == h1 && corpus.get(j+1) == i){
                            words_after_h.add(i);
                        }
                    }
                }
                // end of method from Part 2
                seg0[1] = count/(double)words_after_h.size();
                intervals.add(seg0);
                // create other intervals
                ArrayList<Double> bigram = new ArrayList<>();
                for(int k = 0; k < vocabulary.size(); k++) {
                    count = 0;
                    // find c(h,w)
                    for (int i = 0; i < corpus.size(); i++) {
                        if (i != corpus.size() - 1) {
                            if (corpus.get(i) == h1 && corpus.get(i + 1) == k) {
                                count++;
                            }
                        }
                    }
                    bigram.add(count/(double)words_after_h.size());
                }

                for(int i = 1; i < vocabulary.size(); i++){
                    double l = 0;
                    double ri = 0;
                    for(int j = 0; j <= i; j++){
                        double p = bigram.get(j);
                        if(p != 0) {
                            ri += p;
                            if (j != i) {
                                l += p;
                            }
                        }
                    }
                    double[] seg = new double[2];
                    seg[0] = l;
                    seg[1] = ri;
                    intervals.add(seg);
                }

                index = 0;
                for(int i = 0; i < intervals.size(); i++){
                    double[] seg = intervals.get(i);
                    if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                        index = i;
                        break;
                    }
                }
                h2 = index;
                // end of Unigram Language Model
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                // Beginning of Digram Language Model
                double r = rng.nextDouble();
                // create interval 0
                ArrayList<double[]> intervals = new ArrayList<>();
                double[] seg0 = new double[2];
                seg0[0] = 0;
                // beginning of method from Part 2
                int count = 0;
                ArrayList<Integer> words_after_h = new ArrayList<>();
                // find c(h,0)
                for(int i = 0; i < corpus.size(); i++) {
                    if (i != corpus.size() - 1) {
                        if (corpus.get(i) == h1 && corpus.get(i + 1) == 0) {
                            count++;
                        }
                    }
                }
                // find c(h,u)
                for(int i = 0; i < vocabulary.size(); i++){
                    for(int j = 0; j < corpus.size(); j++){
                        if (corpus.get(j) == h1 && corpus.get(j+1) == i){
                            words_after_h.add(i);
                        }
                    }
                }
                // end of method from Part 2
                seg0[1] = count/(double)words_after_h.size();
                intervals.add(seg0);
                // create other intervals
                ArrayList<Double> bigram = new ArrayList<>();
                for(int k = 0; k < vocabulary.size(); k++) {
                    count = 0;
                    // find c(h,w)
                    for (int i = 0; i < corpus.size(); i++) {
                        if (i != corpus.size() - 1) {
                            if (corpus.get(i) == h1 && corpus.get(i + 1) == k) {
                                count++;
                            }
                        }
                    }
                    bigram.add(count/(double)words_after_h.size());
                }

                for(int i = 1; i < vocabulary.size(); i++){
                    double l = 0;
                    double ri = 0;
                    for(int j = 0; j <= i; j++){
                        double p = bigram.get(j);
                        if(p != 0) {
                            ri += p;
                            if (j != i) {
                                l += p;
                            }
                        }
                    }
                    double[] seg = new double[2];
                    seg[0] = l;
                    seg[1] = ri;
                    intervals.add(seg);
                }

                int index = 0;
                for(int i = 0; i < intervals.size(); i++){
                    double[] seg = intervals.get(i);
                    if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                        index = i;
                        break;
                    }
                }
                h2 = index;
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                // Begin of Trigram Language Model
                ArrayList<double[]> intervals = new ArrayList<>();
                // create interval 0
                double[] seg0 = new double[2];
                seg0[0] = 0;
                // beginning of method from Part 2
                int count = 0;
                ArrayList<Integer> words_after_h1h2 = new ArrayList<>();
                // find c(h,0)
                for(int i = 0; i < corpus.size(); i++) {
                    if (i != corpus.size() - 2) {
                        if (corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == 0) {
                            count++;
                        }
                    }
                }
                // find c(h,u)
                for(int i = 0; i < vocabulary.size(); i++){
                    for(int j = 0; j < corpus.size(); j++){
                        if (corpus.get(j) == h1 && corpus.get(j+1) == h2 && corpus.get(j+2) == i){
                            words_after_h1h2.add(i);
                        }
                    }
                }
                // end of method from Part 2
                if(words_after_h1h2.size() == 0){
                    System.out.println("undefined");
                    return;
                }
                seg0[1] = count/(double)words_after_h1h2.size();
                intervals.add(seg0);
                // create other intervals
                ArrayList<Double> trigram = new ArrayList<>();
                for(int k = 0; k < vocabulary.size(); k++) {
                    count = 0;
                    // find c(h,w)
                    for (int i = 0; i < corpus.size(); i++) {
                        if (i != corpus.size() - 2) {
                            if (corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == k) {
                                count++;
                            }
                        }
                    }
                    trigram.add(count/(double)words_after_h1h2.size());
                }

                for(int i = 1; i < vocabulary.size(); i++){
                    double l = 0;
                    double ri = 0;
                    for(int j = 0; j <= i; j++){
                        double p = trigram.get(j);
                        if(p != 0) {
                            ri += p;
                            if (j != i) {
                                l += p;
                            }
                        }
                    }
                    double[] seg = new double[2];
                    seg[0] = l;
                    seg[1] = ri;
                    intervals.add(seg);
                }
                int index = 0;
                for(int i = 0; i < intervals.size(); i++){
                    double[] seg = intervals.get(i);
                    if(r >= seg[0] && r <= seg[1] && seg[0] != seg[1]){
                        index = i;
                        break;
                    }
                }
                w = index;

                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}

