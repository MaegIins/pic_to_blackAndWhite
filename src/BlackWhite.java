import java.io.IOException;

public class BlackWhite {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {

    String fileName = args[0];
    String newFile = args[1];
    
    if(transfo.verifyFile(fileName)){
      transfo.monochrome(fileName, newFile);
      transfo.bwg(fileName, newFile);
    }
    
    
     
  }






































}

