package FileHandling;
import java.io.FileWriter;
import java.io.IOException;
public class Step2 {
    public static void main(String[] args) {
        try{
            FileWriter fw = new FileWriter("FileHandling/text.txt", true);
            fw.write("Hello Shreyas\n");
            fw.write("This is Step-2");
            fw.close();
            System.out.println("Data Written Successfully");
            }   catch(IOException e) {
                  System.out.println("Some error occured while writing file");
            }
 }
}
