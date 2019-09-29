<?php
require "com.php";

$firstname = $_POST["firstname"];
$lastname = $_POST["lastname"];
$username = $_POST["username"];
$password = $_POST["password"];
$address = $_POST["address"];
$phone = $_POST["phone"];
$d_username=$_POST["d_user"];
$mysql_qry1 = "select * from receptionist where username like '$username' or password like '$password';";
$level="secretary";
$check1= mysqli_query($conn,$mysql_qry1);

if(mysqli_num_rows($check1)>0){echo "username or password already exists";}
else{
	$mysql_qry2 = "select id_doctor from doctors where username like '$d_username';";
	$results = mysqli_query($conn, $mysql_qry2);
$row = mysqli_fetch_array($results);
 $id_doctor = $row[0];
$mysql_qry = "insert into secretaries (first_name, last_name, username, password, id_doctor, address,phone) values ('$firstname','$lastname','$username','$password','$id_doctor','$address','$phone')";
$sql="insert into receptionist (username,password,level) values ('$username','$password','$level')";
if(($conn->query($mysql_qry) === TRUE)&&($conn->query($sql) === TRUE)){
	echo $d_username;
}
else{
	echo "registration not success";
}}
$conn->close();
?>



