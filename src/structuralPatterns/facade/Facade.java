package structuralPatterns.facade;

/*Фасад — это структурный паттерн проектирования, который
предоставляет простой интерфейс к сложной системе классов, библиотеке
или фреймворку.
Основная задача: упрощение использования сложной системы.

Достоинство:
● Изолирует клиентов от компонентов сложной подсистемы.
Недостаток:
● Фасад может превратиться в антипаттерн божественный объект.*/

public class Facade {
}

// Исх данные: интерфейс формы и его реализация в различных фигурах
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing circle...");
    }
}
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing rectangle...");
    }
}
class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing triangle...");
    }
}

// простой фасад для отрисовки фигур: свдим взаимодействие со сложной структурой классов к одному методу draw(),
// которому на вход нужно передать название фигуры
class ShapeFacade {
    private final Shape circle;
    private final Shape rectangle;
    private final Shape triangle;
    public ShapeFacade() {
        circle = new Circle();
        rectangle = new Rectangle();
        triangle = new Triangle();
    }
    public void draw(String shape) {
        switch (shape) {
            case "circle":
                circle.draw();
                break;
            case "rectangle":
                rectangle.draw();
                break;
            case "triangle":
                triangle.draw();
                break;
            default:
                System.out.println("Unknown shape");
        }
    }
}

// клиентский код:
class DemoFacade {
    public static void main(String[] args) {
        ShapeFacade shapeFacade = new ShapeFacade();
        shapeFacade.draw("circle");
    }
}



