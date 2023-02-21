public class Cell {
    private int mark;
    private boolean status;
    //Keeps track of whether the cell has been filled in by the player or computer
    private boolean autofilled;
    //Holds the correct value for the cell
    private int correct;

    public Cell(int correct){
        autofilled = false;
        this.correct = correct;
        status = false;
        mark = 0;
    }

    public Cell(){
        this(0);
    }

    public void setAutofilled(boolean autofilled) {
        this.autofilled = autofilled;
    }

    public boolean isAutofilled() {
        return autofilled;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getCorrect() {
        return correct;
    }

    //Checks if the cell is set to its correct value
    public boolean isCorrect(){
        if(mark == correct){
            return true;
        }
        else {
            status = true;
            mark = 0;
            return false;
        }
    }

    //Cycles the cell between values.
    public void increment(){
        if(mark < 2){
            mark = mark +1;
        }
        else if(mark == 2){
            mark = 0;
        }
    }
}
