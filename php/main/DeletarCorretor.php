<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoCorretor.php');


$user = $_POST['objetoCorretor'];

$objetoUsuario = json_decode($user);

deletarCorretor($objetoUsuario);