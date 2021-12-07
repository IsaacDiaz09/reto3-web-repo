import { mostrarMensaje, urlbase, limpiaCamposGadget, validaProducto } from "./utils.js";

// Valida los atributos del producto, entonces lo guarda
$(document).ready(function () {
    $("#btn-gadget").click(function () {
        // Se recupera el valor de los campos
        const nombre = $.trim($("#name").val());
        const idGadget = $("#idGadget").val();
        const brand = $.trim($("#brand").val());
        const category = $.trim($("#category").val());
        const description = $.trim($("#description").val());
        const price = $("#price").val();
        const quantity = $("#quantity").val();
        const photo = $("#photo").val();

        if (validaProducto(nombre, idGadget, brand, category, description, price, quantity, photo) === false) {
            return;
        } else {
            // Se construye el obj producto
            const gadget = {
                id: idGadget,
                brand: brand,
                category: category,
                name: nombre,
                description: description,
                price: price,
                availability: true,
                quantity: quantity,
                photography: photo
            };

            $.ajax({
                url: `${urlbase}/gadget/new`,
                type: "POST",
                data: JSON.stringify(gadget),
                dataType: "json",
                headers: {
                    "Content-Type": "application/json",
                },
                statusCode: {
                    201: function () {
                        mostrarMensaje(
                            "Confirmacion",
                            "Producto añadido exitosamente",
                            false
                        );
                        limpiaCamposGadget();
                        window.location = "http://localhost:8080/app/gadgets";
                    },
                },
            });
        }
    });
});
