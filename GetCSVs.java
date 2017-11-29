import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davi2705 on 11/29/2017.
 */
public class GetCSVs {
    //String[] csvnames = new String[] {};
    List<String> csvlist = new ArrayList<>();
    public void GetCSVs(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()&& (file.toString().contains("csv"))){
                //System.out.println(file.getAbsolutePath());
                csvlist.add(file.getAbsolutePath());
            } else if (file.isDirectory()){
                 GetCSVs(file.getAbsolutePath());
            }
        }
    }
}
