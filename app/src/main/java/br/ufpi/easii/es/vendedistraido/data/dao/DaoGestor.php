<?php

include 'ConfiguracaoDoServidor.php';

function adicionarGestor($objetoUsuario) {

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

        $sqlInsertGestor = "insert into gestor (id_usuario) values ('$id_user[0]')";

        $sqlTESTE2 = mysql_query($sqlInsertGestor);

        if ($sqlTESTE2) {
            echo'INSERIDO';
        } else {
            echo 'NAO INSERIDO' . mysql_error();
        }
    } else {
        echo 'NAO INSERIDO ' . mysql_error();
    }
}
