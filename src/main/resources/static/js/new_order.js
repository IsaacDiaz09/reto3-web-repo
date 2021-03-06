import { mostrarMensaje } from "./utils.js";
import Constants from "./helpers/constants.js";

let orderDetail = [];
let products = [];
let ids = [];
let salesMan = undefined;
let order = undefined;

// Cuando la pagina se ha cargado trae la informacion del usuario
$(document).ready(function () {
    $.get(Constants.URL_UTILS + "/get_user_info", function (user) {
        $.get(Constants.URL_BASE + "/user/" + user.id, function (data) {
            salesMan = data;
        })
    }
    )
})

// Obtiene el id autogenerado, el detalle de orden y construye el objeto
$(document).ready(function () {
    $("#save-order").click(function () {
        $.get(Constants.URL_UTILS + "/get_autogenerated_order_id", function (response) {
            // Se convierte a set para evitar posibles repetidos
            const IDS = new Set(ids);
            let result = [...IDS];

            createOrder(response, salesMan, result);
			sendOrder(order);
            reset();
            $("#order").html("");
            $("#detail").text("");
            $("#save-order").hide();
        }
        )
    })
}
)

// Agregar producto al detalle de orden
$(document).ready(function () {
    $("#btn-add-product").click(function () {
        $.get(Constants.URL_BASE + "/gadget/all", function (data) {
            products = data;
            DrawProductsTable(data);
        })
    }
    )
})

// Reinicia las variables usadas
const reset = () => {
    orderDetail = [];
    products = [];
    ids = [];
    order = undefined;
}

// Obtiene el producto seleccionado, lo agrega a una lista y oculta el modal
const getProduct = (index) => {
    orderDetail.push(products[index]);
    $("#myModal").modal('hide');
    DrawDetailTable(index);
    $("#save-order").show();
}

// Dibuja una lista con los productos disponibles en el modal
const DrawProductsTable = (data) => {

    let table = `
    <div class="text-center">
    <table class="table table-sm">
    <thead>
      <tr>
        <th scope="col">Nombre</th>
        <th scope="col">Precio</th>
        <th scope="col">Cantidad</th>
        <th scope="col">Foto</th>
        <th scope="col">Acci??n</th>
      </tr>
    </thead>
    <tbody>`

    data.forEach(function (p, i) {
        table +=
            `<tr>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td><img src="${p.photography}" alt="${p.name}" height="50"> </td>
            <td><button class="btn btn-outline-primary" id="${i}">Seleccionar</button></td>
        </tr>`

    });

    table += `<tbody> <table> </div>`

    $("#modal-body").html(table);
    $("#h5-title").text('Productos');
    $("#myModal").modal('show');

    data.forEach(function (product, index) {
        document.getElementById(index).addEventListener("click", function () {
            getProduct(index);
        });
    })
}

// Dibuja una tabla con los productos seleccionados
const DrawDetailTable = (index) => {
    let table = `
    <div class="container h-100 text-center" style="width:70%;">
        <div class="row h-100">
    <table class="table table-bordered">
    <thead>
      <tr>
        <th scope="col">Nombre</th>
        <th scope="col">Precio</th>
        <th scope="col">Cantidad</th>
        <th scope="col">Foto</th>
        <th scope="col">Cantidad a pedir</th>
      </tr>
    </thead>
    <tbody>`

    orderDetail.forEach(function (p, i) {
        table +=
            `<tr>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td><img src="${p.photography}" alt="${p.name}" height="50"> </td>
            <td><input type="number" min="1" value="1" max="${p.quantity}" id="quantity-${p.id}"}></td>
        </tr>`
    });

    table += `<tbody> <table> </div> </div>`

    $("#detail").text('Detalle');
    $("#order").html(table);

    orderDetail.forEach(function (product, index) {
        ids.push('#quantity-' + product.id);

        $('#quantity-' + product.id).on('input', function () {
            ids.push("#quantity-" + product.id);
        });
    })
}

// Obtiene las cantidades a pedir de los productos en el detalle
const getQuantities = (list_ids) => {
    let dict = {};

    list_ids.forEach(function (product, index) {
        dict[index + 1] = $(list_ids[index]).val();
    })
    return dict;
}

// Construye el objeto de tipo orden (la fecha actual se agrega desde el bak-end)
const createOrder = (idOrder, perfil, ids) => {
    order = {
        id: idOrder,
        salesMan: perfil,
        products: {},
        quantities: getQuantities(ids)
    };

    for (let i = 0; i < orderDetail.length; i++) {
        order.products[i + 1] = orderDetail[i];
    }
}

// Recibe el obj orden y lo envia al endpoint para su persistencia
const sendOrder = (order) => {
    $.ajax({
        url: Constants.URL_BASE + "/order/new",
        type: "POST",
        data: JSON.stringify(order),
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
        },
        success: function (data) {
            mostrarMensaje("Confirmacion", "Orden generada exitosamente", false);
        },
        error: function (xhr, status) {
            mostrarMensaje('Error', 'Error al generar la orden', true);
        }
    }
    )
}
