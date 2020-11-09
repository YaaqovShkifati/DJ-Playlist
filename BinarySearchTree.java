package djplaylist;

import java.io.IOException;
import java.io.PrintStream;

public class BinarySearchTree {

    public class Song {

        private String songName, artistName, streams;
        private int avgArtist, avgStreams, totalPlay, reapSong, totalArt, reapSong2;
        private Song left, right;

        public Song(String songName, String artistName, String Streams)  {
            
            
            this.songName = songName;
            this.artistName = artistName;
            this.streams = Streams;
            avgArtist = 0;
            avgStreams = 0;
            totalPlay = 0;
            totalArt = 0;
            reapSong = 1;
            reapSong2 = 1;
        }

        public void avgStreamsCount(int songPlays) {

            totalPlay += songPlays;
            reapSong++;

            avgStreams = (totalPlay + Integer.parseInt(streams)) / reapSong;

        }

        public void averageArtist(Song Art) {
            totalArt += Integer.parseInt(Art.streams);
            reapSong2++;
            avgArtist = (totalArt + Integer.parseInt(streams)) / reapSong2;
        }

        @Override
        public String toString() {

            String outPut1 = "Song Title: " + this.songName + " \n" + "Average Song Plays: " + avgStreams + "\n"
                    + "Artist: " + this.artistName + "\n" + "Average Artist Plays: " + streams + "\n"
                    + "-----------------------------";

            String outPut2 = "Song Title: " + this.songName + " \n" + "Average Song Plays: " + streams + "\n"
                    + "Artist: " + this.artistName + "\n" + "Average Artist Plays: " + avgArtist + "\n"
                    + "-----------------------------";
            String outPut3 = "Song Title: " + this.songName + " \n" + "Average Song Plays: " + streams + "\n"
                    + "Artist: " + this.artistName + "\n" + "Average Artist Plays: " + streams + "\n"
                    + "-----------------------------";

            if (avgStreams != 0) {
                return outPut1;
            }
            else if (avgArtist != 0) {
                return outPut2;
            } else {
                return outPut3;
            }

        }

    }

    Song root;
     public PrintStream output;
     public PrintStream output2;

    public BinarySearchTree() throws IOException{
        root = null;
          output= new PrintStream("DjPlaylist.txt");
          output2= new PrintStream("SubSet.txt");
    }

    public void insert(String songName, String artistName, String Streams) throws IOException {

        Song songTitle = new Song(songName, artistName, Streams);

        if (root == null) {
            root = songTitle;
        } else {
            Song current = root;
            Song perant;

            while (true) {
                perant = current;

                if (songName.equalsIgnoreCase(current.songName)) {
                    current.avgStreamsCount(Integer.parseInt(Streams));

                    return;
                }
                if (artistName.equalsIgnoreCase(current.artistName)) {
                    current.averageArtist(songTitle);
                    return;
                } else {
                    if (songName.compareToIgnoreCase(current.songName) < 0) {

                        current = current.left;

                        if (current == null) {
                            perant.left = songTitle;
                            return;
                        }

                    } else {
                        current = current.right;

                        if (current == null) {
                            perant.right = songTitle;
                            return;
                        }
                    }
                }

            }
        }
    }

 
    public Song subSet(Song root, String song1, String song2) throws IOException {

        if (root == null) {
            return root;
        }

        Song current = root;
        if (song1.compareToIgnoreCase(song2) < 0) {
            subSet(current.left, song1, song2);
        }
        if ((song1.compareToIgnoreCase(song2) <= 0) && (song1.compareToIgnoreCase(song2) >= 0)) {
            output2.println(current);
        }
        if (song1.compareToIgnoreCase(song2) > 0) {
            subSet(current.right, song1, song2);
        }
        return root;
    }
     
    public void subSet(String song1, String song2) throws IOException {
        subSet(root, song1, song2);
    }

    public void preOrder(Song current) throws IOException {

        if (current != null) {

            preOrder(current.left);

           output.println(current);

            preOrder(current.right);

        }
    }

}
