package model;

public class DynamicSizeFurniture extends Furniture {

    private double minSpace;
    private double maxSpace;

    public DynamicSizeFurniture(String name, double minSpace, double maxSpace ) {
        super(name);
        this.minSpace = minSpace;
        this.maxSpace = maxSpace;
    }

    public double getMinSpace() {
        return minSpace;
    }

    public void setMinSpace(double minSpace) {
        this.minSpace = minSpace;
    }

    public double getMaxSpace() {
        return maxSpace;
    }

    public void setMaxSpace(double maxSpace) {
        this.maxSpace = maxSpace;
    }

    @Override
    public double getSpace() {
        return maxSpace;
    }

    @Override
    public String toString() {
        return super.getName() + " (Space from " + minSpace + " m^2 till " + maxSpace + " m^2)";
    }
}
