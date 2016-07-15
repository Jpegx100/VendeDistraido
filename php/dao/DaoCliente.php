<?php

include 'ConfiguracaoDoServidor.php';

function adicionarCliente($objetoUsuario) {
    
    $nome = $objetoUsuario->{'nome'};
    $telefone = $objetoUsuario->{'telefone'};
    $email = $objetoUsuario->{'email'};
    $senha = $objetoUsuario->{'senha'};

    $sqlInsertUsuario = "insert into usuario (email,nome,senha,telefone) values ('$email','$nome','$senha','$telefone')";
    $sqlTESTE = mysql_query($sqlInsertUsuario);

    if ($sqlTESTE) {

        $sqlSelectUsuario = "select id from usuario where email = '$email'";

        $query = mysql_query($sqlSelectUsuario);
        while ($row = mysql_fetch_array($query)) {
            $id_user[] = $row["id"];
        }
        $sqlInsertCliente = "insert into cliente (id_usuario) values ('$id_user[0]')";
        $sqlTESTE2 = mysql_query($sqlInsertCliente);
        if ($sqlTESTE2) {
            echo'INSERIDO';
        } else {
            echo 'NAO INSERIDO ' . mysql_error();
        }
    } else {
        echo 'NAO INSERIDO ' . mysql_error();
    }
}

function listarClientesPorImovel($idImovel){
    
    $query = mysql_query("SELECT id_cliente FROM cliente_interesse_imovel where id_imovel = '$idImovel'");
  
    $ids = '(';

    while ($row = mysql_fetch_array($query)) {
        if ($ids != '(')
            $ids = $ids . ',';
        $ids = $ids . $row['id_cliente'];
    }

    $ids = $ids . ')';
    //echo $ids;
    $query2 = mysql_query("SELECT * FROM usuario where id in $ids ");

    $clientes = array();
    while ($row = mysql_fetch_array($query2)) {
        $array = array(
            "id" => $row["id"],
            "email" => $row["email"],
            "nome" => $row["nome"],
            "senha" => $row["senha"],
            "telefone" => $row["telefone"]
        );
        $clientes[] = $array;
    }

     $string = json_encode($clientes);

    echo $string;
    
    
}
