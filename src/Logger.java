import java.io.*;

public class Logger {
    final File file = new java.io.File("output1.txt");
    final int i = 0;

    public Logger() {
    }

    public void logger(String text){
        if(i == 0){
            System.out.println(text);
        }else{
            try{
                Writer pw = new FileWriter(file);
                pw.write(text);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
