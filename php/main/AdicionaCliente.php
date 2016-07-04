<?php
require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoCliente.php');


$user = $_POST['objetoCliente'];

$objetoUsuario = json_decode($user);

adicionarCliente($objetoUsuario);



