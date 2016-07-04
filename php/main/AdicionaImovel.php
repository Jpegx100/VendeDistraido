<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoImovel.php');


$imovel = $_POST['objetoImovel'];
$idCorretor =  $_POST['idCorretor'];
$objetoImovel = json_decode($imovel);

adicionarImovel($objetoImovel,$idCorretor);

