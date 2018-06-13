package model;

import java.util.ArrayList;

public class Album {
    private String nameAlbum = "неизвестный";
    private ArrayList<Song> songs = new ArrayList<Song>();

    public Album(String name) {
        this.nameAlbum = name;
        }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public  void addSongs(Song  song){
        songs.add(song);
        }

    @Override
    public String toString() {
        return "Album{" +
                "nameAlbum='" + nameAlbum + '\'' +
                ", songs=" + songs +
                '}';
    }
}
