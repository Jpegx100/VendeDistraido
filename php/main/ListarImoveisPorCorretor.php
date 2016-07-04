<?php

require_once('../dao/ConfiguracaoDoServidor.php');
require_once('../dao/DaoImovel.php');


$idCorretor = $_POST['idCorretor'];

listarImoveisPorCorretor($idCorretor);