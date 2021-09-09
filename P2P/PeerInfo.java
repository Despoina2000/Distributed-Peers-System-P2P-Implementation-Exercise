@SuppressWarnings("all")
import org.apache.commons.io.FileUtils;
import java.awt.Desktop;
import java.io.*;

public class PeerInfo {

    // Peer information variables
    protected String username, password, address; // username and password of each peer
    protected int port;
    protected int count_downloads, count_failures = 0; // total downloads and failures
    protected int TOKEN_ID;
    private List<PeerInfo> listPeer;// a list with peers that are connected with the peer
    private List<String> shared_directory;
    private List <PeerInfo> detailsPeer; // list with peers that have the file
 // total downloads and failures

    //Constructor
    public PeerInfo(String username, String password,String shared_directory,String address){
        this.username = username;
        this.password = password;
        this.shared_directory = shared_directory;
        this.address = address;
    }


    public PeerInfo(String address, int port, String username,int count_downloads,int count_failures){
        this.address = address;
        this.port = port;
        this.username = username;
        this.count_downloads = count_downloads;
        this.count_failures = count_failures;
    }

    public PeerInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PeerInfo() {
        username = password = null;
    }

    //GETTERS
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getShared_directory() {
        return shared_directory;
    }

    public String getAddress() {
        return address;
    }



    public int getCount_downloads() {
        return count_downloads;
    }

    public int getCount_failures() {
        return count_failures;
    }

    public int getTOKEN_ID() {
        return TOKEN_ID;
    }

    //SETTERS


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShared_directory(List<String> shared_directory) {
        this.shared_directory = shared_directory;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setCount_downloads(int count_downloads) {
        this.count_downloads = count_downloads;
    }

    public void setCount_failures(int count_failures) {
        this.count_failures = count_failures;
    }

    public void setTOKEN_ID(int TOKEN_ID) {
        this.TOKEN_ID = TOKEN_ID;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //functions for our lists
    void addPeer(PeerInfo newPeer) {  //add connected peers                            
        detailsPeer.add(newPeer);                      
    }
    void addFile(String file){
            shared_directory.add(file);
    }
    boolean haveFile(String file){//searching if we have the file
        for(files in shared_directory){
            if(file==files) return true;
        }
        return false;
    }
    //list
    List<String> list(){
        return reply_list();
    }

    //detals
    List <Peer> details(String file){ 
        //List <Peer> detailsPeer; //in case
        
        if (reply_details(file)!= empty){
            for (peer in mainTracker.reply_details(file) ){//the list with the trackers' peers
             if (peer.haveFile()) {// checking if it has the file
                 detailsPeer.addPeer(peer); //if we want to have a connection
                 } 
        } 
     }
         else {
                 println("ERROR");
                 
                 }
        
      return detailsPeer;
     }
     //check Active
     boolean checkActive(){
        if(share_directory!=empty){//or using the listPeer (listPeer!=empty)
           return true;
        } 
        return false;}
    //downloads
    boolean simpleDownload(String file){
        long min=0;//min long
        Peer best_peer;
       {for (peer in details(file)){
           long start = System.currentTimeMillis();
           peer.checkActive();
           long end = System.currentTimeMillis();
           long elapsedTime = end - start; //find time
           v= elapsedTime*(0.9^peer.count_downloads*1.2^peer.count_failures);//calculate the value
           if(min>v){ min=v; best_peer=peer;} //trying to find best peer
       if(min!=0){
           best_peer.notify(1);//we are inform the tracker
           }
           else{best_peer.notify(0); break;} }
          while (min!=0 ||detals(file)!=empty);//this is wrong but I am waiting to know about hashlist
          if(min!=0){best_peer.addFile(file);return 1;}//we add the file in share_directory 
          else return 0;
       }

       //notify
       boolean notify(boolean value){
        return value;
    }

    //  INCREMENTS
    public void incDownloads(){
        count_downloads++;
    }

    public void incFailures(){
        count_failures++;
    }

    public boolean doesPasswordEqual(String password) {
        return this.password.equals(password);
    }
}
