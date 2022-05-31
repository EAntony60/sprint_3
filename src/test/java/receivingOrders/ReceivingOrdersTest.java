package receivingOrders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Courier;
import model.Order;
import org.junit.Before;
import org.junit.Test;
import utils.DataGenerator;

import static client.CourierApi.*;
import static client.OrderApi.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class ReceivingOrdersTest {

    private RequestSpecification spec;

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                .setContentType("application/json")
                .build();
    }

    @Test
    @DisplayName("Получение списка заказов без параметров")
    public void ordersListWithoutQuery(){
        Response successReceivingListOrders = receivingListOrdersWithoutQuery(spec);

        successReceivingListOrders.then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("size()", greaterThanOrEqualTo(0))
                .and()
                .body("orders", notNullValue());
    }

    @Test
    @DisplayName("Получение списка заказов у конкретного курьера")
    public void ordersListWithCourierId(){
        Courier courier = DataGenerator.courierGenerator(7,7,7);

        createCourier(courier, spec);

        Order order = DataGenerator.orderGenerator(new String[] {"BLACK"}, 7, 7, 15, 1, "+7 233 123 98 92", 10, 24);

        int track = createOrder(order, spec).then().extract().path("track");

        int orderId = getOrderByTrack(track, spec).then().extract().path("order.id");

        int courierId = signInCourier(courier, spec).then().extract().path("id");

        acceptOrder(courierId,orderId, spec);

        receivingListOrdersWithCourierId(courierId, spec).then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders.id", equalTo(orderId))
                .and()
                .body("orders.courierId", equalTo(courierId));

        cancelOrder(track, spec);

        deleteCourier(courierId, spec);
    }

}
