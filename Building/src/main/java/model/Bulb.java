package model;

public class Bulb  {
    private int power;

    public Bulb(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return power + " lk";
    }
}
