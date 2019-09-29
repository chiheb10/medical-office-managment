<?php
require "com.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
/*$user_name="chi";
$user_pass="123456";*/

$mysql_qry = "select level from receptionist where username like '$user_name' and password like '$user_pass';";

$result = mysqli_query($conn, $mysql_qry);
 $row = mysqli_fetch_array($result);
 $level=$row[0];
 
$mysql_qry2 = "select id_patient,name from user where username like '$user_name' and password like '$user_pass';";

/*$result2 = mysqli_query($conn, $mysql_qry2);
 $row2 = mysqli_fetch_array($result2);
 $id=$row2[0];
 $name = $row2[1];
if(mysqli_num_rows($result2)>0){
	echo $level.'+'.$user_name.'+'.$id;
}
else{
	echo "login not success";
}*/
 if(mysqli_num_rows($result)==0){
	echo "login not success";

}
else {
	if($level=="doctor"){
	
$mysql_qry1 = "select * from doctors where username like '$user_name' and password like '$user_pass';";
$result1 = mysqli_query($conn, $mysql_qry1);
 $row1 = mysqli_fetch_array($result1);
 $name = $level.'+'.$row1[1].'+'.$row1[2];
if(mysqli_num_rows($result1)>0){
	echo $name;
}
else{
	echo "login not success";
}
		
}
else if ($level=="user"){
	$mysql_qry2 = "select id_patient,name from user where username like '$user_name' and password like '$user_pass';";

$result2 = mysqli_query($conn, $mysql_qry2);
 $row2 = mysqli_fetch_array($result2);
 $id=$row2[0];
 $name = $row2[1];
if(mysqli_num_rows($result2)>0){
	echo $level.'+'.$user_name.'+'.$id;
}
else{
	echo "login not success";
}
}else if($level=="secretary"){
$mysql_qry3 = "select id from secretaries where username like '$user_name' and password like '$user_pass';";
$result3 = mysqli_query($conn, $mysql_qry3);
 $row3 = mysqli_fetch_array($result3);

if(mysqli_num_rows($result3)>0){
	echo $level.'+'.$row3[0];
}
else{
	echo "login not success";
}
	
	
}
}
	

	


?>