package model.exceptions;

public class IlluminanceTooMuchException extends Exception {
    public  IlluminanceTooMuchException() {
        System.out.println("Excess of light in the room. Reduce the amount of light added");
        }
}
