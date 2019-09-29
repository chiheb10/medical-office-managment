<?php
require "com.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
$mysql_qry = "select * from doctors where username like '$user_name' and password like '$user_pass';";
$result = mysqli_query($conn, $mysql_qry);
 $row = mysqli_fetch_array($result);
 $name = $row[1].'+'.$row[2];

 if(mysqli_num_rows($result)>0){
	 echo $name;
}
else{
	echo "login not success";
}
?>