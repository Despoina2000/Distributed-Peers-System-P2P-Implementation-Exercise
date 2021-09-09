import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class Tracker{
      private Hashtable<String, Rest_Info> info = new Hashtable<String ,Rest_Info>();//basics info for 1.
      private Hashtable<Integer,PeerInfo> ID=new Hashtable<Integer,PeerInfo>();//the token id and peer informations
      private List<String> list_file=new List<File>();//list with active files
      private Hashtable<Integer,List<String>> FilesID=new Hashtable<Integer,List<String>>();//list with token id and files

      // the private class for the rest information for hashtable info
      private class Rest_Info {
        String password;
        int count_downloads=0;
        int count_failures=0;

        void Rest_Info(String p) { password = p;}
        void setCountDownloads(){count_downloads +=1;}
        void setCountFailures(){count_failures+=1;}
        String getPassword(){return password;}
        int getCountDownloads(){return count_downloads;}
        int getCountFailures(){return count_failures;}
      }

//constructors 
      public Tracker(){}
      public Tracker(String username, String password){
        Node n=Node(password);
        info.put(username,n);
      }
//register
      boolean register(PeerInfo peer){
      if(info.containsKey(peer.getUsername())){
        info.put(peer.getUsername(),Node(peer.password));

        System.out.println("Accept"); 
        return true;}

      else{
        System.out.println("No"); 
        return false;}
    }
//log in
      int log_in(PeerInfo peer){

      if(info.containsKey(peer.getUsername())){
        if (info.get(peer.getUsername()).getPassword()=password){
          int tokenId=ThreadLocalRandom.current().nextInt(1,max+1);
          ID.put(tokenId,peer);
          peer.setTOKEN_ID(tokenId);
        }
        else {
          System.out.println("False password");
          return 0;}

      }else{
        System.out.println("Username does not exist"); 
        return 0;}}
//log out
        int log_out(PeerInfo peer){
          if(ID.containsKey(peer.getTOKEN_ID())){
            ID.remove(peer.getTOKEN_ID());
            System.out.println("Success");
          }
          else System.out.println("Fail");

        }
//reply_list
void ListfileDownload(){
  try (FileReader reader = new FileReader("ListfileDownload.txt");
  BufferedReader br = new BufferedReader(reader)) {

 // read line by line
 String line;
 while ((line = br.readLine()) != null) {
     list_file.add(line);

} catch (IOException e) {
 System.err.format("IOException: %s%n", e);


}


}
}
List<String> reply_list(){
  return list_file;


}
void setFilesID(){
  Enumeration<Integer> enumeration = ID.keys();
  while(enumeration.hasMoreElements()) {
    int key = enumeration.nextElement();
    FilesID.put(key,ID.get(key).getShared_directory());
}
}

List<PeerInfo> reply_details(String file){
  List<PeerInfo> peer=new List<PeerInfo>();
  Enumeration<Integer> enumeration = ID.keys();
  while(enumeration.hasMoreElements()) {
    int key = enumeration.nextElement();
    
    for(String files in FilesID.get(key)){
      if(files==file && ID.get(key).checkActive()){
        peer.add(ID.get(key));
      }

    }
}
return peer;

}



      


      public static void main( String  args[]){

        }

    }
