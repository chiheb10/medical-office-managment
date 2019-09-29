<?php
require "com.php";
$id_app=$_POST["id"];
//$id_app="59";
$sql="select id_doctor,patient_phone,date,time from reservation where id like '$id_app';";
$result=mysqli_query($conn,$sql);
$row=mysqli_fetch_array($result);
$id_d=$row[0];
$ph=$row[1];
$date=$row[2];
$time=$row[3];
$sql1="select id from working where id_doctor like '$id_d' and date like '$date';";
$result1=mysqli_query($conn,$sql1);
$row1=mysqli_fetch_array($result1);
$id_w=$row1[0];
$sql2="select name1 from doctors where id_doctor like '$id_d' ;";
$result2=mysqli_query($conn,$sql2);
$row2=mysqli_fetch_array($result2);
$name=$row2[0];
$corresp=array("app1"=>"8H","app2"=>"8H30","app3"=>"9H","app4"=>"9H30","app5"=>"10H","app6"=>"10H30","app7"=>"11H","app8"=>"11H30","app9"=>"14H","app10"=>"14H30","app11"=>"15H","app12"=>"15H30","app13"=>"16H","app14"=>"16H30","app15"=>"17H","app16"=>"17H30");
$mysql_qry="delete from reservation where id like '$id_app';";
$mysql_qry1="UPDATE working SET $time = '' where id like '$id_w';";
//echo $id_app;
if(($conn->query($mysql_qry)=== TRUE)&&($conn->query($mysql_qry1)=== TRUE)){
	echo $ph.'+'.$date.'+'.$corresp[$time].'+'.$name;
}
else{
	echo "delete not success";
}
$conn->close();


?>