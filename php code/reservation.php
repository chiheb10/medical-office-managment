<?php
require "com.php";
$firstname=$_POST["firstname"];
$lastname=$_POST["lastname"];
$phone=$_POST["phone"];
$age=$_POST["age"];
$id=$_POST["id"];
$number=$_POST["number"];
$username=$_POST["username"];
$password=$_POST["password"];
$des=$_POST["description"];

$table="working";
$sql = "SHOW COLUMNS FROM working";
$result = mysqli_query($conn,$sql);

$temp1=array();
while($row = mysqli_fetch_array($result)){
    //echo $row['Field']."<br>";
	array_push($temp1,$row['Field']);	
}
$pos=intval($number)+2;
$app=$temp1[$pos];

$sql="select id_patient from user where username like '$username' and password like '$password'";
$result1 = mysqli_query($conn, $sql);
$row1 = mysqli_fetch_array($result1);
$id_user=$row1[0];
$sql2="select id_doctor,date from working where id like '$id'";
$result2 = mysqli_query($conn, $sql2);
$row2 = mysqli_fetch_array($result2);
$id_doctor=$row2[0];
$date=$row2[1];
if(mysqli_num_rows($result1)==0){echo "please verify your username and password";}
else{



$mysql_qry="UPDATE working SET $app = 'reserved' where id like '$id'";
$mysql_qry2="insert into reservation (id_doctor,id_patient,date,time,patient_firstname,patient_lastname,patient_phone,patient_age,matter) values ('$id_doctor','$id_user','$date','$app','$firstname','$lastname','$phone','$age','$des')";
if( ($conn->query($mysql_qry2))&&($conn->query($mysql_qry) === TRUE)){
	
	echo $id_user;
}
else{
	echo "reservation not success";
}}
$conn->close();






?>