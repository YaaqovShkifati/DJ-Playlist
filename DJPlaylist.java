/**
 * @Author: Yaaqov Shkifati
 * @Since: 10/01/2020
 * @verison 1.0
 */
package djplaylist;

import java.util.*;
import java.io.*;

/**
 * The DJPlaylist class, is designed to create a playList of songs that would
 * insert the data into a binary tree. The PlayList would obtain; the song
 * title, average plays for each song, the artist, and the average of plays for
 * each artist.
 */
public final class DJPlaylist extends BinarySearchTree {

    private String[] weekFiles;
    private BinarySearchTree songTree;
    private ArrayList<String> storeData;
    private ArrayList<String> songTitle;
    private ArrayList<String> artistName;
    private ArrayList<String> Streams;
   
 /**
 * The DJPlaylist constructor initializes the data.
 * @throws IOException
 */
    public DJPlaylist() throws IOException {

        weekFiles = new String[]{"SongRec1.csv", "SongRec2.csv", "SongRec3.csv", "SongRec4.csv"};
        storeData = new ArrayList<>();      // Stores all the data into one Arraylist.
        songTitle = new ArrayList<>();     // Stores the song Titles.
        artistName = new ArrayList<>();    // Stores the Atrist Name.
        Streams = new ArrayList<>();       // Stores the songs streams.
        songTree = new BinarySearchTree(); // A binary search tree data stucture.

        for (int i = 0; i < weekFiles.length; i++) {
            readFile(weekFiles[i]);
        }

    }//End of DJPlaylist constructor.
    
     /**
     * The readFile method reads the csv files and stores into an array list.
     * @param fileName csv file.
     * @throws IOException
     */

    public void readFile(String fileName) throws IOException {

        try {

            Scanner input = new Scanner(new File(fileName), "UTF-8");

            while (input.hasNextLine()) {

                String read = input.nextLine();

                storeData.add(read);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//End of readFile.
    
    
  /**
    * The storeData method splits the data and stores it into the its proper
    * array lists.
    */
    public void storeData() {

        String[] tokens;

        for (int i = 0; i < storeData.size(); i++) {

            tokens = storeData.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            int j = 0;
            while (j < tokens.length) {
                songTitle.add(tokens[j]);
                j++;
                artistName.add(tokens[j]);
                j++;
                Streams.add(tokens[j]);
                j++;
            }

        }

    }//End of storeData.
    
     /**
     * The Songlist method inserts the data into a binary tree.
     * @throws IOException 
     */
    public void SongList() throws IOException{

        for (int i = 0; i < songTitle.size(); i++) {
            songTree.insert(songTitle.get(i), artistName.get(i),Streams.get(i));
           
        }
    }//End of SongList.

   /**
     * The print method prints the output to a text file in preOrder traversal. 
     * @throws IOException 
     */
    public void print() throws IOException {
        
        songTree.preOrder(songTree.root);
       
       songTree.subSet("Beautiful Crazy ", "Break My Heart");
       
    }//End print.

    public static void main(String[] args) throws IOException {

        DJPlaylist playList = new DJPlaylist();

        playList.storeData();
        playList.SongList();
        
        playList.print();
    
    }//End of Main.

}//End DJPlaylist.
