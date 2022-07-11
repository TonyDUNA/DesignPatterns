package structuralPatterns.adapter;


/*Адаптер - преобразует интерфейс одного класса в интерфейс другого.
Используется для того, чтобы существующие классы работали с другими
без изменения их исходного кода.*/


public class Adapter {

}

//интерфейс закругления
interface Roundable {
    double getRadius();
}

// круглый колышек
class RoundPeg implements Roundable {
    private final double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}

//  круглое отверстие
class RoundHole implements Roundable {
    private final double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    // метод проверки - помещается ли в круглое отверстие колышек
    public boolean insert(Roundable peg) {
        return this.getRadius() >= peg.getRadius();
    }
}

//допустим появляется новый класс квадратных колышек, несовместимый с существующими круглыми отверстиями:
class SquarePeg {
    private final double side;
    public SquarePeg(double side) {
        this.side = side;
    }
    public double getSide() {
        return side;
    }
    public double getArea() {
        return Math.pow(this.side, 2);
    }
}

// для решения - адаптер: наследуется от нового класса и реализует интерфейс старого.
// в нашем примере - адаптирует квадратные колышки к чему-то круглому

class SquarePegAdapter extends SquarePeg
        implements Roundable {
    public SquarePegAdapter(double side) {
        super(side);
    }
    @Override
    public double getRadius() {
        // радиус описанной окружности
        return getSide() * Math.sqrt(2)/2;
    }
}

// Проверка:
class Demo {
    public static void main(String[] args) {
        RoundHole roundHole = new RoundHole(10);
        RoundPeg roundPeg1 = new RoundPeg(11);
        RoundPeg roundPeg2 = new RoundPeg(6);
        SquarePegAdapter squarePegAdapter1 = new SquarePegAdapter(5);
        SquarePegAdapter squarePegAdapter2 = new SquarePegAdapter(15);
        System.out.println("roundPeg1 insert into roundHole - " + roundHole.insert(roundPeg1));
        System.out.println("roundPeg2 insert into roundHole - " + roundHole.insert(roundPeg2));
        System.out.println("squarePegAdapter1 insert into roundHole - " + roundHole.insert(squarePegAdapter1));
        System.out.println("squarePegAdapter2 insert into roundHole - " + roundHole.insert(squarePegAdapter2));
    }
}


