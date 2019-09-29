<?php

require "com.php";

$d_username=$_GET['d_username'];

$mysql_qry="select id_doctor from doctors where username like '$d_username';";

$results = mysqli_query($conn, $mysql_qry);
$row = mysqli_fetch_array($results);
$id_doctor = $row[0];
$stmt = $conn->prepare("SELECT first_name,last_name,username,password,address,phone FROM secretaries where id_doctor like '$id_doctor';");
$stmt->execute();
$stmt->bind_result($firstname,$lastname,$username,$password,$address,$phone);
$secretaries=array();
while($stmt->fetch()){
	$temp = array();
 $temp['firstname'] = $firstname; 
 $temp['lastname'] = $lastname; 
 $temp['username'] = $username; 
 $temp['password'] = $password; 
 $temp['address'] = $address; 
 $temp['phone'] = $phone;

 array_push($secretaries, $temp);
	
}
echo json_encode($secretaries);



?>