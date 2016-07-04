<?php

include 'ConfiguracaoDoServidor.php';

function adicionarImovel($objetoImovel) {

    $endereco = $objetoImovel->{'endereco'};
    $latitude = $objetoImovel->{'latitude'};
    $longitude = $objetoImovel->{'longitude'};
    $id_corretor = $objetoImovel->{'id_corretor'};

    $sqlInsertImovel = "insert into imovel (endereco,latitude,longitude,id_corretor) values ('$endereco','$latitude','$longitude','$id_corretor')";
    $sqlTESTE = mysql_query($sqlInsertImovel);

    if ($sqlTESTE) {
        echo'INSERIDO';
    } else {
        echo 'NAO INSERIDO';
    }
}
function deletarImovel($objetoImovel) {

    $id = $objetoImovel->{'id'};

    $sqlDeleteImovel = "delete from imovel where id = '$id'";

    $sqlTESTE = mysql_query($sqlDeleteImovel);

    if ($sqlTESTE) {
        echo'DELETADO';
    } else {
        echo 'NAO DELETADO';
    }
}
