import { mostrarMensaje, urlbase } from "./utils.js";

$(document).ready(function () {
    $("#btn-add-product").click(function () {
        $.get(urlbase + "/gadget/all", function (data) {
            DrawProductsTable(data);
        })
    }
    )
})

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
            <td><button class="btn btn-outline-primary" onclick="seleccionarProducto(${i})">Seleccionar</button></td>
        </tr>`
    });

    table += `<tbody> <table> </div>`

    $("#modal-body").html(table);
    $("#h5-title").text('Productos');
    $("#myModal").modal('show');
}
