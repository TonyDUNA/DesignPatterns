package creationalPatterns.Singelton;

/*Одиночка (Singleton) гарантирует, что у класса есть только один
экземпляр, и предоставляет к нему глобальную точку доступа.
Преимущества:
● Объект класса в единственном экземпляре
● Глобальная видимость
● Ленивая инициализация

Недостатки:
● Нарушает принцип единой ответственности (SRP) - Singleton занимается также контролем кол-ва экз-ров
● Маскирует плохой дизайн
● Проблема тестирования
*/

// вариант первый - не потокобезопасный, но для однопоточных приложений - вполне
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    } //  приватный конструктор, чтобы нельзя было создать через него экз-р данного класса

    // метод getInstance, возвращающий новый экз-р класса, если ещё нет его, либо сущ экз-р, если он есть
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// вариант второй - потокобезопасный, но медленный. Добавляется synchronized, которая полезна только первый раз, потом замедляет

class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {
    }

    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

// вариант третий - классический. Добавляется volatile в поле instance, чтобы изменения в нём были видны в неск потоках

class SingletonClassic {
    private static volatile SingletonClassic instance;

    private SingletonClassic() {
    }

    // добавляется вложенный блок synchronized
    public static SingletonClassic getInstance() {
        SingletonClassic localInstance = instance; // экземпляр - в локальную переменную
        if (localInstance == null) {
            synchronized (SingletonClassic.class) { // если localInstance=null, то заходим в блок synch, и снова записываем
                // значение instance в localInstance, так как за это время другой поток мог создать экз-р класса Singleton
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SingletonClassic(); // создаем новый экземпляр класса
                }
            }
        }
        return localInstance; // иначе возвращаем экземпляр сущ-го экз-ра
    }
}

