package creationalPatterns.fabric;

/* Фабрика — это общая концепция проектирования функций, методов и
классов, когда какая-то одна часть программы отвечает за создание
других частей программы.*/

/*Простая фабрика — объект для создания других объектов.
        Обычно это класс, в котором есть один метод с условным оператором,
        выбирающим создаваемый объект. У простой фабрики, как правило, нет подклассов*/

//1. класс User с потомками
 class User {}

class Admin extends User{}

class Manager extends User{}

class Customer extends User{}

//2. класс enam перечисляет типы пользователей
enum UserType {
    MANAGER, CUSTOMER, ADMIN
}

// 3.простая фабрика - объект для создания других объектов
public class SimpleFabric {
//    в нем метод с условным оператором  switch case, выбирающим создаваемый объект:
    public User createUser(UserType type) {
        switch (type) {
            case ADMIN:
                return new Admin();
            case MANAGER:
                return new Manager();
            case CUSTOMER:
                return new Customer();
            default:
                throw new UnsupportedOperationException();
        }
    }
}