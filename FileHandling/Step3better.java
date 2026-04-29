package FileHandling;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Step3better {
  public static void main(String[] args) {
     
    try{
        BufferedReader br = new BufferedReader(new FileReader("FileHandling/text.txt"));

        String line;

        while((line = br.readLine())!=null) {
           System.out.println(line);
        }
br.close();

    } catch(IOException e) {
         System.out.println("Error reding file");
    }
  }  
}
