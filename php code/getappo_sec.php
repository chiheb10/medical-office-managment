<?php

require "com.php";

$id=$_GET['id'];
$mysql_qry="select id_doctor from secretaries where id like '$id';";
$result=mysqli_query($conn,$mysql_qry);
$row=mysqli_fetch_array($result);
$did=$row[0];
$sql="SELECT id,date,time,patient_firstname,patient_lastname,patient_phone,patient_age,matter,gender FROM reservation 
LEFT JOIN user 
ON reservation.id_patient=user.id_patient 
where id_doctor like '$did';" ;
//$sql="SELECT id,date,time,patient_firstname,patient_lastname,patient_phone,patient_age,matter FROM reservation where id_doctor like '$did';" ;
$corresp=array("app1"=>"8H","app2"=>"8H30","app3"=>"9H","app4"=>"9H30","app5"=>"10H","app6"=>"10H30","app7"=>"11H","app8"=>"11H30","app9"=>"14H","app10"=>"14H30","app11"=>"15H","app12"=>"15H30","app13"=>"16H","app14"=>"16H30","app15"=>"17H","app16"=>"17H30");
$stmt =$conn->prepare($sql);
$stmt->execute();
$stmt->bind_result($id,$date,$time,$fname,$lname,$phone,$age,$des,$gen);
$appo=array();
while($stmt->fetch()){
 $temp = array();
 $temp['id']= $id;
 $temp['date'] = $date; 
 $temp['time'] = $corresp[$time]; 
 $temp['firstname'] = $fname; 
 $temp['phone'] = $phone; 
 $temp['lastname'] = $lname;
 $temp['age'] = $age;
 $temp['matter'] = $des;
  $temp['gender'] = $gen;
 


 array_push($appo, $temp);
	
}

echo json_encode($appo);



?>