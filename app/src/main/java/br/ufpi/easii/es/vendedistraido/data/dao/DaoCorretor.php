<?php

include 'ConfiguracaoDoServidor.php';

function adicionarCorretor($objetoUsuario, $idGestor) {

    $nome = $objetoUsuario->{'nome'};
    $telefone = $objetoUsuario->{'telefone'};
    $email = $objetoUsuario->{'email'};
    $senha = $objetoUsuario->{'senha'};

    $sqlInsertUsuario = "insert into usuario (email,nome,senha,telefone) values ('$email','$nome','$senha','$telefone')";
    $sqlTESTE1 = mysql_query($sqlInsertUsuario);

    if ($sqlTESTE1) {

        $sqlSelectUsuario = "select id from usuario where email = '$email'";

        $query = mysql_query($sqlSelectUsuario);
        while ($row = mysql_fetch_array($query)) {
            $id_user[] = $row["id"];
        }

        $idUsuario = $id_user[0];

        $sqlInsertCorretor = "insert into corretor (id_usuario,id_gestor) values ('$idUsuario','$idGestor')";

        $sqlTESTE2 = mysql_query($sqlInsertCorretor);

        if ($sqlTESTE2) {
            echo'INSERIDO';
        } else {
            echo 'NAO INSERIDO' . mysql_error();
        }
    } else {
        echo 'NAO INSERIDO' . mysql_error();
    }
}

function deletarCorretor($objetoUsuario) {

    $id = $objetoUsuario->{'id'};

    $sqlDeleteCorretor = "delete from corretor where id_corretor = '$id'";

    mysql_query($sqlDeleteCorretor);

    $sqlDeleteCorretor2 = "delete from usuario where id = '$id'";

    mysql_query($sqlDeleteCorretor2);
}

//function listarCorretores($objetoUsuario) {
function listarCorretores() {
    //$id = $objetoUsuario->{'id'};
    $id = '2';
    $query = mysql_query("SELECT * FROM corretor where id_gestor = '$id'");
 
    $result = mysqli_query($query);
    
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);
    
}
