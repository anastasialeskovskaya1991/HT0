package model;

import java.util.concurrent.TimeUnit;

public class Song {

    private String nameSong;
    private String time;
    private String path;

    public Song(String nameSong, String time, String path) {
        this.nameSong = nameSong;
//        double tempTime = Double.parseDouble(time);
//        int result = ((int)tempTime  % 3600) / 60;
  //      String.valueOf(result);
        this.time = time;
        this.path = path;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Song{" +
                "nameSong='" + nameSong + '\'' +
                ", time='" + time + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

