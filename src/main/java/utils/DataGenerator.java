package utils;

import model.Courier;
import model.Order;

import static utils.Utils.*;

public class DataGenerator {

    //Генерация данных курьера
    public static Courier courierGenerator(int login, int password, int firstName) {
        Courier courier = Courier.builder().login(getRandomString(login)).password(getRandomString(password)).firstName(getRandomString(firstName)).build();
        return courier;
    }

    public static Courier courierGenerator(int login, int password, String firstName) {
        Courier courier = Courier.builder().login(getRandomString(login)).password(getRandomString(password)).firstName(firstName).build();
        return courier;
    }

    public static Courier courierGenerator(int login, String password, int firstName) {
        Courier courier = Courier.builder().login(getRandomString(login)).password(password).firstName(getRandomString(firstName)).build();
        return courier;
    }
    
    public static Courier courierGenerator(String login, int password, int firstName) {
        Courier courier = Courier.builder().login(login).password(getRandomString(password)).firstName(getRandomString(firstName)).build();
        return courier;
    }
    
    public static Courier courierGenerator(String login, String password, int firstName) {
        Courier courier = Courier.builder().login(login).password(password).firstName(getRandomString(firstName)).build();
        return courier;
    }
    
    public static Courier courierGenerator(String login, String password, String firstName) {
        Courier courier = Courier.builder().login(login).password(password).firstName(firstName).build();
        return courier;
    }

    public static Courier courierGenerator(int login, String password, String firstName) {
        Courier courier = Courier.builder().login(getRandomString(login)).password(password).firstName(firstName).build();
        return courier;
    }

    public static Courier courierGenerator(String login, int password, String firstName) {
        Courier courier = Courier.builder().login(login).password(getRandomString(password)).firstName(firstName).build();
        return courier;
    }

    //Генерация данных для заказа
    public static Order orderGenerator(String[] color, int firstName, int lastName, int address, int metroStation, String phone, int rentTime, int comment) {
        Order order = Order.builder().color(color).firstName(getRandomString(firstName)).lastName(getRandomString(lastName)).address(getRandomString(address)).metroStation(getRandomString(metroStation)).phone("+7 233 123 98 92").rentTime(getRandomNumber(0,rentTime)).deliveryDate(getRandomDate()).comment(getRandomString(comment)).build();
        return order;
    }
}
