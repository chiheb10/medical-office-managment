<?php
require "com.php";
$app="";
$info=$_GET['info'];
$len=strlen($info);
$pos = strpos ($info ,"+" );
$did = substr($info,0,$pos);
$date = substr($info,$pos+1,$len);
$mysql_qry="select id from working where id_doctor like '$did' and date like '$date';";
$result=mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result)==0){
$mysql_qry1="insert into working (id_doctor, date, app1, app2, app3, app4, app5, app6, app7, app8, app9, app10, app11, app12, app13, app14, app15, app16) values ('$did','$date','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app','$app')";

$check= mysqli_query($conn,$mysql_qry1);

$stmt = $conn->prepare("SELECT  id,app1,app2,app3,app4,app5,app6,app7,app8,app9,app10,app11,app12,app13,app14,app15,app16 FROM working where date like '$date' and id_doctor like '$did' ;");
$stmt->execute();
$stmt->bind_result($id,$app1,$app2,$app3,$app4,$app5,$app6,$app7,$app8,$app9,$app10,$app11,$app12,$app13,$app14,$app15,$app16);
$scheduler=array();
while($stmt->fetch()){
	$temp = array();
 $temp['id']= $id;
 $temp['app1'] = $app1; 
 $temp['app2'] = $app2; 
 $temp['app3'] = $app3; 
 $temp['app4'] = $app4;
 $temp['app5'] = $app5;
 $temp['app6'] = $app6;
 $temp['app7'] = $app7;
 $temp['app8'] = $app8;
 $temp['app9'] = $app9;
 $temp['app10'] = $app10;
 $temp['app11'] = $app11;
 $temp['app12'] = $app12;
 $temp['app13'] = $app13;
 $temp['app14'] = $app14;
 $temp['app15'] = $app15;
 $temp['app16'] = $app16;


array_push($scheduler, $temp);}}
else{$stmt = $conn->prepare("SELECT  id,app1,app2,app3,app4,app5,app6,app7,app8,app9,app10,app11,app12,app13,app14,app15,app16 FROM working where date like '$date' and id_doctor like '$did' ;");
$stmt->execute();
$stmt->bind_result($id,$app1,$app2,$app3,$app4,$app5,$app6,$app7,$app8,$app9,$app10,$app11,$app12,$app13,$app14,$app15,$app16);
$scheduler=array();
while($stmt->fetch()){
	$temp = array();
 $temp['id']= $id;
 $temp['app1'] = $app1; 
 $temp['app2'] = $app2; 
 $temp['app3'] = $app3; 
 $temp['app4'] = $app4;
 $temp['app5'] = $app5;
 $temp['app6'] = $app6;
 $temp['app7'] = $app7;
 $temp['app8'] = $app8;
 $temp['app9'] = $app9;
 $temp['app10'] = $app10;
 $temp['app11'] = $app11;
 $temp['app12'] = $app12;
 $temp['app13'] = $app13;
 $temp['app14'] = $app14;
 $temp['app15'] = $app15;
$temp['app16'] = $app16;
array_push($scheduler, $temp);}}
	


echo json_encode($scheduler);

?>