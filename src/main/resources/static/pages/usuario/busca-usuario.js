$(document).ready(function() {
   //buscaIdDefault();

   $('#btnBuscarUsuario').on('click', buscarUsuario);
});

function buscaIdDefault(){
 // Exemplo: busca o ID do usuário atual via API
    $.ajax({
        url: '/api/usuarios/id-atual', // ajuste conforme sua API
        method: 'GET',
        success: function(data) {
            $('#username').val(data.username);
        }
    });
}

function buscarUsuario() {
    $('#loader-overlay').show();
    $.ajax({
        url: `http://localhost:8080/api/jsonplaceholder/users`,
        method: 'GET',
        success: function(response) {

            let list = response.data;

            $('#usuarios').empty();

            $.each(list, function(index, usuario) {
                //let usuario = list(index);
                $('#usuarios').append(
                    `<tr>
                        <td>${usuario.name}</td>
                        <td>${usuario.username}</td>
                        <td>${usuario.email}</td>
                    </tr>`
                );
            });
            $('#loader-overlay').hide();
        },
        error: function() {
            $('#usuarios').empty();
            $('#usuarios').append('<tr><td colspan="5" class="text-danger">Usuário não encontrado</td></tr>');
            $('#loader-overlay').hide();
        }
    });
}