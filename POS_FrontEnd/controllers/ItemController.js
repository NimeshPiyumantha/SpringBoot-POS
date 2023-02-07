/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/

let baseUrl = "http://localhost:8080/springBoot/";
loadAllItems();
/**
 * Item Save
 * */

$("#btnAddItem").attr('disabled', true);
$("#btnUpdateItem").attr('disabled', true);
$("#btnDeleteItem").attr('disabled', true);
/**
 * Item Save
 * Item ID
 * */
function generateItemID() {
    $("#txtItemID").val("I00-001");
    $.ajax({
        url: baseUrl + "item/ItemIdGenerate",
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (resp) {
            let code = resp.value;
            let tempId = parseInt(code.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                $("#txtItemID").val("I00-00" + tempId);
            } else if (tempId <= 99) {
                $("#txtItemID").val("I00-0" + tempId);
            } else {
                $("#txtItemID").val("I00-" + tempId);
            }
        },
        error: function (ob, statusText, error) {

        }
    });
}

/**
 * Button Add New Item
 * */
$("#btnAddItem").click(function () {
    let formData = $("#itemForm").serialize();
    $.ajax({
        url: baseUrl + "item",
        method: "post",
        data: formData,
        dataType: "json",
        success: function (res) {
            saveUpdateAlert("item", res.message);
            loadAllItems();
        },
        error: function (error) {
            unSuccessUpdateAlert("item", JSON.parse(error.responseText).message);
        }
    });
});

/**
 * clear input fields Values Method
 * */
function setTextFieldValues(code, description, qty, price) {
    $("#txtItemID").val(code);
    $("#txtItemName").val(description);
    $("#txtItemQty").val(qty);
    $("#txtItemPrice").val(price);
    $("#txtItemName").focus();
    checkValidity(ItemsValidations);
    $("#btnAddItem").attr('disabled', true);
    $("#btnUpdateItem").attr('disabled', true);
    $("#btnDeleteItem").attr('disabled', true);

}

/**
 * load all Item Method
 * */
function loadAllItems() {
    $("#ItemTable").empty();
    $.ajax({
        url: baseUrl + "item/loadAllItem",
        method: "GET",
        dataType: "json",
        success: function (res) {
            console.log(res);
            for (let i of res.data) {
                let code = i.code;
                let description = i.description;
                let qty = i.qty;
                let unitPrice = i.unitPrice;

                let row = "<tr><td>" + code + "</td><td>" + description + "</td><td>" + qty + "</td><td>" + unitPrice + "</td></tr>";
                $("#ItemTable").append(row);
            }
            blindClickEvents();
            generateItemID();
            setTextFieldValues("", "", "", "");
            console.log(res.message);
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            console.log(message);
        }
    });
}

/**
 * Table Listener Click and Load textFields
 * */
function blindClickEvents() {
    $("#ItemTable>tr").on("click", function () {
        let code = $(this).children().eq(0).text();
        let description = $(this).children().eq(1).text();
        let qty = $(this).children().eq(2).text();
        let unitPrice = $(this).children().eq(3).text();
        console.log(code, description, qty, unitPrice);

        $("#txtItemID").val(code);
        $("#txtItemName").val(description);
        $("#txtItemQty").val(qty);
        $("#txtItemPrice").val(unitPrice);
    });
    $("#btnAddItem").attr('disabled', true);
}


/**
 * Search id and Load Table
 * */
$("#ItemIdSearch").on("keypress", function (event) {
    if (event.which === 13) {
        var search = $("#ItemIdSearch").val();
        $("#ItemTable").empty();
        $.ajax({
            url: baseUrl + "item/searchItemCode/?code="+ search,
            method: "GET",
            contentType: "application/json",
            dataType: "json",
            success: function (res) {
                console.log(res);
                let row = "<tr><td>" + res.code + "</td><td>" + res.description + "</td><td>" + res.qty + "</td><td>" + res.unitPrice + "</td></tr>";
                $("#ItemTable").append(row);
                blindClickEvents();
            },
            error: function (error) {
                loadAllItems();
                let message = JSON.parse(error.responseText).message;
                emptyMassage(message);
            }
        })
    }
});

