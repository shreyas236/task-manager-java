package FileHandling;
import java.io.File;
import java.io.IOException;
public class Step1 {
    public static void main(String[] args) {
        try{
            File f = new File("abc/text.txt");
            Boolean result = f.createNewFile(); 

        if(result) {
            System.out.println("File Creates Successfully");
        } else {
            System.out.println("File Already Exist");
        }

        } catch(IOException e) {
            System.out.println("Some error occured");
        }
    }
}
