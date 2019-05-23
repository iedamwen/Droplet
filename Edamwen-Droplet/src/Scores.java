import java.io.Serializable;

public class Scores implements Serializable
{
        private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int score;

    public Scores(String name,int score){
        this.name = name;
        this.score = score;
    }


}
