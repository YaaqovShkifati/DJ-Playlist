/**
 * @Author: Yaaqov Shkifati
 * @Since: 10/01/2020
 * @verison 1.0
 */
package djplaylist;

import java.util.*;
import java.io.*;

public final class DJPlaylist extends BinarySearchTree {

    private String[] weekFiles;
    private BinarySearchTree songTree;
    private ArrayList<String> storeData;
    private ArrayList<String> songTitle;
    private ArrayList<String> artistName;
    private ArrayList<String> Streams;
   

    public DJPlaylist() throws IOException {

        weekFiles = new String[]{"SongRec1.csv", "SongRec2.csv", "SongRec3.csv", "SongRec4.csv"};
        storeData = new ArrayList<>();
        songTitle = new ArrayList<>();
        artistName = new ArrayList<>();
        Streams = new ArrayList<>();
        songTree = new BinarySearchTree();

        for (int i = 0; i < weekFiles.length; i++) {
            readFile(weekFiles[i]);
        }

    }

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
    }

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

    }

    public void SongList() throws IOException{

        for (int i = 0; i < songTitle.size(); i++) {
            songTree.insert(songTitle.get(i), artistName.get(i),Streams.get(i));
           
        }
    }


    public void print() throws IOException {
        
        songTree.preOrder(songTree.root);
       
       songTree.subSet("Beautiful Crazy ", "Break My Heart");
       
    }

    public static void main(String[] args) throws IOException {

        DJPlaylist playList = new DJPlaylist();

        playList.storeData();
        playList.SongList();
        
        playList.print();
    }

}
