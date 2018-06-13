package model;

import model.exceptions.IlluminanceTooMuchException;
import model.exceptions.SpaceUsageTooMuchException;
import java.util.ArrayList;



public class Room {

    private  String name;
    private  double roomSpace;
    private double freeSpace;
    private Double freeSpacePercent;
    private int windows;
    private int roomLum;


    private static final int luminosity = 700;
    private static final int minLuminosity = 300;
    private static final int maxLuminosity = 4000;

    private ArrayList<Furniture> furnitures;
    private  ArrayList<Bulb> bulbs;

    public Room(String name, double space, int qtyOfWindows) throws IlluminanceTooMuchException{
        roomLum = qtyOfWindows * luminosity;

        if ((minLuminosity < roomLum) && (roomLum < maxLuminosity)) {
            this.name = name;
            this.roomSpace = space;
            this.windows = qtyOfWindows;
        } else throw new IlluminanceTooMuchException();
    }
    public String getName(){
        return name;
    }

    public double getRoomSpace() {
        return roomSpace;
    }
    public int getRoomLum() {
        return roomLum;
    }

    public int getWindows() {
        return windows;
    }

    public ArrayList<Bulb> getBulbs() {
        return bulbs;
    }

    public void setFreeSpaces(Double spaceFurn){
        freeSpace = roomSpace - spaceFurn;
        freeSpacePercent = (freeSpace / roomSpace * 100);
       // System.out.println("свободная площадь " + freeSpace);
    }

    //метод, контролирующий добавление предметов в помещение
    public void addFurniture(Furniture furniture) {

        double commonSpaceofFurnit = 0;
        try {
            if (furnitures == null) {
                furnitures = new ArrayList<Furniture>();
            } else {
                for (Furniture furn : furnitures) {
                    commonSpaceofFurnit += furn.getSpace();
                    }
                }

            commonSpaceofFurnit += furniture.getSpace();

            if((commonSpaceofFurnit) < roomSpace * 0.7) {
                furnitures.add(furniture);
                setFreeSpaces(furniture.getSpace());
            } else {
                throw new SpaceUsageTooMuchException();
            }
        } catch (SpaceUsageTooMuchException e) {
            System.out.println("Item /" + furniture.getName() + "/ in the room/" + name +  "/ can not be to add");
            System.out.println("Space of added items can't be > 70% from common space ");
        }
    }

    public void addBulb(Bulb bulb) {
        try {
            if(bulbs == null) {
                bulbs = new ArrayList<Bulb>();
            }
            if((roomLum + bulb.getPower()) <= maxLuminosity) {
                roomLum += bulb.getPower();
                bulbs.add(bulb);
                System.out.println("Bulb in " + "room " + name +  "is added");
            } else {
                throw new IlluminanceTooMuchException();
            }
        }catch (IlluminanceTooMuchException e) {
            System.out.println("Impossibility to add a light bulb. Exceeding the norm for the amount of light in the room.");
        }
    }

    @Override
    public String toString() {
        return  '\t' + name + '\n' + "\t\t" + "Luminosity = " + roomLum + " (" +(windows==1? windows +" window " +
                luminosity + " lk " :  windows + " windows of " + luminosity + " lk ") +
                (bulbs == null ? ")" : ", bulbs " + bulbs.toString().replaceAll("^\\[|\\]$", "") +
                        ") ") + '\n' + "\t\tSpace = " + roomSpace + " m^2" + " ("+
                (furnitures == null ? "Free 100% )" : "guaranteed free : " + freeSpace + " m^2 " +" or " +
                        String.format("%.2f ", freeSpacePercent)+ "% space)")+ '\n' +
                (furnitures == null ? "\t\tno furniture" : "\t\tFurniture : \n" + "\t\t" +
                        furnitures.toString().replaceAll("^\\[|\\]$", "")) +
                '\n';
        }
}
