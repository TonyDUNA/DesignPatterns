package structuralPatterns.decorator;


/*Декоратор — это структурный паттерн, который позволяет добавлять
        объектам новое поведение на лету, помещая их в объекты-обёртки.
        Гибкая альтернатива подклассам для расширения функциональности.

        Примеры декораторов в стандартных библиотеках Java.
Подклассы InputStream, OutputStream, Reader, Writer, например GZIPInputStream:
public GZIPInputStream(InputStream in) throws IOException {
 this(in, 512);
}
Пример использования:
GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream("file.gzip"));*/

public class Decorator {
}

// интерфейс машины
interface Car {
    int getSpeed();
}

// простая машина с определенной скоростью
class SimpleCar implements Car {
    private int speed = 100;
    @Override
    public int getSpeed() {
        return speed;
    }
}

// декоратор - SportCar- любое авто дооборудуем в спорткар.
class SportCar implements Car {
    private final Car car;
    public SportCar(Car car){ // в конструктор приходит объект класса Car
        this.car = car;
    }
    @Override
    public int getSpeed() {
        return this.car.getSpeed() + 50; // и добавляем к скорости авто некоторое значение (то есть новый функционал)
    }
}

class DemoDecorator {
    public static void main(String[] args) {
        Car simpleCar = new SimpleCar();
        System.out.println("Simple car speed: " + simpleCar.getSpeed()); // скорость простого авто
        Car sportCar = new SportCar(simpleCar); // с помощью декоратора - делаем спорткар
        System.out.println("Sport car speed: " + sportCar.getSpeed()); // скорость бывшего простого авто
    }
}

