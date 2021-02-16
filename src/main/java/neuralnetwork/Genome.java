package neuralnetwork;

import java.text.DecimalFormat;
import java.util.Random;


public class Genome {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private NodalNetwork nodalNetwork;
    private String id;
    private double score;
    private String parentage;
    private int learningNetwork;

    public Genome(int generationNumber, NodalNetwork protoplast, String parantage, int learningNetwork) {
        this.nodalNetwork = protoplast.copy();
        this.learningNetwork = learningNetwork;
        this.id = "gen-" + generationNumber + "/" + generateId();
        this.parentage = parantage;
        this.score = 0.0;
    }

    void show(boolean showNetwork) {
        System.out.println("GENOME id: " + this.id + "\n"
                + "parentage: " + this.parentage + "\n"
                + "SCORE" + this.score);
        if (showNetwork) {
            this.nodalNetwork.show();
        }

        System.out.println("\n");
    }

    public String show() {
        return "GENOME id: " + this.id + "\n"
                + "parentage: " + this.parentage + "\n"
                + "SCORE" + this.score + "\n"
                + this.nodalNetwork.showString();
    }

    public NodalNetwork getNodalNetwork() {
        return nodalNetwork;
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
        return this.show();
    }

    public int getLearningNetwork() {
        return learningNetwork;
    }

    public void setLearningNetwork(int learningNetwork) {
        this.learningNetwork = learningNetwork;
    }
}
