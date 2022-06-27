package table;

public class Table2048 {
    private int id;
    private int score;
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Table2048(int score, int num) {
        this.score = score;
        this.num = num;
    }

    public Table2048() {
    }
}
