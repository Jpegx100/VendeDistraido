<?php
require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoGestor.php');


$user = $_POST['objetoCliente'];
 
$objetoUsuario = json_decode($user);

adicionarGestor($objetoUsuario);