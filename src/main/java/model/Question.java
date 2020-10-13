package model;

public class Question {
    private String description;
    private String category;
    private int score;

    public Question(String description, String category, int score) {
        this.description = description;
        this.category = category;
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
