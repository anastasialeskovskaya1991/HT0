package model;

import model.exceptions.IlluminanceTooMuchException;

import java.util.ArrayList;

public class Building {
    private String name;
    private ArrayList<Room> rooms;

    public Building(String name){
        this.name = name;
        rooms = new ArrayList<Room>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    public Room getRoom(int index) {
        return rooms.get(index);
    }

    public void addRoom(String name, double space, int window){
        try {
            Room room = new Room(name, space, window);
            rooms.add(room);
        }catch (IlluminanceTooMuchException e){}
    }

    public void describe(){
        System.out.println(name + '\n' + rooms.toString().replaceAll("^\\[|\\]$", ""));
    }
}
