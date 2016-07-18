<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoCliente.php');

$idImovel = $_POST['idImovel'];
//$idImovel = "1";

listarClientesPorImovel($idImovel);