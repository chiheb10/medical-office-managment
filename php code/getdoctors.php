<?php

require "com.php";

$rspec=$_GET['rspec'];
$len=strlen($rspec);
$pos = strpos ($rspec ,"+" );
$region = substr($rspec,0,$pos);
$spec = substr($rspec,$pos+1,$len);


$stmt = $conn->prepare("SELECT id_doctor,name1,phone,address FROM doctors where speciality like '$spec' and region like '$region' ;");
$stmt->execute();
$stmt->bind_result($id,$name,$phone,$address);
$doctors=array();
while($stmt->fetch()){
 $temp = array();
 
 $temp['id']= $id;
 $temp['name'] = $name; 
 $temp['phone'] = $phone; 
 $temp['address'] = $address; 


 array_push($doctors, $temp);
	
}

echo json_encode($doctors);



?>