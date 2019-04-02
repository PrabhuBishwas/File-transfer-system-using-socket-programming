
package client;
import java.io.*;
import java.net.*;


public class Client{
    public static void main(String[] args){
        try{
            InputStreamReader in= new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(in);
            
            String ipAddress= "";
            String fileName= "";
           
                System.out.print("Please enter a valid server ip address:");
                ipAddress= reader.readLine();
                
            System.out.println("Enter the file name:");
            fileName= reader.readLine();
            Socket socket= new Socket(ipAddress, 9090);
            InputStream inputByte= socket.getInputStream();
            BufferedInputStream input= new BufferedInputStream(inputByte);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(fileName);
            int code= input.read();
            if(code==1){
                BufferedOutputStream outputFile= new BufferedOutputStream(new FileOutputStream("D:\\download\\"+ fileName));
                byte[] buffer= new byte[1024];
                int bytesRead =0;
                while((bytesRead= input.read(buffer))!=-1){
                    System.out.print(".");
                    outputFile.write(buffer, 0, bytesRead);
                    outputFile.flush();
                    
                }
                System.out.println();
                System.out.println("File" + fileName+" was successfully downloaded");
                
            }
            else{
                System.out.println("File is not present on the server");
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
