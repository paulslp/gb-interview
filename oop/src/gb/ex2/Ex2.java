package gb.ex2;


/*
interface Moveable {
void move();
}
interface Stopable {
void stop();
}
abstract class Car {
public Engine engine;
private String color;
private String name;
protected void start() {
System.out.println("Car starting");
}
abstract void open();
public Engine getEngine() {
return engine;
}
public void setEngine(Engine engine) {
this.engine = engine;
}
public String getColor() {
return color;
}
public void setColor(String color) {
this.color = color;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
}
class LightWeightCar extends Car implements Moveable {
@Override
void open() {
System.out.println("Car is open");
}
@Override
public void move() {
System.out.println("Car is moving");
}
}
class Lorry extends Car, Moveable, Stopable {
public void move(){
System.out.println("Car is moving");
}
public void stop(){
System.out.println("Car is stop");
}
}*/

public class Ex2 {
    class Inner {
        static public void show() {
            System.out.println("Метод внутреннего класса");
        }
    }

    public static void main(String[] args) {
        System.out.println("1. В классе Car поле engine лучше объявить через интерфейс IEngine");
        System.out.println("2. В классе Lorry должен быть один родитель (класс Car) и реализация интерфейсов Moveable, Stopable с использованием implements, а не extends");
    }
}