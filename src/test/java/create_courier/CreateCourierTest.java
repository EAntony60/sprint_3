package create_courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.DataGenerator;

import static client.CourierApi.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static utils.Utils.getRandomString;

public class CreateCourierTest {

    private Courier courier;
    private int id;

    private RequestSpecification spec;

    @Before
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                .setContentType("application/json")
                .build();
    }

    @After
    public void tearDown() {
        id = signInCourier(courier, spec).then().extract().path("id");
        deleteCourier(id, spec);
    }

    @Test
    @DisplayName("Создание курьера с полными и валидными данными")
    public void successCreateNewCourier() {
        courier = DataGenerator.courierGenerator(7, 7, 7);
        Response createCourierResponse = createCourier(courier, spec);
        createCourierResponse.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание не уникального курьера")
    public void createNotUniqueCourier() {
        courier = DataGenerator.courierGenerator(7, 7, 7);
        createCourier(courier, spec);
        Courier notUnique = Courier.builder()
                .login(courier.getLogin())
                .password(courier.getPassword())
                .firstName(courier.getFirstName())
                .build();
        Response createCourierResponse = createCourier(notUnique, spec);
        createCourierResponse.then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}
