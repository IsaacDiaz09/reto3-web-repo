import { mostrarMensaje } from "./utils.js";
import Constants from "./helpers/constants.js";

// Cuando el DOM se haya cargado hace una peticion y recupera la informacion del usuario
$(document).ready(function () {
    $("#show-orders").click(function () {
        $.ajax({
            url: `${Constants.URL_UTILS}/all_ase_orders`,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                DrawOrdersTable(data);
            },
            error: function (xhr, status) {
                mostrarMensaje('Error', 'Error al obtener la información de las ordenes', true);
            }
        });
    })
}
)


// Se dibuja tabla donde se mostrara el resumen de las ordenes
const DrawOrdersTable = (orders) => {
    let productsName = [];

    // Se escribe el encabezado
    let data = `
        <div class="container h-100 text-center">
            <div class="row h-100">
            <table class="table table-sm">
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Productos</th>
                    <th scope="col">Cantidades</th>
                    <th scope="col">Total</th>
                    <th scope="col">Estado</th>`
    if (window.location.href === "http://localhost:8080/app/seguimiento") {
        data +=
            `<th scope="col">Aprobar</th>
                    <th scope="col">Rechazar</th>`
    }

    data += `</tr>
                </thead>
                <tbody>`

    orders.forEach(function (order, i) {
        // Se convierte la fecha a un formato mas corto
        let date = new Date(order.registerDay);

        data += `
                <tr>
                <td>${date.toDateString()}</td>`

        let sum = 0;
        let size = Object.keys(order.products).length;
        // Ya que pueden haber varios productos, se recorren y se obtiene el total y sus nombres
        for (let index = 0; index < size; index++) {
            productsName.push(order.products[index + 1].name);
            sum += order.products[index + 1].price
        }

        // Se agregan los datos procesados a sus filas
        data +=
            `<td>[${productsName}]</td>
        <td>[${Object.values(order.quantities)}]</td>
        <td>${sum}</td>`

        if (order.status == Constants.ORDER_PENDING) {
            data += `<td class="alert alert-warning" role="alert">${order.status}</td>`
        } else if (order.status == Constants.ORDER_APROVED) {
            data += `<td class="alert alert-success" role="alert">${order.status}</td>`
        } else if (order.status == Constants.ORDER_REJECTED) {
            data += `<td class="alert alert-danger" role="alert">${order.status}</td>`
        }

        if (window.location.href === "http://localhost:8080/app/seguimiento") {
            data += `<td><button class="btn btn-success" id="aprobar-${order.id}">Aprobar</button></td>`
            data += `<td><button class="btn btn-danger" id="rechazar-${order.id}">Rechazar</button></td>`
        }

        data += `</tr>`
        // Se reinician las variables usadas
        productsName = [];
        sum = 0;

    }),

        // Se finaliza el cuerpo de la tabla
        data += `  </tbody>
                </div></div>`

    // Finalmente, se añade todo el contenido al modal y se muestra
    $("#modal-body").html(data);
    $("#h5-title").text('Mis ordenes');
    $("#myModal").modal('show');

    if (window.location.href === "http://localhost:8080/app/seguimiento") {

        orders.forEach(function (product, index) {
            document.getElementById("aprobar-" + product.id).addEventListener("click", function () {
                aprobar(product);
            })
            document.getElementById("rechazar-" + product.id).addEventListener("click", function () {
                rechazar(product);
            })
        })
    }
}

const aprobar = (order) => {
    order.status = Constants.ORDER_APROVED
    $.ajax({
        url: `${Constants.URL_BASE}/order/update`,
        type: "PUT",
        data: JSON.stringify(order),
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
        },
        statusCode: {
            201: function () {
                $("#myModal").modal('hide');
                mostrarMensaje("Confirmacion", "Orden aprobada exitosamente", false);
            },
        },
    });
}

const rechazar = (order) => {
    order.status = Constants.ORDER_REJECTED
    $.ajax({
        url: `${Constants.URL_BASE}/order/update`,
        type: "PUT",
        data: JSON.stringify(order),
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
        },
        statusCode: {
            201: function () {
                $("#myModal").modal('hide');
                mostrarMensaje("Confirmacion", "Orden rechazada exitosamente", false);
            },
        },
    });
}