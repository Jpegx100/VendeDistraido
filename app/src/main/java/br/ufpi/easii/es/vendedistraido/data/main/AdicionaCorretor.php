<?php
require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoCorretor.php');


$user = $_POST['objetoCorretor'];
$idGestor = $_POST['idGestor'];

$objetoUsuario = json_decode($user);

adicionarCorretor($objetoUsuario,$idGestor);
