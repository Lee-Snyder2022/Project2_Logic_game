public class Clue {
    private int[] elements;
    private int compare;
    private String story;
    private int type;
    private int size;
    private int grid;

    public Clue(int t, int s, int g){
        type = t;
        size = s;
        grid = g;
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
    public int getElement(int n){
        return elements[n];
    }

    public String toString(){
        switch (type){
            case 0:
                story = (elements[1]+1)+ " is paired with " + (elements[0]+1) + " Grid: " + grid;
                break;
            case 1:
                story = (elements[1]+1) + " is not paired with " + (elements[0]+1)  + " Grid: " + grid;
                break;
            case 2:
                story = (elements[1]+1) + " and " + (elements[2]+1) + " are not paired with " + (elements[0]+1) + " Grid: " + grid;
                break;
            case 3:
                story = "Either " + (elements[1]+1) + " or " + (elements[2]+1) +  " is paired with " + (elements[0]+1) + " Grid: " + grid;
                break;
            default:
                break;
        }
        return story;
    }
}
