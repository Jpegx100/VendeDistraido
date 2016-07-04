<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoUsuario.php');

error_reporting(0);
ini_set("display_errors", 0);


$user = $_POST['objetoUsuario'];

$objetoUsuario = json_decode($user);

pesquisarUsuario($objetoUsuario);

