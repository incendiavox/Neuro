import java.util.ArrayList;
import java.util.List;

/**
 * Created by davi2705 on 8/3/2017.
 */
public class Neuron {
    //neuron variables
    int dendriteNum;
    String neuronName;
    String[][] neuronData;
    //constructor of neuron object
    public Neuron(String filename){
        neuronData = nsort.read1("/Users/davi2705/Documents/Nprog/traced/traces/"+filename);
        dendriteNum = neuronData.length;
        dendriteNum -= 1;
        neuronName = filename;
        //String[] dendrit =  neuronData[3];
        //System.out.println(neuronData[1][1]);
    }
    public void setDendrites(){
        int hold = dendriteNum;
        int x = 1;
        int y = 0;
        final List<Dendrite> dendlist = new ArrayList<>();

        //while loop to create the right number of dendrite objects with info put in
        while( x < hold){
            String[] dinfo = neuronData[x];
            String placeholder = new String("True");
            int dosomemath = 0; //do math to figure out how many kids
            //System.out.println(neuronData[x][1]);
            //System.out.println(dinfo[0]);  good
            //System.out.println(dinfo[4]);  good
            //System.out.println(dinfo[6]);  good
            //System.out.println(dinfo[9]);
            //System.out.println(dinfo[10]);
            //System.out.println(Integer.parseInt(dinfo[6]));
            //System.out.println(dinfo[9]);
            //System.out.println(dosomemath);

            //dendlist.add(new Dendrite(Integer.parseInt(dinfo[0]),
                   // placeholder,dinfo[4],Integer.parseInt(dinfo[6]),dinfo[9],dosomemath));
            x++;
        }
        //System.out.println(dendlist);
    }

}
