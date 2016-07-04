<?php
require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoImovel.php');


$imovel = $_POST['objetoImovel'];

$objetoImovel = json_decode($imovel);

deletarImovel($objetoImovel);

