/**
 * Created by davi2705 on 9/18/2017.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CrunchData {
    int denum = 0;//total amount of dendrites
    double avgdln = 0; //average dendrite length
    double avgprm = 0;  //average number of primaries per neuron
    double avgprmln = 0;  //average primary length
    double avgsec = 0;  //average number of secondaries
    double avgsecln = 0; //average secondary length
    double avgtert = 0;  //average number of tertiaries
    double avgtertln = 0;  //average length of tertiaries
    double totdendlen = 0; //total dendrite length of all in cell
    double totdendlen1 = 0; // total primary dendrite length
    double totdendlen2 = 0; // total secondary dendrite length
    double totdendlen3 = 0; // total tertiary dendlength
    double branchnum = 0;  //number of branches
    //branches without primaries included
    Dendrite longprm = new Dendrite("0", 0, "0", "0", 0, "0", 0); //longest primary dendrite: neuron, dendrite, length
    Dendrite longsec = new Dendrite("0", 0, "0", "0", 0, "0", 0);  //longest secondary dendrite: neuron, dendrite, length
    Dendrite longtert = new Dendrite("0", 0, "0", "0", 0, "0", 0);  //longest tertiary dendrite: neuron, dendrite, length
    String[] mostprm = {"0", "0"};  //neuron with most primaries: , number of primaries, neuron
    String[] mostsec = {"0", "0"};  //neuron with most secondaries: , number of secondaries,neuron
    String[] mosttert = {"0", "0"};  //neuron with most tertiaries: , number of tertiaries,neuron
    int numofn;

    Neuron cnur;  //current neuron from list
    public CrunchData(List<Neuron> Neuronlistdata) throws Exception {
        List<Neuron> nldata = Neuronlistdata;  //nldata is imported list of neurons
        String wrfile = "/Users/davi2705/Documents/neat.csv";
        FileWriter writer = null;
        writer = new FileWriter(wrfile);
        nsort.wrt(writer, Arrays.asList("neuron","number of dendrites","total dendrite length","number of primaries","primary length","avg prim length",
                "number of secondaries","sec length","avg sec len","number of tertiaries","tert length","avg tert len","avg dendrite length","avg branch length"));
        numofn = nldata.size();  //number of neurons entered
        int x = 0;
        while (x < numofn) {
            cnur = nldata.get(x);
            denum += cnur.dendlist.size();
            //System.out.println(cnur.primnum);
            int y = 0;
            Dendrite cden;//current dendrite from current neuron
            while (y < cnur.dendlist.size()) {
                cden = cnur.dendlist.get(y);
                avgdln += cden.dendLeng;
                //System.out.println(y);
                if (cden.level.equals("Primary")) {
                    avgprm++;
                    avgprmln += cden.dendLeng;
                    cnur.dend_1lensum+=cden.dendLeng;

                    if (longprm.dendLeng < cden.dendLeng) {
                        longprm = cden;
                    }
                } else if (cden.level.equals("Secondary")) {
                    avgsec++;
                    avgsecln += cden.dendLeng;
                    cnur.dend_2lensum+=cden.dendLeng;

                    if (longsec.dendLeng < cden.dendLeng) {
                        longsec = cden;
                    }
                } else if (cden.level.equals("Tertiary")) {
                    avgtert++;
                    avgtertln += cden.dendLeng;
                    cnur.dend_3lensum+=cden.dendLeng;
                    if (longtert.dendLeng < cden.dendLeng) {
                        longtert = cden;
                    }
                }


                y++;
            }
            if (Integer.valueOf(mostprm[0]) < cnur.primnum) {
                mostprm[0] = String.valueOf(cnur.primnum);
                mostprm[1] = cnur.neuronName;
            }
            if (Integer.valueOf(mostsec[0]) < cnur.secnum) {
                mostsec[0] = String.valueOf(cnur.secnum);
                mostsec[1] = cnur.neuronName;
            }
            if (Integer.valueOf(mosttert[0]) < cnur.tertnum) {
                mosttert[0] = String.valueOf(cnur.tertnum);
                mosttert[1] = cnur.neuronName;
            }
           int dendperp = cnur.primnum+cnur.secnum+cnur.tertnum; //number of dendrites per neuron
            String[] tt = {cnur.neuronName, String.valueOf(dendperp),String.valueOf(avgdln),String.valueOf(cnur.primnum),String.valueOf(cnur.dend_1lensum),
                    String.valueOf(cnur.dend_1lensum/cnur.primnum),String.valueOf(cnur.secnum),String.valueOf(cnur.dend_2lensum),
                    String.valueOf(cnur.dend_2lensum/cnur.secnum),String.valueOf(cnur.tertnum), String.valueOf(cnur.dend_3lensum),String.valueOf(cnur.dend_3lensum/cnur.tertnum),
                    String.valueOf(cnur.avgdlen),String.valueOf((cnur.dend_2lensum+cnur.dend_3lensum)/(cnur.secnum+cnur.tertnum))};
            nsort.wrt(writer, Arrays.asList(tt));

            x++;
        }
        totdendlen = avgdln;
        totdendlen1 = avgprmln; //check these and make sure they are working as intended
        totdendlen2 = avgsecln; //check these and make sure they are working as intended
        totdendlen3 = avgtertln; //check these and make sure they are working as intended
        branchnum = avgsec+avgtert;
        avgprmln = (avgprmln / avgprm);
        avgsecln = (avgsecln / avgsec);
        avgtertln = (avgtertln / avgtert);
        double popprimes = avgprm;
        double popsecs = avgsec;
        double popterts = avgtert;
       // double popdendnum =
        avgdln = (avgdln / denum);
        avgprm = (avgprm / numofn);
        avgsec = avgsec / numofn;
        avgtert = (avgtert / numofn);
        System.out.println(mostprm[0] + " " + mostprm[1]);
        System.out.println(mostsec[0] + " " + mostsec[1]);
        System.out.println(mosttert[0] + " " + mosttert[1]);
        System.out.println("average dendrite length= " + avgdln + "um");
        System.out.println("average number of primaries per neuron = " + avgprm);
        System.out.println("average primary length = " + avgprmln);
        System.out.println("longest primary is on neuron " + longprm.pneuron + "  id= " + longprm.id + " length= " + longprm.dendLeng);
        System.out.println("average number of secondaries per neuron = " + avgsec);
        System.out.println("average secondary length = " + avgsecln);
        System.out.println("longest secondary is on neuron " + longsec.pneuron + "  id= " + longsec.id + " length= " + longsec.dendLeng);
        System.out.println("average number of tertiaries per neuron = " + avgtert);
        System.out.println("average tertiary length = " + avgtertln);
        //System.out.println(totdendlen+" "+totdendlen1+" "+ totdendlen2+" "+totdendlen3);
        String[] ff = new String[]{"average primary length = ", Double.toString(avgprmln),"average secondary length = " ,Double.toString( avgsecln),"longest primary is on neuron ", longprm.pneuron,
                "  id= "+Double.toString(longprm.id) , " length= " , Double.toString(longprm.dendLeng)};
        nsort.wrt(writer,Arrays.asList(ff));
        String[] pp = new String[]{"average secondary length = " ,Double.toString( avgsecln)};
        String[] qqq = new String[]{"population dendrite length ", Double.toString(totdendlen),"pop primary dendrite length",Double.toString(totdendlen1),
                "pop second dendrite length",Double.toString(totdendlen2) , "pop tert dendrite length",Double.toString(totdendlen3)};
        String[] blank = new String[]{" "};
        String[] branchwrit = new String[]{"number of branches = " ,Double.toString( branchnum)};
        String[] ntotwrit = new String[]{"number of neurons = " ,Integer.toString( numofn)};
        String[] dtotwrit = new String[]{"number of dendrites = " ,Integer.toString( denum)};
        String[] popavgprime  = new String[]{"Average # of 1 = " ,Double.toString(popprimes/numofn)}; //avg num of primary per neuron
        String[] popavgsec  = new String[]{"Average # of 2 = " ,Double.toString(popsecs/numofn)}; //avg num of secondary per neuron
        String[] popavgtert  = new String[]{"Average # of 3 = " ,Double.toString(popterts/numofn)}; //avg num of tertiary par neuron
        String[] popavglength = new String[]{"pop avg dend length",Double.toString(avgdln)};

        nsort.wrt(writer,Arrays.asList(pp));
        nsort.wrt(writer,Arrays.asList(qqq));
        nsort.wrt(writer,Arrays.asList(blank));
        nsort.wrt(writer,Arrays.asList(branchwrit));
        nsort.wrt(writer,Arrays.asList(ntotwrit));
        nsort.wrt(writer,Arrays.asList(dtotwrit));
        nsort.wrt(writer,Arrays.asList(popavgprime));
        nsort.wrt(writer,Arrays.asList(popavgsec));
        nsort.wrt(writer,Arrays.asList(popavgtert));
        nsort.wrt(writer,Arrays.asList(popavglength));



/*        File directory = new File("/Users/davi2705/Documents/Nprog/traced/cntrl exdose traced");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                //System.out.println(file.getName());
                File folder = new File(file.getName());
                File[] listOfFiles = folder.listFiles();

                for (File infile : listOfFiles) {
                    if (infile.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            }
        }*/







 /*       File directory = new File("/Users/davi2705/Documents/Nprog/traced/cntrl exdose traced");
        File[] listOfFiles = folder.listF();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                File[] infile = file.listFiles();
                System.out.println(file.getName());
                //for ( File innerfile : infile) {
                  //  if( innerfile.isFile()){
                  //      System.out.println(innerfile.getName());
                  //  }
               // }
            }
        }
*/

        writer.flush();
        writer.close();




        }




}