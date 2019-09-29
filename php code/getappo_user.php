<?php

require "com.php";

$id=$_GET['id'];

$sql="SELECT date,time,name1,address,phone FROM reservation 
LEFT JOIN doctors 
ON reservation.id_doctor=doctors.id_doctor where id_patient like '$id';" ;
$corresp=array("app1"=>"8H","app2"=>"8H30","app3"=>"9H","app4"=>"9H30","app5"=>"10H","app6"=>"10H30","app7"=>"11H","app8"=>"11H30","app9"=>"14H","app10"=>"14H30","app11"=>"15H","app12"=>"15H30","app13"=>"16H","app14"=>"16H30","app15"=>"17H","app16"=>"17H30");

$stmt =$conn->prepare($sql);
$stmt->execute();
$stmt->bind_result($date,$time,$name,$address,$phone);
$appo=array();
while($stmt->fetch()){
 $temp = array();
 //$temp['id']= $id;
 $temp['date'] = $date; 
 $temp['time'] = $corresp[$time]; 
 $temp['name'] = $name; 
 $temp['phone'] = $phone; 
 $temp['address'] = $address; 


 array_push($appo, $temp);
	
}

echo json_encode($appo);



?>