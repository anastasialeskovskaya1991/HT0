package model;

import java.util.ArrayList;

public class Artist {
    private String name;
    private ArrayList<Album> albums = new ArrayList<Album>();

    public Artist(String name) {
        this.name = (name == null) ? "N/A" : name;
    }

    public Artist(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
        System.out.println("Added new album:" + album.getName() + " to artist:" + this.name );
    }

    @Override
    public String toString() {
        return "model.Artist{" +
                "name='" + name + '\'' +
                ", albums=" + albums +
                '}';
    }

}
