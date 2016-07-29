<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoImovel.php');


$imovel = $_POST['objetoImovel'];
$idCorretor =  $_POST['idCorretor'];
$fotos = $_POST['fotos'];
$objetoImovel = json_decode($imovel);

adicionarImovel($objetoImovel,$idCorretor,$fotos);

