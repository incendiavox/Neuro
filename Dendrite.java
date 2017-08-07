/**
 * Created by davi2705 on 8/3/2017.
 */
public class Dendrite {
    //dendrite variables
    int id;  // dendrite ID
    String level;   //Primary, secondary, tertiary...
    double dendLeng; //length of dendrite
    int parentD; //0 if primary otherwise parents id
    String children; //children ID
    int childnum;   //number of children

    //Constructor
    public Dendrite(int a,String b,String c, int d,String e, int f){
        id = a;
        level = b;
        String num = new String(c);
        double hold = (Double.valueOf(num)).doubleValue();
        dendLeng = hold;
        parentD = d;
        children = e;
        childnum = f;
        System.out.println("dendrite made "+id);

    }

}
