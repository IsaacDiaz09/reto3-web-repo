import { mostrarMensaje, urlbase, url } from "./utils.js";

const orderDetail = [];
let products = [];
let salesMan = undefined;

$(document).ready(function () {
    $.get(url + "/get_user_info", function (user) {
        $.get(url + "/api/user/" + user.id, function (data) {
            salesMan = data;
            // to-do --> console.log(salesMan); ya se ha recuperado el obj entero para añadir a la otdeb
        })
    }
    )
})


$(document).ready(function () {
    $("#btn-add-product").click(function () {
        $.get(urlbase + "/gadget/all", function (data) {
            products = data;
            DrawProductsTable(data);
        })
    }
    )
})

const getProduct = (index) => {
    orderDetail.push(products[index]);
    $("#myModal").modal('hide');
    DrawDetailTable(index);
    $("#save-order").show();
}

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
        <th scope="col">Acción</th>
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
            <td><input type="number" id="${p.name}"}></td>

        </tr>`
    });

    table += `<tbody> <table> </div> </div>`

    $("#detail").text('Detalle');
    $("#order").html(table);
}

const guardar = () => {
    let pedido = {
        id: 1,
        status: 'Pendiente',
        salesMan: perfilGlobal,
        products: {}
    };

    for (let i = 0; i < detalle.length; i++) {
        pedido.products[i + 1] = detalle[i];
    }

    console.log(pedido);
}
