package creationalPatterns.fabric.builder;

/*Строитель (Builder) — это паттерн проектирования, который позволяет
поэтапно создавать сложные объекты с помощью четко определенной
последовательности действий.
Строитель (Builder) позволяет сделать инициализацию структур данных
более наглядной, гибкой при этом сохраняя такое полезное их свойство
как неизменяемость (immutability).

паттерн билдер реализуется за счет создания внутреннего статич класса Builder,
 в котором конструктор+параметр+метод возвращает готовый объект

 https://vertex-academy.com/tutorials/ru/pattern-builder-java/

 */

public class Builder {
}

//
class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;

    // геттеры
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    // 1. статический метод создания билдера, инициализирует объект вложенного класса Builder:
    public static Builder builder() {
        return new Builder();
    }

    // 2.в самом вложенном классе билдер -  конструктор и методы для заполнения параметров Person. На выходе всегда вызывают класс Builder.
    public static final class Builder {
        private Person person;
        private Builder() { person = new Person(); } // конструктор

        // методы для заполнения параметров Person. На выходе всегда вызывают класс Builder.
        public Builder firstName(String firstName){
            person.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName){
            person.middleName = middleName;
            return this;
        }

        public Builder lastName(String lastName){
            person.lastName = lastName;
            return this;
        }

        public Builder age(int age){
            person.age = age;
            return this;
        }

        // 3.метод, который возвращает готовый объект
        public Person build(){
            return person;
        }


    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

class DemoBuilder {
    public static void main(String[] args) {
        Person person = Person.builder()
                .firstName("Иван")
                .middleName("Иванович")
                .lastName("Иванов")
                .age(30)
                .build();
        System.out.println(person.toString());
    }
}

