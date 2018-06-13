package model;

import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Song> songs = new ArrayList<Song>();

    public Album(String name) {
        this.name = (name == null) ? "N/A" : name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }


    public void addSongs(Song song){
        songs.add(song);
        System.out.println("Added new song:" + song.getName() + " to album:" + this.name);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}
