import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davi2705 on 11/29/2017.
 */
public class GetCSVs {
    //String[] csvnames = new String[] {};
    List<String> csvlist = new ArrayList<>();
    int somefiles = 0;
    public void GetCSVs(String directoryName){
        int sholl = 0;

        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()&& (file.toString().contains("csv"))){
                if (file.toString().contains("sholl")){
                    //System.out.println("sholl");
                    sholl++;
                }else {
                    //System.out.println(file.getAbsolutePath());
                    csvlist.add(file.getAbsolutePath());
                    somefiles++;
                }
            } else if (file.isDirectory()){
                 GetCSVs(file.getAbsolutePath());
            }
        }
    }
}
