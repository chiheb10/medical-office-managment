<?php
require "com.php";
$id = $_POST["id"];
$level=$_POST["level"];

if($level=="user"){
	$sql="select username,password,addresse,e_mail from user where id_patient like '$id'";
	$check= mysqli_query($conn,$sql);
	$row=mysqli_fetch_array($check);
	if(mysqli_num_rows($check)>0){
		echo $row[0].'+'.$row[1].'+'.$row[2].'+'.$row[3];
	}
	else {echo "try again";}
}
if($level=="secretary"){
	$sql="select username,password,address,phone from secretaries where id like '$id'";
	$check= mysqli_query($conn,$sql);
	$row=mysqli_fetch_array($check);
	if(mysqli_num_rows($check)>0){
		echo $row[0].'+'.$row[1].'+'.$row[2].'+'.$row[3];
	}
	else {echo "try again";}
}

?>