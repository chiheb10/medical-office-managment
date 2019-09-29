<?php
require "com.php";

$name1 = $_POST["name"];
$username1 = $_POST["username"];
$password = $_POST["password"];
$gender = $_POST["gender"];
$address = $_POST["address"];
$email = $_POST["email"];
$level="user";
$mysql_qry1 = "select * from receptionist where username like '$username1' or password like '$password';";
$check= mysqli_query($conn,$mysql_qry1);
if(mysqli_num_rows($check)>0){echo "username or password already exists";}
else{
$mysql_qry = "insert into user (name, username, password, addresse, e_mail,gender) values ('$name1','$username1','$password','$address','$email','$gender')";
$mysql_qry1="insert into receptionist (username,password,level) values ('$username1','$password','$level')";
if(($conn->query($mysql_qry) === TRUE) && ($conn->query($mysql_qry1) === TRUE)){
	echo "registration success";
}
else{
	echo "registration not success";
}}
$conn->close();
?>