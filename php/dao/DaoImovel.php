<?php

include 'ConfiguracaoDoServidor.php';

function adicionarImovel($objetoImovel, $idCorretor) {

    $endereco = $objetoImovel->{'endereco'};
    $latitude = $objetoImovel->{'latitude'};
    $longitude = $objetoImovel->{'longitude'};
    // $id_corretor = $objetoImovel->{'id_corretor'};

    $sqlInsertImovel = "insert into imovel (endereco,latitude,longitude,id_corretor) values ('$endereco','$latitude','$longitude','$idCorretor')";
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

function listarImoveis() {

    $query = mysql_query("SELECT * FROM imovel");

    $users = array();
    while ($row = mysql_fetch_array($query)) {
        $array = array(
            "endereco" => $row["endereco"],
            "latitude" => $row["latitude"],
            "longitude" => $row["longitude"],
            "id_corretor" => $row["id_corretor"],
            "id" => $row["id"]
        );
        $users[] = $array;
    }

    $string = json_encode($users);

    echo $string;
}

function listarImoveisPorCorretor($idCorretor) {

    $query = mysql_query("SELECT * FROM imovel where id_corretor = '$idCorretor'");

    $users = array();
    while ($row = mysql_fetch_array($query)) {
        $array = array(
            "endereco" => $row["endereco"],
            "latitude" => $row["latitude"],
            "longitude" => $row["longitude"],
            "id_corretor" => $row["id_corretor"],
            "id" => $row["id"]
        );
        $users[] = $array;
    }

    $string = json_encode($users);

    echo $string;
}
function listarImoveisPorCliente($idCliente){
    
    
    $query = mysql_query("SELECT id_imovel FROM cliente_interesse_imovel where id_cliente = '$idCliente'");
  
    $ids = '(';

    while ($row = mysql_fetch_array($query)) {
        if ($ids != '(')
            $ids = $ids . ',';
        $ids = $ids . $row['id_imovel'];
    }

    $ids = $ids . ')';
    //echo $ids;
    $query2 = mysql_query("SELECT * FROM imovel where id in $ids ");

    $imoveis = array();
    while ($row = mysql_fetch_array($query2)) {
        $array = array(
            "endereco" => $row["endereco"],
            "latitude" => $row["latitude"],
            "longitude" => $row["longitude"],
            "id_corretor" => $row["id_corretor"],
            "id" => $row["id"]
            
        );
        $imoveis[] = $array;
    }

     $string = json_encode($imoveis);

    echo $string;

       
}


function interresseClienteImovel($idCliente,$idImovel){
    
    $sqlInsertImovel = "insert into cliente_interesse_imovel (id_cliente,id_imovel) values ('$idCliente','$idImovel')";
    $sqlTESTE = mysql_query($sqlInsertImovel);

    if ($sqlTESTE) {
        echo'INSERIDO';
    } else {
        echo 'NAO INSERIDO';
    }
}

function RemoverInterresseClienteImovel($idCliente,$idImovel){
    
    $sqlDeleteImovel = "delete from cliente_interesse_imovel where id_cliente = '$idCliente' and id_imovel = '$idImovel'";
    $sqlTESTE = mysql_query($sqlDeleteImovel);

    if ($sqlTESTE) {
        echo'DELETADO COM SUCESSO';
    } else {
        echo 'NAO DELETADO';
    }
    
}