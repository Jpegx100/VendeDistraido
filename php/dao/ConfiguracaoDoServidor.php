<?php

error_reporting(0);
ini_set("display_errors",0);

$db['sever'] = 'localhost';
$db['user'] = 'root';
$db['password'] = '';
$db['dbname'] = 'vendedistraido';

$conn = mysql_connect($db['server'],$db['user'],$db['password']);
mysql_select_db($db['dbname'],$conn);
