<?php
require "com.php";
$receiver_data=$_POST['array'];
$new_array=json_decode($receiver_data,true);
//echo $receiver_data->['name'];
//print_r($receiver_data);
foreach($new_array as $row)
{
	$d_username=$row['name'];
}
?>