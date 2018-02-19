import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by davi2705 on 8/3/2017.
 */
public class Neuron {
    //neuron variables
    private int dendriteNum; //number of dendrites on a neuron
    int primnum; //number of primaries on a neuron
    int secnum;  //number of secondaries on a neuron
    int tertnum;  //number of tertiaries
    int nnn; //where length data is
    int minr; //min set range
    int maxr; //max set range
    double avgdlen; //average dendrite length for neuron
    double avgprimlen; //average primary length
    double avgseclen;  //average secondary length
    double avgtertlen; //average teriary length
    double dendlensum; //total dendrite length of neuron
    double dend_1lensum; // total primary length for neuron  "these need the math hooked up"
    double dend_2lensum; // total secondery length for neuron
    double dend_3lensum; // total tertiary length for neuron
    double long_prime; //longest primary length
    double short_prime; //shortest primary length
    String neuronName;  //file name of neuron
    private String[][] neuronData; //data for neuron
    final List<Dendrite> dendlist = new ArrayList<>();
    int cerror = 0; // if this isnt 0 we got an error, also this is jank

    //constructor of neuron object
    public Neuron(String filename,int n, int minRange,int maxRange){
        System.out.println(n);
        neuronData = nsort.read1(filename);
         nnn = n;
         minr = minRange;
         maxr = maxRange;
        //neuronData = nsort.read1("/Users/davi2705/Documents/Nprog/traced/traces/"+filename);  for test files
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
        if(String.valueOf(neuronData[0][0]).equals(0) || String.valueOf(neuronData[0][nnn]).equals(0)){
            cerror = 2;
        }
/*        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter column with length data: ");
        int n = reader.nextInt(); // Scans the next token of the input as an int.
//once finished
        reader.close();*/
        //while loop to create the right number of dendrite objects with info put in
        while( x < hold){
            String[] dinfo = neuronData[x];
            String ary = new String("True");
            int dosomemath = 1; //do math to figure out how many kids

/*            if (!Double.isNaN(Double.parseDouble(dinfo[nnn - 4]))) {
                cerror = 1;
            }
            //make sure length is a number
            if (!Double.isNaN(Double.parseDouble(dinfo[nnn]))) {
                cerror = 1;
            }*/
            if(dinfo[nnn-1].equals("true")||dinfo[nnn-1].equals("TRUE")||dinfo[nnn-1].equals("True")){
                ary = "Primary";
                primnum++;
                try {
                    dosomemath = Math.round(dinfo[nnn+5].length()/2);
                    //System.out.println("got" + dosomemath);

                }catch(NumberFormatException e){
                    dosomemath= 0;
                    cerror = 1;
                }
            }else{
                //while(q<hold){
                //System.out.println(x+" "+neuronData[Integer.valueOf(neuronData[x][6])][3]);
                //System.out.println(neuronName+" "+neuronData[x][0]+ " "+ neuronData[x][nnn+2]);
                try {

                    if (neuronData[Integer.parseInt(neuronData[x][nnn+2])][nnn-1].equals("true")||neuronData[Integer.parseInt(neuronData[x][nnn+2])][nnn-1].equals("TRUE")||neuronData[Integer.parseInt(neuronData[x][nnn+2])][nnn-1].equals("True")) {
                        ary = "Secondary";
                        secnum ++;
                    } else {
                        ary = "Tertiary";
                        tertnum++;
                    }
                }catch (NumberFormatException  | NullPointerException e){
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
                pare = Integer.valueOf(dinfo[nnn+5]);
            }catch(NumberFormatException e){
                 pare = 0;
            }
            //System.out.println(dinfo[9]);
            //System.out.println(dinfo[3]);
            //hard coded
           // dendlist.add(new Dendrite(neuronName,Integer.parseInt(dinfo[0]),
               //     ary,dinfo[4],pare,dinfo[9],dosomemath));
            // user input

            //error detection
            if(Double.valueOf(dinfo[nnn])>maxr ||Double.valueOf(dinfo[nnn])<minr){
                cerror = 1;
            }


            dendlist.add(new Dendrite(neuronName,Integer.parseInt(dinfo[0]),
                    ary,dinfo[nnn],pare,dinfo[(nnn+5)],dosomemath));

            avgdlen+= Double.valueOf(dinfo[nnn]);
            dendlensum+= Double.valueOf(dinfo[nnn]);
            x++;
        }
        avgdlen= avgdlen/dendriteNum;
        short_prime = 200;
        //System.out.println(dendlist);
    }

}
