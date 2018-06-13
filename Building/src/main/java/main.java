import model.*;


public class Main {
    public static void main(String[] args)  {
        System.out.println("hello");

        Building building = new Building("School");
        building.addRoom("Room1",100,3);
        building.addRoom("Room2",500,1);

        building.getRoom(0).addBulb(new Bulb(250));

        //Furniture sub = new Furniture("Мягкое кресло", 20);
        building.addRoom("Room3",500, 5);

        //генерирование ошибки SpaceUsageTooMuchException
        building.getRoom(0).addFurniture(new FixedSizeFurniture("Chair",200));

        building.getRoom(0).addFurniture(new FixedSizeFurniture("Chair",2));

        //System.out.println(building.getRoom(2));
        building.getRoom(2).addBulb(new Bulb(50));
        building.getRoom(0).addFurniture(new FixedSizeFurniture("Beautiful sofa", 24));
        building.getRoom(2).addBulb(new Bulb(5));
        building.addRoom("Room4", 56,4 );
        building.getRoom(3).addFurniture(new DynamicSizeFurniture("Sofa",4,5));
        building.getRoom(3).addBulb(new Bulb(150));

        building.describe();


        }
}
