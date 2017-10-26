import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davi2705 on 8/1/2017.
 *
 * need to make a neuron object that has dendrite objects inside that, dendrites then hold info
 */
public class nsort {



        private static final char ds = ',';
        static String[] hold1;
        static int hpnt1;
        static String[] hold2 = new String[800];
        static int hpnt2;
        public static final List<Neuron> neuronList = new ArrayList<>();


        public static void wrt(Writer w, List<String> values) throws IOException {
            wrt(w, values, ds, ' ');
        }
        public static void wrt(Writer w, List<String> values, char separators) throws IOException {
            wrt(w, values, separators, ' ');
        }
        private static String followCVSformat(String value) {

            String result = value;
            if (result.contains("\"")) {
                result = result.replace("\"", "\"\"");
            }
            return result;

        }

        public static void wrt(Writer w, List<String> values, char separators, char customQuote) throws IOException{
            boolean first = true;
            if(separators == ' '){
                separators = ds;
            }
            StringBuilder sb = new StringBuilder();
            for(String value : values){
                if(!first) {
                    sb.append(separators);
                }
                if (customQuote == ' ') {
                    sb.append(followCVSformat(value));
                } else {
                    sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
                }

                first = false;
            }
            sb.append("\n");
            w.append(sb.toString());
        }


