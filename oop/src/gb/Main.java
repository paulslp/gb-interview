package gb;

import gb.begaviour.Figure;
import gb.builder.PersonBuilder;
import gb.domain.Person;
import gb.domain.figure.Circle;
import gb.domain.figure.Square;
import gb.domain.figure.Triangle;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("-------------Person info--------------------");
        Person person = new PersonBuilder().withFirstName("Кузнецов")
                .withLastName("Василий")
                .withMiddleName("Аркадьевич")
                .withAddress("Адрес")
                .withAge(45)
                .withCountry("Россия")
                .withGender("Мужской")
                .withPhone("232345")
                .build();
        System.out.println(person);
        System.out.println("-------------Figures--------------------");
        List<Figure> figures = List.of(new Circle(1), new Square(2), new Triangle(1, 2, 3));
        figures.forEach(figure -> System.out.println(figure.getClass() + ": perimeter =" + figure.getPerimeter()));
    }
}
