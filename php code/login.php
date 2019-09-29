<?php
require "com.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
$mysql_qry = "select id,name from user where username like '$user_name' and password like '$user_pass';";

$result = mysqli_query($conn, $mysql_qry);
 $row = mysqli_fetch_array($result);
 $id=$row[0];
 $name = $row[1];
if(mysqli_num_rows($result)>0){
	echo $user_name.'+'.$id;
}
else{
	echo "login not success";
}
?>