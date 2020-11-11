package model;

public class Evaluation {

    public int idDiagnostico;
    public int idPergunta;
    public int score;
    public Evaluation(int score, int idPergunta, int idDiagnostico){
        this.idDiagnostico = idDiagnostico;
        this.idPergunta = idPergunta;
        this.score = score;
    }

    public int getidDiagnostico() {
        return idDiagnostico;
    }
    public void setidDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getidPergunta() {
        return idPergunta;
    }
    public void setidPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getscore() {
        return score;
    }
    public void setscore(int score) {
        this.score = score;
    }

}
