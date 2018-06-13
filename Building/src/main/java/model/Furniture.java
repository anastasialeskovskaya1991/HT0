package model;

public abstract class Furniture {

    private String name;

    public Furniture(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double getSpace();

//    @Override
//    public String toString() {
//       // return  name  + " (Space " + (commonSpace == 0 ? "from " + fromSpace + " till " + tillSpace + "m^2)" : commonSpace + " m^2)") ;
//    }
}
