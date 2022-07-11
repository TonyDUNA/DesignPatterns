package structuralPatterns.proxy;

/*Заместитель — это объект, который выступает прослойкой между
        клиентом и реальным сервисным объектом.
        Возможности:
        ● Ленивая инициализация (виртуальный прокси)
        ● Защита доступа (защищающий прокси)
        ● Кеширование объектов (кэширующий прокси)
        ● Преобразование протоколов (удаленные прокси)
        ● Логирование запросов (логирующий прокси)*/

import java.util.Currency;
import java.util.HashMap;
import java.util.Random;

public class Proxy {
}

// Пример: курс валют в банке не меняется в теченение дня. Сервис запрашивает периодически курс валюты- запросы к сайту ЦБ
// Так как курс не меняется в течение дня, можно оптимизировать - избавиться от запросов с помощью proxy.
interface CurrencyRateService {
    double getDailyCurrencyRate(Currency currency);
}
// имитация- (заглушка) с данными о курсе
class CurrencyRateServiceImpl implements CurrencyRateService {
    @Override
    public double getDailyCurrencyRate(Currency currency) {
        Random rnd = new Random();
        double result = 100 * rnd.nextDouble();
        double scale = Math.pow(10, 2);
        return Math.round(result * scale) / scale;
    }
}

// Proxy имплементирующий сервисный интерфейс:
class CurrencyRateServiceProxy implements CurrencyRateService {
    private final CurrencyRateService currencyRateService; // ссылка на сервисный объект
    private final HashMap<Currency, Double> currencyRatesMap = new HashMap<>(); // hashMap с текущими курсами валют
    public CurrencyRateServiceProxy() {
        this.currencyRateService = new CurrencyRateServiceImpl(); // констр-р с инициализацией сервисного объекта
    }
    @Override
    public double getDailyCurrencyRate(Currency currency) { // если hashMap уже содержит ключ - данные о курсе валютной единицы
        // за сутки - вернуть, если нет - то запрос в ЦБ и добавление в hashMap значения
        if (currencyRatesMap.containsKey(currency)) {
            return currencyRatesMap.get(currency);
        }
        double dailyRate = currencyRateService.getDailyCurrencyRate(currency);
        currencyRatesMap.put(currency, dailyRate);
        return dailyRate;
    }
}

// клиентский код: при трех запросах курса евро, получаем одно значение, то есть отрабатывает proxy а не random()

class DemoProxy {
    private static final String EUR = "EUR";
    public static void main(String[] args) {
        CurrencyRateService currencyRateService = new CurrencyRateServiceProxy();
        Currency eur = Currency.getInstance(EUR);
        System.out.println(currencyRateService.getDailyCurrencyRate(eur));
        System.out.println(currencyRateService.getDailyCurrencyRate(eur));
        System.out.println(currencyRateService.getDailyCurrencyRate(eur));
    }
}
