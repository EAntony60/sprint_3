package utils;

import model.Courier;

import static utils.Utils.getRandomString;

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
    
    public static Object courierGenerator(String login, String password, String firstName) {
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
}
