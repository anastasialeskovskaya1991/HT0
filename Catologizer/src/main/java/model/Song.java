package model;

public class Song {

    private String name;
    private String time;
    private String path;

    public Song(String name, String time, String path) {
        this.name = (name == null) ? "N/A" : name;
//        double tempTime = Double.parseDouble(time);
//        int result = ((int)tempTime  % 3600) / 60;
        //      String.valueOf(result);
        this.time = time;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        try {
            int timeInMillis = (int) Double.parseDouble(time);
            int timeInSec = timeInMillis / 1000;
            int mins = timeInSec / 60;
            int secs = timeInSec % 60;
            return mins + ":" + secs;
        } catch (Exception e) {
            return time;
        }
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
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

