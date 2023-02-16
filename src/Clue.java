public class Clue {
    private int[] elements;
    private int compare;
    private String story;
    private int type;
    private int size;

    public Clue(int t, int s){
        type = t;
        size = s;
    }

    public void setElements(int[] e){
        elements = e;
    }

    public void setCompare(int c){
        compare = c;
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
