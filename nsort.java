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
            int hpnt1 = 0;
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
                    //System.out.println(name.length);
                    while (x<16) {
                        //System.out.println(x);
                        dlist[hpnt1][x] = name[x];

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
            //System.out.println(dlist[8][8]);
        return dlist;
        }

        public static void main(String[]args) throws Exception{
            String wrfile = "/Users/davi2705/Documents/neat.csv";
            FileWriter writer = new FileWriter(wrfile);
            nsort.wrt(writer, Arrays.asList("its","over","9000"));

            //read1("/Users/davi2705/Documents/Nprog/traced/traces/test_trace2.csv");
            Neuron n1 = new Neuron("test_trace2.csv");
            Neuron n2 = new Neuron("test_trace1.csv");
           // System.out.println(n1.dendriteNum);

           // read2("/Users/davi2705/Downloads/Dept_Acronyms.csv");

            ///System.out.println(hold1[3]);
           //System.out.println(hold2[3]);
            //nsort.wrt(writer, Arrays.asList(hold1[5],hold2[100]));
            n1.setDendrites();
            n2.setDendrites();

            String num = new String("9.34E-04");
            double hold = (Double.valueOf(num)).doubleValue();
            double d = n1.dendlist.get(0).dendLeng;
            double d2 = n2.dendlist.get(0).dendLeng;
            System.out.println(d+ " "+d2);

            System.out.println((d+d2)/2.0);


            //double hold = (Double.valueOf("9.34E-04")).doubleValue();
            //System.out.println(hold);


            writer.flush();
            writer.close();
        }
    }


