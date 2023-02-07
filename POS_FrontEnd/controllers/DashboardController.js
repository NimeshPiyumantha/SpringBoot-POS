/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

let baseUrl = "http://localhost:8080/POS_BackEnd_war/";

$("#txtCustomerCount").val("00");
$.ajax({
    url: baseUrl + "customer/CustomerCount",
    method: "GET",
    contentType: "application/json",
    dataType: "json",
    success: function (resp) {
        let num = resp.count;
        $("#txtCustomerCount").text(num);

    },
    error: function (ob, statusText, error) {

    }
});

$("#txtItemsCount").val("00");
$.ajax({
    url: baseUrl + "item/itemCount",
    method: "GET",
    contentType: "application/json",
    dataType: "json",
    success: function (resp) {
        let num = resp.count;
        $("#txtItemsCount").text(num);

    },
    error: function (ob, statusText, error) {

    }
});

$("#txtOrderCount").val("00");
$.ajax({
    url: baseUrl + "orders/ordersCount",
    method: "GET",
    contentType: "application/json",
    dataType: "json",
    success: function (resp) {
        let num = resp.count;
        $("#txtOrderCount").text(num);

    },
    error: function (ob, statusText, error) {

    }
});