/**
 * Created by davi2705 on 8/3/2017.
 */
public class Dendrite {
    //dendrite variables
    String pneuron; //which neuron dendrite is a part of
    int id;  // dendrite ID
    String level;   //Primary, secondary, tertiary...
    double dendLeng; //length of dendrite
    int parentD; //0 if primary otherwise parents id
    String children; //children ID
    int childnum;   //number of children

    //Constructor
    public Dendrite(String p,int a,String b,String c, int d,String e, int f){
        pneuron = p;
        id = a;
        level = b;
        String num = new String(c);
        //double hold =
        dendLeng = Double.valueOf(num);
        parentD = d;
        children = e;
        childnum = f;
        //System.out.println("dendrite made "+level);

    }

}
