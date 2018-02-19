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
    public CrunchData(List<Neuron> Neuronlistdata, File outputfile) throws Exception {
        List<Neuron> nldata = Neuronlistdata;  //nldata is imported list of neurons
       // String wrfile = "/Users/davi2705/Documents/neat.csv";

        if(SNTAnalyzerUI.totError.equals("nfe")){
            SNTAnalyzerUI.infoBox("Selected columns do not contain the correct data type","NFE");
        }

        FileWriter writer = null;
        writer = new FileWriter(outputfile);
        nsort.wrt(writer, Arrays.asList("neuron","number of dendrites","total dendrite length","number of primaries","primary length","avg prim length",
                "number of secondaries","sec length","avg sec len","number of tertiaries","tert length","avg tert len","avg dendrite length","avg branch length","num of branches","longest prim","shortest prim"));
        numofn = nldata.size();  //number of neurons entered
        int x = 0;
        while (x < numofn) {
            cnur = nldata.get(x);
            denum += cnur.dendlist.size();
            //System.out.println(cnur.primnum);
            if (cnur.cerror == 1) {
                nsort.wrt(writer, Arrays.asList(cnur.neuronName, "has incorrect data or units or data falls outside selected range"));
                nldata.remove(x);
                numofn--;

            }else if(cnur.cerror ==2){
                nsort.wrt(writer, Arrays.asList(cnur.neuronName, "has incorrect centroid calibration "));
                nldata.remove(x);
                numofn--;
            } else {
                int y = 0;
                Dendrite cden;//current dendrite from current neuron
                while (y < cnur.dendlist.size()) {
                    cden = cnur.dendlist.get(y);
                    avgdln += cden.dendLeng;
                    //System.out.println(y);
                    if (cden.level.equals("Primary")) {
                        avgprm++;
                        avgprmln += cden.dendLeng;
                        //cnur.avgprimlen += cden.dendLeng;
                        cnur.dend_1lensum += cden.dendLeng;
                        if (cnur.long_prime<cden.dendLeng) {
                            cnur.long_prime = cden.dendLeng;
                        }
                        if (cnur.short_prime>cden.dendLeng){
                            cnur.short_prime = cden.dendLeng;
                        }
                        if (longprm.dendLeng < cden.dendLeng) {
                            longprm = cden;
                        }
                    } else if (cden.level.equals("Secondary")) {
                        avgsec++;
                        avgsecln += cden.dendLeng;
                        //cnur.avgseclen+= cden.dendLeng;
                        cnur.dend_2lensum += cden.dendLeng;

                        if (longsec.dendLeng < cden.dendLeng) {
                            longsec = cden;
                        }
                    } else if (cden.level.equals("Tertiary")) {
                        avgtert++;
                        avgtertln += cden.dendLeng;
                        cnur.dend_3lensum += cden.dendLeng;
                       // cnur.avgtertlen +=cden.dendLeng;
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
                if(Double.isNaN(cnur.dend_2lensum / cnur.secnum)){
                    cnur.dend_2lensum = 0;
                    cnur.secnum = 1;
                }
                if(Double.isNaN(cnur.dend_3lensum / cnur.tertnum)){
                    cnur.dend_3lensum = 0;
                    cnur.tertnum = 1;
                }


                int dendperp = cnur.primnum + cnur.secnum + cnur.tertnum; //number of dendrites per neuron
                String[] tt = {cnur.neuronName, String.valueOf(dendperp), String.valueOf(cnur.dend_2lensum + cnur.dend_3lensum + cnur.dend_1lensum),
                        String.valueOf(cnur.primnum), String.valueOf(cnur.dend_1lensum),
                        String.valueOf(cnur.dend_1lensum / cnur.primnum), String.valueOf(cnur.secnum), String.valueOf(cnur.dend_2lensum),
                        String.valueOf(cnur.dend_2lensum / cnur.secnum), String.valueOf(cnur.tertnum), String.valueOf(cnur.dend_3lensum), String.valueOf(cnur.dend_3lensum / cnur.tertnum),
                        String.valueOf(cnur.avgdlen), String.valueOf((cnur.dend_2lensum + cnur.dend_3lensum) / (cnur.secnum + cnur.tertnum)),String.valueOf(cnur.secnum+cnur.tertnum),
                        Double.toString(cnur.long_prime), Double.toString(cnur.short_prime)};
                nsort.wrt(writer, Arrays.asList(tt));

                cnur.avgprimlen= cnur.dend_1lensum/cnur.primnum;
                cnur.avgseclen= cnur.dend_2lensum/cnur.secnum;
                cnur.avgtertlen = cnur.dend_3lensum/cnur.tertnum;


                x++;
            }
        }
        totdendlen = avgdln;
        totdendlen1 = avgprmln;
        totdendlen2 = avgsecln;
        totdendlen3 = avgtertln;
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

        //std deviation calculations

        int tt = 0;

        double primnumstd=0;
        double secnumstd = 0;
        double tertnumstd = 0;
        double poptotlenstd=0;

        double avg2_prim_len = 0;
        double avg2_sec_len = 0;
        double avg2_tert_len = 0;
        double avg2primlenstd = 0;
        double avg2seclenstd = 0;
        double avg2tertlenstd = 0;

        double pop_p_lenstd = 0;
        double pop_s_lenstd = 0;
        double pop_t_lenstd = 0;
        while(tt<nldata.size()){
            Neuron stddata =  nldata.get(tt);
            primnumstd += Math.pow(stddata.primnum -(popprimes/numofn),2);
            secnumstd += Math.pow(stddata.secnum-(popsecs/numofn),2);
            tertnumstd += Math.pow(stddata.tertnum-(popterts/numofn),2);
            avg2_prim_len+= stddata.avgprimlen;

            double ughhold = stddata.dendlensum-(totdendlen/numofn);
            poptotlenstd += Math.pow(ughhold,2);
            avg2primlenstd +=Math.pow(stddata.avgprimlen-(avgprmln),2);
            if(!Double.isNaN(stddata.avgseclen)){
                avg2seclenstd +=Math.pow(stddata.avgseclen-(avgsecln),2);
                avg2_sec_len+= stddata.avgseclen;
            }
            if(!Double.isNaN(stddata.avgtertlen)){
                avg2tertlenstd +=Math.pow(stddata.avgtertlen-(avgtertln),2);
                avg2_tert_len+= stddata.avgtertlen;
            }

            int ttt = 0;
            while(ttt<(stddata.dendlist.size())){
                Dendrite stdden = stddata.dendlist.get(ttt);
                if(stdden.level.equals("Primary")) {
                    pop_p_lenstd += Math.pow(stdden.dendLeng-(totdendlen1/popprimes),2);
                }
                if (stdden.level.equals("Secondary")){
                    pop_s_lenstd+= Math.pow(stdden.dendLeng-(totdendlen2/popsecs),2);
                }
                if (stdden.level.equals("Tertiary")){
                    pop_t_lenstd+= Math.pow(stdden.dendLeng-(totdendlen3/popsecs),2);
                }
                ttt++;
            }

            tt++;
        }
        avg2_prim_len = avg2_prim_len/numofn;
        avg2_sec_len = avg2_sec_len/numofn;
        avg2_tert_len = avg2_tert_len/numofn;

        primnumstd = Math.pow(primnumstd/numofn,.5);
        secnumstd = Math.pow(secnumstd/numofn,.5);
        tertnumstd = Math.pow(tertnumstd/numofn,.5);
        poptotlenstd = Math.pow(poptotlenstd/numofn,.5);
        avg2primlenstd = Math.pow(avg2primlenstd/numofn,.5);
        avg2seclenstd = Math.pow(avg2seclenstd/numofn,.5);
        avg2tertlenstd = Math.pow(avg2tertlenstd/numofn,.5);
        pop_p_lenstd = Math.pow(pop_p_lenstd/popprimes,.5);
        pop_s_lenstd = Math.pow(pop_s_lenstd/popsecs, .5);
        pop_t_lenstd = Math.pow(pop_t_lenstd/popterts, .5);
        System.out.println("other std "+pop_p_lenstd);


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
        //nsort.wrt(writer,Arrays.asList(ff));

        String[] qqq = new String[]{"population dendrite length ", Double.toString(totdendlen)};
        String[] qq1 = new String[]{"pop primary dendrite length",Double.toString(totdendlen1)};
        String[] qq2 = new String[]{"pop second dendrite length",Double.toString(totdendlen2)};
        String[] qq3 = new String[]{"pop tert dendrite length",Double.toString(totdendlen3)};
        String[] blank = new String[]{" "};
        String[] titlestring = new String[]{" ","pop values","std","st err m", "avg of avg values","std","st err m"};
        String[] branchwrit = new String[]{"number of branches = " ,Double.toString( branchnum)};
        String[] ntotwrit = new String[]{"number of neurons = " ,Integer.toString( numofn)};
        String[] dtotwrit = new String[]{"number of dendrites = " ,Integer.toString( denum)};

        String[] popavgprime  = new String[]{"Average # of 1 = " ,Double.toString(popprimes/numofn),Double.toString(primnumstd),
                Double.toString(primnumstd/(Math.pow(numofn,.5)))}; //avg num of primary per neuron
        String[] popavgprlen = new String[]{"Average primary length = " ,Double.toString(avgprmln),Double.toString(pop_p_lenstd), Double.toString(pop_p_lenstd/(Math.pow(popprimes,.5)))
                ,Double.toString(avg2_prim_len),Double.toString(avg2primlenstd), Double.toString(avg2primlenstd/(Math.pow(numofn,.5)))};

        String[] popavgsec  = new String[]{"Average # of 2 = " ,Double.toString(popsecs/numofn),Double.toString(secnumstd),
                Double.toString(secnumstd/(Math.pow(numofn,.5)))}; //avg num of secondary per neuron
        String[] pp = new String[]{"average secondary length = " ,Double.toString( avgsecln),Double.toString(pop_s_lenstd),Double.toString(pop_s_lenstd/(Math.pow(popsecs, .5))),
                Double.toString(avg2_sec_len),Double.toString(avg2seclenstd),Double.toString(avg2seclenstd/(Math.pow(numofn,.5)))};

        String[] popavgtert  = new String[]{"Average # of 3 = " ,Double.toString(popterts/numofn),Double.toString(tertnumstd),
                Double.toString(tertnumstd/(Math.pow(numofn,.5)))}; //avg num of tertiary par neuron
        String[] popavgtertlen = new String[]{"average tertiary length = " , Double.toString(avgtertln),Double.toString(pop_t_lenstd),Double.toString(pop_t_lenstd/(Math.pow(popterts, .5))),
                Double.toString(avg2_tert_len),Double.toString(avg2tertlenstd), Double.toString(avg2tertlenstd/(Math.pow(numofn,.5)))};

        String[] popavglength = new String[]{"pop avg dend length",Double.toString(avgdln)};
        String[] avgtotdendlen = new String[]{"avg tot dendrite length", Double.toString(totdendlen/numofn),Double.toString(poptotlenstd),
                Double.toString(poptotlenstd/(Math.pow(numofn,.5)))};
        String[] userselections = new String[]{SNTAnalyzerUI.minLabelString};

        nsort.wrt(writer,Arrays.asList(blank));
        nsort.wrt(writer,Arrays.asList(blank));
        nsort.wrt(writer,Arrays.asList(titlestring));
        nsort.wrt(writer,Arrays.asList(branchwrit));
        nsort.wrt(writer,Arrays.asList(ntotwrit));
        nsort.wrt(writer,Arrays.asList(dtotwrit));
        nsort.wrt(writer,Arrays.asList(popavgprime));
        nsort.wrt(writer,Arrays.asList(popavgprlen));
        nsort.wrt(writer,Arrays.asList(popavgsec));
        nsort.wrt(writer,Arrays.asList(pp));
        nsort.wrt(writer,Arrays.asList(popavgtert));
        nsort.wrt(writer,Arrays.asList(popavgtertlen));
        nsort.wrt(writer,Arrays.asList(avgtotdendlen));
        //nsort.wrt(writer,Arrays.asList(popavglength));
        nsort.wrt(writer,Arrays.asList(blank));
        nsort.wrt(writer,Arrays.asList(qqq));
        nsort.wrt(writer,Arrays.asList(qq1));
        nsort.wrt(writer,Arrays.asList(qq2));
        nsort.wrt(writer,Arrays.asList(qq3));
        nsort.wrt(writer,Arrays.asList(blank));






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



        // centroid in wrong spot, still work? not count centroid as primary




        }




}