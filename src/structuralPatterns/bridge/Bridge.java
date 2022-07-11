package structuralPatterns.bridge;

/*Мост — это структурный паттерн проектирования, который разделяет один
        или несколько классов на две отдельные иерархии — абстракция и
        реализация, позволяя изменять их независимо друг от друга.
        Преимущества:
        ● Улучшает масштабируемость кода
        ● Уменьшает связанность классов*/

public class Bridge {
}

// абстракция формы
abstract class Shape {
    // 3)после реализации интерфейса Color добавляем это поле и конструктор и подправляем реализацию подкласса(
    // конструктор матчинг супер и fillColor() в метод draw():
    protected final Color color;
    protected Shape(Color color) {
        this.color = color;
    }
    public abstract void draw();
}


// два наследника - прямоугольник и круг
class Rectangle extends Shape {
    protected Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
        color.fillColor();
    }
}

class Circle extends Shape {
    protected Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle");
        color.fillColor();
    }
}

// в ходе развития приложения, нужно добавить цвет фигуры, от которого зависит функционал метода draw()
// чтобы не добавлять классы с разными цветами, выделяем цвет в отдельный интерфейс
interface Color {
    void fillColor();
}

class Blue implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling in blue color");
    }
}
class Green implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling in green color");
    }
}
class Red implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling in red color");
    }
}


