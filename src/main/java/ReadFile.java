import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {

    private BufferedReader bufferedReader;

    public ReadFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("Poker.txt").getFile());
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String readNext(){
        try {
            return bufferedReader.readLine();
        }catch(java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
