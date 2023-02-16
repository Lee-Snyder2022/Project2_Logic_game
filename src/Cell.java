public class Cell {
    private int mark;
    private int status;
    private boolean correct;

    public Cell(){
        this.mark = mark;
    }

    /*public Cell(){
        this(**);
    }*/

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
