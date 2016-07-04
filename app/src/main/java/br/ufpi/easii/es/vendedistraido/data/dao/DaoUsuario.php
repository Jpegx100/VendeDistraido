<?php
include 'ConfiguracaoDoServidor.php';

function pesquisarUsuario($objetoUsuario) {


    $email = $objetoUsuario->{'email'};

    $query = mysql_query("SELECT * FROM usuario where email = '$email'");
    while ($row = mysql_fetch_array($query)) {

        $idUsuario = $row["id"];
        $emailUsuario = $row["email"];
        $nomeUsuario = $row["nome"];
        $senhaUsuario = $row["senha"];
        $telefoneUsuario = $row["telefone"];
    }

    $array = array(
        "id" => $idUsuario,
        "email" => $emailUsuario,
        "nome" => $nomeUsuario,
        "senha" => $senhaUsuario,
        "telefone" => $telefoneUsuario
    );
    
    $tipoUser = 'null';
    
    $query1 = mysql_query("SELECT * FROM cliente where id_usuario = '$idUsuario'");
    while ($row = mysql_fetch_array($query1)) {
        $tipoUser = 'cl';
    }
    $query2 = mysql_query("SELECT * FROM gestor where id_usuario = '$idUsuario'");
    while ($row = mysql_fetch_array($query2)) {
        $tipoUser = 'ge';
    }
    $query3 = mysql_query("SELECT * FROM corretor where id_usuario = '$idUsuario'");
    while ($row = mysql_fetch_array($query3)) {
        $tipoUser = 'co';
    }

    $string = json_encode($array) . '!=>' . $tipoUser;

    echo $string;
}






