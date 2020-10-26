package model;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Date;

public class Diagnosis {
    private int score;
    private String eixo;
    private String pergunta;

    public Diagnosis(int score, String eixo, String pergunta) {
        this.score = score;
        this.eixo = eixo;
        this.pergunta = pergunta;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEixo() {
        return eixo;
    }

    public void setEixo(String eixo) {
        this.eixo = eixo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
