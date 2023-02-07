/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

let baseUrl = "http://localhost:8080/springBoot/";
loadAllOrders();
loadAllOrderDetails();

function loadAllOrders() {
    $("#tblOrder").empty();
    $.ajax({
        url: baseUrl + "orders/LoadOrders", method: "GET", dataType: "json", success: function (res) {
            console.log(res);

            for (let i of res.data) {
                let oid = i.oid;
                let date = i.date;
                let cusID = i.cusID;

                let row = "<tr><td>" + oid + "</td><td>" + date + "</td><td>" + cusID + "</td></tr>";
                $("#tblOrder").append(row);
            }
            console.log(res.message);
        }, error: function (error) {
            let message = JSON.parse(error.responseText).message;
            console.log(message);
        }

    });
}

function loadAllOrderDetails() {
    $("#tblOrderDetails").empty();
    $.ajax({
        url: baseUrl + "orders/LoadOrderDetails", method: "GET", dataType: "json", success: function (res) {
            console.log(res);

            for (let i of res.data) {
                let oid = i.oid;
                let itemCode = i.itemCode;
                let qty = i.qty;
                let unitPrice = i.unitPrice;

                let row = "<tr><td>" + oid + "</td><td>" + itemCode + "</td><td>" + qty + "</td><td>" + unitPrice + "</td></tr>";
                $("#tblOrderDetails").append(row);
            }
            console.log(res.message);
        }, error: function (error) {
            let message = JSON.parse(error.responseText).message;
            console.log(message);
        }

    });
}