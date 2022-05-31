package createorders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.DataGenerator;

import static client.OrderApi.cancelOrder;
import static client.OrderApi.createOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrdersTest {

    private final int expectStatusCode;
    private final String descriptionData;
    private final Order order;

    private int trackOrder;

    private RequestSpecification spec;

    public CreateOrdersTest(int expectStatusCode, String descriptionData, Order order) {
        this.expectStatusCode = expectStatusCode;
        this.descriptionData = descriptionData;
        this.order = order;
    }

    @Parameterized.Parameters(name = "test data: {0} {1}")
    public static Object[][] getNotValidData(){
        return new Object[][]{
                {SC_CREATED, "Только чёрный", DataGenerator.orderGenerator(new String[] {"BLACK"},7,7,15,1,"+7 233 123 98 92", 10, 24)},
                {SC_CREATED, "Только серый", DataGenerator.orderGenerator(new String[]{"GREY"},7,7,15,1,"+7 233 123 98 92", 10, 24)},
                {SC_CREATED, "Серый и черный", DataGenerator.orderGenerator(new String[]{"GREY", "BLACK"},7,7,15,1,"+7 233 123 98 92", 10, 24)},
                {SC_CREATED, "Цвет отсутствует", DataGenerator.orderGenerator(new String[]{},7,7,15,1,"+7 233 123 98 92", 10, 24)}
        };
    }

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                .setContentType("application/json")
                .build();
    }

    @After
    public  void tearDown(){
        trackOrder = createOrder(order, spec).then().extract().path("track");
        cancelOrder(trackOrder, spec);

    }

    @Test
    @DisplayName("Создание заказ с разными вариациями цвета")
    public void successCreateOrder(){
        Response createOrderResponse = createOrder(order, spec);
        createOrderResponse.then()
                .assertThat()
                .statusCode(expectStatusCode)
                .and()
                .body("track",notNullValue());
    }
}
