package FileHandling;
import java.io.FileReader;
import java.io.IOException;

public class Step3 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("FileHandling/text.txt");
            int i;

            while((i = fr.read())!=-1) {
                System.out.println((char)i);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}
