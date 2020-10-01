package model;

import java.util.Date;

public class Diagnosis {
    private double score;
    private Date creationDate;

    public Diagnosis(double score, Date creationDate) {
        this.score = score;
        this.creationDate = creationDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
