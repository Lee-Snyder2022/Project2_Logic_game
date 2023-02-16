public class Clue {
    private int[][] elements;
    private String story;
    private int type;

    public Clue(int t){
        type = t;
    }

    public void setElements(int n){
    }

    public int getType(){
        return type;
    }
    public int getElement(int one, int two){
        return 1;
    }

    public String getStory(){
        return story;
    }
}
