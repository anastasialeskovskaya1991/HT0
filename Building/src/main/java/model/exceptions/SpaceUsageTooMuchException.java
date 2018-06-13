package model.exceptions;

public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException(){
        System.out.println("Error placing the item in the room");
    }
}

