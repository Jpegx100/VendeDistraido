<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoGestor.php');


$user = $_POST['objetoGestor'];

$objetoUsuario = json_decode($user);

listarCorretores($objetoUsuario);

//listarCorretores();
