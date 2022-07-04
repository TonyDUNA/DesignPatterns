package creationalPatterns.fabric;

// фабричный метод - порождающий шаблон проектирования, предоставляющий подклассам интерфейсдля создания экземпляра некоторого класса.
// то есть делегирует создание объектов наследникам родительского класса. Это позволяетне манипулировать специфичными классами, аманипулировать
// объектами на более абстрактном уровне

/*Если вы видите иерархию классов продуктов и абстрактный создающий
        метод, который переопределяется в подклассах, то перед вами паттерн
        “Фабричный метод”*/

public class FabricMethod {
}

// 1. Есть класс Employee, от которого наследуются работники
class Employee {
    public void doSomething() {
        System.out.println("nothing...");
    }
}

class Programmer extends Employee {
    @Override
    public void doSomething() {
        System.out.println("coding...");
    }
}

class SalesManager extends Employee {
    @Override
    public void doSomething() {
        System.out.println("sale...");
    }
}
// 2.фабричный метод: есть класс Department с абстрактным методом, который переопределяется в подклассах и создает новых
// работников:
abstract class Department {
    public abstract Employee createEmployee();
}

class ITDepartment extends Department {
    @Override
    public Employee createEmployee() {
        return new Programmer();
    }
}

class SalesDepartment extends Department{
    @Override
    public Employee createEmployee() {
        return new SalesManager();
    }
}

// проерка работы
class Demo {
    public static void main(String[] args) {
        ITDepartment itDepartment = new ITDepartment();
        Employee programmer = itDepartment.createEmployee();
        programmer.doSomething();
        SalesDepartment salesDepartment = new SalesDepartment();
        Employee salesManager = salesDepartment.createEmployee();
        salesManager.doSomething();
    }
}

