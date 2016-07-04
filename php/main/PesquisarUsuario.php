<?php
require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoUsuario.php');


$user = $_POST['objetoUsuario'];

$objetoUsuario = json_decode($user);

pesquisarUsuario($objetoUsuario);

