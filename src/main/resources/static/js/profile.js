import { mostrarMensaje, url } from "./utils.js";

$(document).ready(function () {
    $("#btn-profile").click(function () {
        $.ajax({
            url: url + "/get_user_info",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                DrawModalProfile(data);
            },
            error: function (xhr, status) {
                mostrarMensaje('Error', 'Error al obtener la información del usuario', true);
            }
        });
    }
    )
})

const DrawModalProfile = (user) => {

    let data = `<div class="col-md-12">
    <div class="card mb-3">
        <div class="card-body text-center">
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Nombre</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.name}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Email</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.email}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Celular</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.cellPhone}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Direccion</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.address}
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Zona</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.zone}
                </div>
            </div>
            <hr>
            
            <div class="row">
                <div class="col-sm-3">
                    <h6 class="mb-0">Rol</h6>
                </div>
                <div class="col-sm-9 text-secondary">
                    ${user.role}
                </div>
            </div>
        </div>
    </div>`

    $("#modal-body").html(data);
    $("#h5-title").text('Mi perfil');
    $("#myModal").modal('show');
}