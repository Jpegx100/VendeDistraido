<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoImovel.php');

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$idCliente = $_POST['idCliente'];
$idImovel = $_POST['idImovel'];
//$idCliente = "8";
//$idImovel = "4";

interresseClienteImovel($idCliente,$idImovel);