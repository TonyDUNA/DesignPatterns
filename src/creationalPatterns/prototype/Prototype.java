package creationalPatterns.prototype;

/*Прототип — это порождающий паттерн, позволяющий создавать новые
объекты на основе имеющегося экземпляра (прототипа).
На практике применяется длā клонирования существующих объектов.
В Java требуется имплементация интерфейса Cloneable.*/

public class Prototype {
}

// класс имплементирует интерфейс Cloneable
class Person implements Cloneable {
    private String name;
    private int age;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // метод позволяющий клонировать объект
    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }
}

// Проверка - в результате которой видим создание экз-ра объекта и его клонирование - ок.
// Но с более сложными объектами clone() не работает - например прототипирование объекта с разными значениями
// какого-то поля - например passport

 class DemoPrototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person1 = new Person();
        person1.setName("Иван");
        person1.setAge(30);
        Person person2 = person1.clone();
        System.out.println(person1.toString());
        System.out.println(person2.toString());
    }
}

// вариант более полный - добавляем классу Passport интерфейс Cloneable и метод clone().
// также в методе clone() класса Person вызываем метод clone() класса Password и записываем результат в поле password:
class Person1 implements Cloneable {
    private String name;
    private int age;
    private Passport passport;
    // getters and setters


    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", passport=" + passport +
                '}';
    }
    @Override
    protected Person1 clone() throws CloneNotSupportedException {
        Person1 person1 = (Person1) super.clone();
        person1.passport = passport.clone();
        return person1;
    }
}

class Passport implements Cloneable {
    private String series;
    private String number;
    // getters and setters


    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    protected Passport clone() throws CloneNotSupportedException {
        return (Passport) super.clone();
    }
}

//проверка работы программы
// В результате, два объекта с разными ссылками на номера паспортов, соответственно изменение номера паспорта1 не
// изменит номер паспорта2:
//Person{name='Иван', age=30, passport=creationalPatterns.fabric.prototype.Passport@4f3f5b24}
//Person{name='Иван', age=30, passport=creationalPatterns.fabric.prototype.Passport@15aeb7ab}
class DemoPrototypeFull {
    public static void main(String[] args) throws CloneNotSupportedException {
        Passport passport = new Passport();
        passport.setSeries("1234");
        passport.setNumber("123456");
        Person1 person1 = new Person1();
        person1.setName("Иван");
        person1.setAge(30);
        person1.setPassport(passport);
        Person1 person2 = person1.clone();
        System.out.println(person1.toString());
        System.out.println(person2.toString());
    }
}


// Для сложных случаев - 3 вариант - самостоятельная реализация clone():

class Person3 implements Cloneable {
    private String name;
    private int age;
    public Person3() {}
    public Person3(Person3 person) {
        if (person != null) {
            this.name = person.name;
            this.age = person.age;
        }
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected Person3 clone() throws CloneNotSupportedException {
        return new Person3(this);
    }
}