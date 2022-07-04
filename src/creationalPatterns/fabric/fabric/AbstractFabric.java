package creationalPatterns.fabric.fabric;


/*Абстрактная фабрика — порождающий шаблон проектирования, предоставляет интерфейс для создания семейств взаимосвязанных
 или взаимозависимых объектов, не специфицируя их конкретных классов.
Простыми словами - фабрика фабрик.
*/

public class AbstractFabric {
}

// 1. имеем интерфейс машины-пикапа
 interface Pickup {
    void description();
}

// две сущности имплементируют интерфейс пикап:
 class DodgePickup implements Pickup {
    @Override
    public void description() {
        System.out.println("Dodge Pickup");
    }
}
 class FordPickup implements Pickup {
    @Override
    public void description() {
        System.out.println("Ford Pickup");
    }
}
// 2. и аналогично интерфейс машины-suv
interface Suv {
    void description();
}

// две сущности имплементируют интерфейс внедорожников Suv:
 class DodgeSuv implements Suv {
    @Override
    public void description() {
        System.out.println("Dodge SUV");
    }
}
 class FordSuv implements Suv {
    @Override
    public void description() {
        System.out.println("Ford SUV");
    }
}

// реализация интерфейса абстрактной фабрики  CarsFactory с двумя методами - создание двух типов: внедорожники/пикапы:

interface CarsFactory {
    Suv createSuv();
    Pickup createPickup();
}

// классы Форд и Додж, реализующие данный интерфейс, с обоими типами Suv, Pickup
class DodgeFactory implements CarsFactory {
    @Override
    public Suv createSuv() {
        return new DodgeSuv();
    }
    @Override
    public Pickup createPickup() {
        return new DodgePickup();
    }
}


class FordFactory implements CarsFactory {
    @Override
    public Suv createSuv() {
        return new FordSuv();
    }
    @Override
    public Pickup createPickup() {
        return new FordPickup();
    }
}

// проверка реализации:
class DemoAbstractFabric {
    public static void main(String[] args) {
        CarsFactory carsFactory = new DodgeFactory(); // создали объект - фабрику Dodge, ссылка на интерф CarsFactory
        Pickup pickup = carsFactory.createPickup(); // ссылку на объект фабрика Dodge кладем в переменную типа Pickup, вызываем метод создания пикапа
        pickup.description(); // у пикапа вызвали метод description
    }
}
