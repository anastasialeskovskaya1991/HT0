package model;

public class FixedSizeFurniture extends Furniture {
    private double commonSpace;

    public FixedSizeFurniture(String name, double commonSpace) {
        super(name);
        this.commonSpace = commonSpace;
    }

    public double getCommonSpace() {
        return commonSpace;
    }

    public void setCommonSpace(double commonSpace) {
        this.commonSpace = commonSpace;
    }

    @Override
    public double getSpace() {
        return commonSpace;
    }

    @Override
    public String toString() {
       return  super.getName()  + " (Space " +  commonSpace + " m^2)" ;
    }
}
