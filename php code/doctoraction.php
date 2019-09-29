<?php
require "com.php";
$sch_id=$_POST["sch_id"];
$num=$_POST["number"];
$action=$_POST["action"];
$mysql_qryt="select id_doctor,date from working where id like '$sch_id';";
	$resultt = mysqli_query($conn, $mysql_qryt);
 $rowt = mysqli_fetch_array($resultt);
 $did=$rowt[0];
  $date=$rowt[1];
  $sql_d="select username,name1 from doctors where id_doctor like '$did';";
  $res=mysqli_query($conn, $sql_d);
  $r=mysqli_fetch_array($res);
  $username=$r[0];
  $d_name=$r[1];
//echo $num.'+'.$sch_id.'+'.$action;
/*$sch_id="58";
$num="5";
$action="appo details";*/
$sql = "SHOW COLUMNS FROM working";
$result = mysqli_query($conn,$sql);

$temp1=array();
while($row = mysqli_fetch_array($result)){
    //echo $row['Field']."<br>";
	array_push($temp1,$row['Field']);	
}
$pos=intval($num)+2;
$app=$temp1[$pos];

if($action=="non working"){
	//$mysql_qry="UPDATE working SET $app = 'non working time' where id like '$sch_id';";
	$sq_test="select id_doctor,$app from working where id like '$sch_id';";
	$res_test=mysqli_query($conn,$sq_test);
	$row_test=  mysqli_fetch_array($res_test);
	$id_d=$row_test[0];
	$tp=$row_test[1];
	$mysql_qry="UPDATE working SET $app = 'non working time' where id like '$sch_id';";
	if($tp=="reserved"){
		$val1="nw";
		$mysql_qry1="select patient_firstname,patient_lastname,patient_phone from reservation where id_doctor like '$did' and date like '$date' and time like '$app';";
 $result1 = mysqli_query($conn, $mysql_qry1);
 $row1 = mysqli_fetch_array($result1);
 $val=$val1.'+'.$row1[0].'+'.$row1[1].'+'.$row1[2].'+'.$d_name;
 		
		}
	else{
		$val="w";
	}
	if($conn->query($mysql_qry)=== TRUE){
		echo $val.'+'.$username.'+'.$date;
	}
	else {echo "try again";}
}
else if($action=="working"){
	$mysql_qry="UPDATE working SET $app = '' where id like '$sch_id';";
	if($conn->query($mysql_qry)=== TRUE){
		echo "w".'+'.$username.'+'.$date;
	}
	else {echo "try again";}
}
else if($action=="appo details"){
	
 $mysql_qry1="select patient_firstname,patient_lastname,patient_phone,patient_age,matter from reservation where id_doctor like '$did' and date like '$date' and time like '$app';";
 $result1 = mysqli_query($conn, $mysql_qry1);
 $row1 = mysqli_fetch_array($result1);
 echo "a".'+'.$row1[0].'+'.$row1[1].'+'.$row1[2].'+'.$row1[3].'+'.$row1[4];
}

$conn->close();
?>