        public static String[][] read1(String args) {
            String csvfile = "/Users/davi2705/Documents/Nprog/test_trace2.csv";
            String[] hold1 = new String[800];
            int hpnt1 = 0;  //holds which dendrite is currently being read
            String thisfile = new String(args);
            BufferedReader br = null;
            BufferedReader brr = null;
            String line = "";
            String liner = "";
            String cvsSplitBy = ",";
            String[][] dlist = new String[0][0];
            int x = 0;
            int row = 0;
            try {
                //set size of dlist matrix
                br = new BufferedReader(new FileReader(thisfile));
                brr = new BufferedReader(new FileReader(thisfile));
                int column = br.readLine().split(cvsSplitBy).length;
                while((liner = brr.readLine()) != null){
                    row += 1;

                }
                dlist = new String[row][column];
                //System.out.println(column);
                //System.out.println(row);

                //read file and fill matrix
                while ((line = br.readLine()) != null) {
                    //String[] name = line.split(cvsSplitBy);
                    String[] name = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    //System.out.println(hpnt1);
                    //System.out.println(name);
                    while (x<15) {//x is which column is being read
                        //System.out.println(x);
                        dlist[hpnt1][x] = name[x];
                        //System.out.println(hpnt1 + " " + x+ " "+ name[x]);

                        x+=1;
                    }
                    hold1[hpnt1] = line;
                    x = 0;
                    hpnt1 +=1;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //System.out.println(dlist[9][8]);
        return dlist;
        }
        /**public static void doData(List<Neuron> Neuronlistdata){
            List<Neuron> nldata = Neuronlistdata;  //nldata is imported list of neurons
            int numofn = nldata.size();  //number of neurons entered
            int denum = 0;//total amount of dendrites
            double avgdln =0; //average dendrite length
            double avgprm = 0;  //average number of primaries per neuron
            double avgprmln =0;  //average primary length
            double avgsec = 0;  //average number of secondaries
            double avgsecln = 0; //average secondary length
            double avgtert = 0;  //average number of tertiaries
            double avgtertln = 0;  //average length of tertiaries
            //branches without primaries included
            Dendrite longprm = new Dendrite("0",0,"0","0",0,"0",0); //longest primary dendrite: neuron, dendrite, length
            Dendrite longsec = new Dendrite("0",0,"0","0",0,"0",0);  //longest secondary dendrite: neuron, dendrite, length
            Dendrite longtert = new Dendrite("0",0,"0","0",0,"0",0);  //longest tertiary dendrite: neuron, dendrite, length
            String[] mostprm = {"0","0"};  //neuron with most primaries: , number of primaries, neuron
            String[] mostsec = {"0","0"};  //neuron with most secondaries: , number of secondaries,neuron
            String[] mosttert = {"0","0"};  //neuron with most tertiaries: , number of tertiaries,neuron
            int x = 0;

            Neuron cnur;  //current neuron from list
            while( x<numofn){
                cnur = nldata.get(x);
                denum += cnur.dendlist.size();
                System.out.println(cnur.primnum);
                int y = 0;
                Dendrite cden;//current dendrite from current neuron
                while( y < cnur.dendlist.size()) {
                    cden = cnur.dendlist.get(y);
                    avgdln += cden.dendLeng;
                    //System.out.println(y);
                    if (cden.level.equals("Primary")) {
                        avgprm++;
                        avgprmln += cden.dendLeng;

                        if (longprm.dendLeng < cden.dendLeng) {
                            longprm = cden;
                        }
                    }else if (cden.level.equals("Secondary")) {
                        avgsec++;
                        avgsecln += cden.dendLeng;

                        if (longsec.dendLeng < cden.dendLeng) {
                            longsec = cden;
                        }
                    }else if(cden.level.equals("Tertiary")){
                        avgtert++;
                        avgtertln += cden.dendLeng;
                        if(longtert.dendLeng<cden.dendLeng){
                            longtert = cden;
                        }
                    }


                    y++;
                }
                if (Integer.valueOf(mostprm[0])<cnur.primnum){
                    mostprm[0] = String.valueOf(cnur.primnum);
                    mostprm[1] = cnur.neuronName;
                }
                if (Integer.valueOf(mostsec[0])<cnur.secnum){
                    mostsec[0] = String.valueOf(cnur.secnum);
                    mostsec[1] = cnur.neuronName;
                }
                if (Integer.valueOf(mosttert[0])<cnur.tertnum){
                    mosttert[0] = String.valueOf(cnur.tertnum);
                    mosttert[1] = cnur.neuronName;
                }
                x++;
            }
            avgprmln = (avgprmln/avgprm);
            avgsecln = (avgsecln/avgsec);
            avgtertln = (avgtertln/avgtert);
            avgdln = (avgdln/denum);
            avgprm = (avgprm/numofn);
            avgsec = avgsec/numofn;
            avgtert = (avgtert/numofn);
            System.out.println(mostprm[0]+" " +mostprm[1]);
            System.out.println(mostsec[0]+" " +mostsec[1]);
            System.out.println(mosttert[0]+" " +mosttert[1]);
            System.out.println("average dendrite length= "+avgdln+"um");
            System.out.println("average number of primaries per neuron = "+ avgprm);
            System.out.println("average primary length = "+ avgprmln);
            System.out.println("longest primary is on neuron "+ longprm.pneuron+ "  id= "+longprm.id+ " length= "+longprm.dendLeng);
            System.out.println("average number of secondaries per neuron = "+ avgsec);
            System.out.println("average secondary length = "+ avgsecln);
            System.out.println("longest secondary is on neuron "+ longsec.pneuron+ "  id= "+longsec.id+ " length= "+longsec.dendLeng);
            System.out.println("average number of tertiaries per neuron = "+ avgtert);
            System.out.println("average tertiary length = "+ avgtertln);
        }**/


        public static void main(String[]args) throws Exception{
            String wrfile = "/Users/davi2705/Documents/neat.csv";
            FileWriter writer = new FileWriter(wrfile);
            //nsort.wrt(writer, Arrays.asList("its","over","9000"));


            //read1("/Users/davi2705/Documents/Nprog/traced/traces/test_trace2.csv");
            Neuron n1 = new Neuron("test_trace2.csv");
            Neuron n3 = new Neuron("test_trace3.csv");
            Neuron n2 = new Neuron("test_trace1.csv");
            Neuron n4 = new Neuron("EmilyMM3-01--17-DIV19-10uMDFO_12_MMStack_Pos0.ome.csv");

            //nsort.wrt(writer, Arrays.asList(hold1[5],hold2[100]));

           // String num = new String("9.34E-04");
           // double hold = (Double.valueOf(num)).doubleValue();
           // String d = n1.dendlist.get(0).level;
            //String d2 = n1.dendlist.get(7).level;
           //neuronList.add(n1);
            neuronList.add(n1);
            neuronList.add(n3);
            neuronList.add(n2);
            neuronList.add(n4);
            Dendrite d312 = neuronList.get(1).dendlist.get(1);
            Dendrite d2 = neuronList.get(2).dendlist.get(3);
            //System.out.println(d312.id + " " + d312.dendLeng);
            //System.out.println(d2.id + " " + d312.dendLeng);
            Dendrite d3 = neuronList.get(0).dendlist.get(4);
            //System.out.println(d312.level);
            //System.out.println();
            //System.out.println(neuronList.get(1).dendlist.size());
            //System.out.println(d3.id+" "+d3.dendLeng);
            int x = 0;
           //System.out.println( (d312.dendLeng<d3.dendLeng));

            //doData(neuronList);
            CrunchData data = new CrunchData(neuronList);
            //nsort.wrt(writer, Arrays.asList(d3.id+" "+d3.dendLeng,d312.level));


            writer.flush();
            writer.close();
        }
    }
    //Neurons in different rows with image name and calculations as column
//look at tommys programs
// number of total neurons at bottom with averages and standard deviation
//population so for neurons that have tertiaries dont include neurons without tert for population average for neurons that
//centroid detection pop out what doesnt have a path 0 length 0


//currently testtrace1 is correctly registering its tertiary

// multiple treatments, input number of treatments, choose filenames for outputs
//hard code lower limit at 0, allow changing of settings
//check calibration of length data

//total length of all dendrites on neuron 1,2,3,all, total of branch length
// number of branches, average branch length(second and tert summed)
//average number of 1, 2, 3 for population, branches as well
//average length of primaries on one neuron, as a population average number of primaries, branches as well
//rejected file output list
//background, popko guide, why I am doing this and the goals