/**
 * Item Update
 * */

/**
 * Update Action
 * */
$("#btnUpdateItem").click(function () {

    let code = $("#txtItemID").val();
    let description = $("#txtItemName").val();
    let qty = $("#txtItemQty").val();
    let unitPrice = $("#txtItemPrice").val();

    var itemOb = {
        code: code,
        description: description,
        qty: qty,
        unitPrice: unitPrice
    }

    $.ajax({
        url: baseUrl + "item",
        method: "put",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res) {
            saveUpdateAlert("Item", res.message);
            loadAllItems();
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("Item", message);
        }
    });
});


/**
 * Item Delete
 * */

/**
 * Delete Action
 * */
$("#btnDeleteItem").click(function () {

    let itCode = $("#txtItemID").val();
    let itDescription = $("#txtItemName").val();
    let itQty = $("#txtItemQty").val();
    let itUnitPrice = $("#txtItemPrice").val();

    const itemOb = {
        code: itCode,
        description: itDescription,
        qty: itQty,
        unitPrice: itUnitPrice
    }
    $.ajax({
        url: baseUrl + "item",
        method: "delete",
        contentType: "application/json",
        data: JSON.stringify(itemOb),
        success: function (res) {
            saveUpdateAlert("Item", res.message);
            loadAllItems();
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("Item", message);
        }
    });
});


/**
 * Auto Forces Input Fields Save
 * */
$("#txtItemID").focus();
const regExItemCode = /^(I00-)[0-9]{3,4}$/;
const regExItemName = /^[A-z ]{3,20}$/;
const regExItemPrice = /^[0-9]{1,10}$/;
const regExItemQtyOnHand = /^[0-9]{1,}[.]?[0-9]{1,2}$/;

let ItemsValidations = [];
ItemsValidations.push({
    reg: regExItemCode,
    field: $('#txtItemID'),
    error: 'Item ID Pattern is Wrong : I00-001'
});
ItemsValidations.push({
    reg: regExItemName,
    field: $('#txtItemName'),
    error: 'Item Name Pattern is Wrong : A-z 3-20'
});
ItemsValidations.push({
    reg: regExItemPrice,
    field: $('#txtItemQty'),
    error: 'Item Qty Pattern is Wrong : 0-9 1-10'
});
ItemsValidations.push({
    reg: regExItemQtyOnHand,
    field: $('#txtItemPrice'),
    error: 'Item Salary Pattern is Wrong : 100 or 100.00'
});

//disable tab key of all four text fields using grouping selector in CSS
$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('keydown', function (event) {
    if (event.key === "Tab") {
        event.preventDefault();
    }
});

$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('keyup', function (event) {
    checkValidity(ItemsValidations);
});

$("#txtItemID,#txtItemName,#txtItemQty,#txtItemPrice").on('blur', function (event) {
    checkValidity(ItemsValidations);
});

$("#txtItemID").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemCode, $("#txtItemID"))) {
        $("#txtItemName").focus();
    } else {
        focusText($("#txtItemID"));
    }
});

$("#txtItemName").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemName, $("#txtItemName"))) {
        focusText($("#txtItemQty"));
    }
});

$("#txtItemQty").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemPrice, $("#txtItemQty"))) {
        focusText($("#txtItemPrice"));
    }
});

$("#txtItemPrice").on('keydown', function (event) {
    if (event.key === "Enter" && check(regExItemQtyOnHand, $("#txtItemPrice"))) {
        if (event.which === 13) {
            $('#btnAddItem').focus();
        }
    }
});

function setButtonState(value) {
    if (value > 0) {
        $("#btnAddItem").attr('disabled', true);
        $("#btnUpdateItem").attr('disabled', true);
        $("#btnDeleteItem").attr('disabled', true);
    } else {
        $("#btnAddItem").attr('disabled', false);
        $("#btnUpdateItem").attr('disabled', false);
        $("#btnDeleteItem").attr('disabled', false);
    }
}