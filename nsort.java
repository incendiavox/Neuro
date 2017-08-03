import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by davi2705 on 8/1/2017.
 *
 * need to make a neuron object that has dendrite objects inside that, dendrites then hold info
 */
public class nsort {



        private static final char ds = ',';
        static String[] hold1 = new String[800];
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


        public static void read1(String args) {
            String csvfile = "/Users/davi2705/Documents/Nprog/test_trace2.csv";
            String thisfile = new String(args);
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            String[][] dlist = new String[16][15];
            int x = 0;
            try {
                br = new BufferedReader(new FileReader(thisfile));
                while ((line = br.readLine()) != null) {
                    String[] name = line.split(cvsSplitBy);
                    System.out.println(hpnt1);
                    //System.out.println(line);
                    while (x<16) {
                        dlist[x][hpnt1] = name[x];
                        System.out.println(dlist[x][hpnt1]);
                        x+=1;
                    }
                    hold1[hpnt1] = line;
                    x = 0;
                    //System.out.println("Drawer = " + name[0] + " person is " + name[1]);
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
            System.out.println(dlist[8][8]);

        }
        public static void read2(String args) {
            String csvfile = "/Users/davi2705/Downloads/PRD_AdminList.csv";
            String thisfile = new String(args);
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            try {
                br = new BufferedReader(new FileReader(thisfile));
                while ((line = br.readLine()) != null) {
                    String[] name = line.split(cvsSplitBy);
                    //System.out.println(hpnt2);
                    System.out.println(name[1]);
                    hold2[hpnt2] = name[0];
                    //System.out.println("Drawer = " + name[0] + " person is " + name[1]);
                    hpnt2 +=1;
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

        }
        public static void main(String[]args) throws Exception{
            String wrfile = "/Users/davi2705/Documents/neat.csv";
            FileWriter writer = new FileWriter(wrfile);
            nsort.wrt(writer, Arrays.asList("its","over","9000"));

            read1("/Users/davi2705/Documents/Nprog/traced/traces/test_trace2.csv");
           // read2("/Users/davi2705/Downloads/Dept_Acronyms.csv");

            ///System.out.println(hold1[3]);
           //System.out.println(hold2[3]);
            //nsort.wrt(writer, Arrays.asList(hold1[5],hold2[100]));

           /* for( String val1 : hold1) {
                if (val1 != null) {
                    //System.out.println(val1);
                    if (val1.length() > 9) {
                        String[] vals = val1.split(" ");
                        for (String valss : vals) {
                            if (valss != null) {
                                for (String vals2 : hold2) {
                                    if (valss.equals(vals2)) {
                                        nsort.wrt(writer, Arrays.asList(vals2));
                                        System.out.println("found one  " + vals2);
                                    }
                                }
                            }
                        }
                    } else {
                        for (String val2 : hold2) {
                            if (val2 != null) {
                                if (val1.equals(val2)){
                                    System.out.println(val1 + " " + val2);
                                    nsort.wrt(writer, Arrays.asList(val2));
                                }
                            }
                        }
                    }
                }
            }*/
            writer.flush();
            writer.close();
        }
    }


