import java.util.ArrayList;
import java.util.List;

/**
 * Created by davi2705 on 8/3/2017.
 */
public class Neuron {
    //neuron variables
    private int dendriteNum; //number of dendrites on a neuron
    int primnum; //number of primaries on a neuron
    int secnum;  //number of secondaries on a neuron
    int tertnum;  //number of tertiaries
    double avgdlen; //average dendrite length for neuron
    double avgprimlen; //average primary length
    double avgseclen;  //average secondary length
    double avgtertlen; //average teriary length
    String neuronName;  //file name of neuron
    private String[][] neuronData; //data for neuron
    final List<Dendrite> dendlist = new ArrayList<>();
    //constructor of neuron object
    public Neuron(String filename){
        neuronData = nsort.read1("/Users/davi2705/Documents/Nprog/traced/traces/"+filename);
        dendriteNum = neuronData.length;
        dendriteNum -= 1;

        neuronName = filename;
        setDendrites();


        //String[] dendrit =  neuronData[3];
        //System.out.println(neuronData[1][1]);
    }
    public void setDendrites(){
        int hold = dendriteNum;
        int x = 1;
        int q = 0;
        int pare;


        //while loop to create the right number of dendrite objects with info put in
        while( x < hold){
            String[] dinfo = neuronData[x];
            String ary = new String("True");
            int dosomemath = 1; //do math to figure out how many kids
            if(dinfo[3].equals("true")||dinfo[3].equals("TRUE")||dinfo[3].equals("True")){
                ary = "Primary";
                primnum++;
                try {
                    dosomemath = Math.round(dinfo[9].length()/2);
                    //System.out.println("got" + dosomemath);

                }catch(NumberFormatException e){
                    dosomemath= 0;
                }
            }else{
                //while(q<hold){
                //System.out.println(x+" "+neuronData[Integer.valueOf(neuronData[x][6])][3]);
                try {
                    if (neuronData[Integer.parseInt(neuronData[x][6])][3].equals("true")||neuronData[Integer.parseInt(neuronData[x][6])][3].equals("TRUE")) {
                        ary = "Secondary";
                        secnum ++;
                    } else {
                        //testing still needs to be done here using a data set with tertiarys
                        ary = "Tertiary";
                        tertnum++;
                    }
                }catch (NumberFormatException e){
                    ary = "Secondary";
                    secnum++;
                }
                   //System.out.println(ary);
                    //q++;
                //}

            }

            //System.out.println(neuronData[x][1]);
            //System.out.println(ary+ " "+ dinfo[0] );
            //System.out.println(dinfo[8].length());
            //System.out.println(dinfo[6]);  good
            //System.out.println(dinfo[9]);
            //System.out.println(dinfo[10]);

            //takes care of empty cells
            try {
                pare = Integer.valueOf(dinfo[9]);
            }catch(NumberFormatException e){
                 pare = 0;
            }
            //System.out.println(dinfo[9]);
            //System.out.println(dinfo[3]);
            dendlist.add(new Dendrite(neuronName,Integer.parseInt(dinfo[0]),
                    ary,dinfo[4],pare,dinfo[9],dosomemath));
            avgdlen+= Double.valueOf(dinfo[4]);
            x++;
        }
        avgdlen= avgdlen/dendriteNum;
        //System.out.println(dendlist);
    }

}
