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
        nsort.wrt(writer, Arrays.asList("neuron","number of primaries","number of secondaries","number of tertiaries","avg dendrite length"));
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
            String[] tt = {cnur.neuronName, String.valueOf(cnur.primnum), String.valueOf(cnur.secnum), String.valueOf(cnur.tertnum),String.valueOf(cnur.avgdlen)+"um"};
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
        String[] ff = new String[]{"average primary length = ", Double.toString(avgprmln)+"um","longest primary is on neuron ", longprm.pneuron ,
                "  id= "+Double.toString(longprm.id) , " length= " , Double.toString(longprm.dendLeng)+"um"};
        nsort.wrt(writer,Arrays.asList(ff));
        String[] pp = new String[]{"average secondary length = " ,Double.toString( avgsecln)+"um"};
        String[] qqq = new String[]{"population dendrite length ", Double.toString(totdendlen),"pop primary dendrite length",Double.toString(totdendlen1),
                "pop second dendrite length",Double.toString(totdendlen2) , "pop tert dendrite length",Double.toString(totdendlen3)};
        nsort.wrt(writer,Arrays.asList(pp));
        nsort.wrt(writer,Arrays.asList(qqq));
        writer.flush();
        writer.close();


    }
}