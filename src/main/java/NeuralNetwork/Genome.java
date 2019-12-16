package NeuralNetwork;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;


public class Genome {

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private NeuralNetwork neuralNetwork;
    private String id;
    private double score;
    private String parentage;


    public Genome(int generationNumber, NeuralNetwork protoplast, String parantage) {
        this.neuralNetwork = protoplast.copy();
        this.id = "gen-" + generationNumber + "/" + generateId();
        this.parentage = parantage;
        this.score = 0.0;
    }

    void show(boolean showNetwork) {
        System.out.println("GENOME id: " + this.id + "\n"
                + "parentage: " + this.parentage + "\n"
                + "SCORE" + this.score);
        if (showNetwork) {
            this.neuralNetwork.show();
        }
        System.out.println("\n");

    }

    public String show() {

        return "GENOME id: " + this.id + "\n"
                + "parentage: " + this.parentage + "\n"
                + "SCORE" + this.score + "\n";
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentage(String parentage) {
        this.parentage = parentage;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public String generateId() {
        StringBuilder key = new StringBuilder();
        String alphabet1 = "abc";
        String alphabet2 = "123456";

        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            key.append(alphabet1.charAt(r.nextInt(alphabet1.length())));
        }
        for (int i = 0; i < 3; i++) {
            key.append(alphabet2.charAt(r.nextInt(alphabet2.length())));
        }
        return key.toString();
    }

    public String saveToMemory() {
//        return this.toString() + "\n" + "weights: \n" + Arrays.deepToString(this.getWeights())
//                + "\nbiases: \n" + Arrays.deepToString(this.getBiases()) + "\n\n- - - - - - - - - - - - -\n";
        return this.show();
    }
}
