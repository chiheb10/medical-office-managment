<?php
require "com.php";
$app="";
$info=$_GET['info'];
$len=strlen($info);
$pos = strpos ($info ,"+" );
$username = substr($info,0,$pos);
$date = substr($info,$pos+1,$len);
$sql="select id_doctor from doctors where username like '$username'";
$sql1=mysqli_query($conn,$sql);
 $row = mysqli_fetch_array($sql1);
 $did=$row[0];

 $app="";
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
else{
	$sqli = "SHOW COLUMNS FROM working";
$resulti = mysqli_query($conn,$sqli);

$temp1=array();
while($rowi = mysqli_fetch_array($resulti)){
    //echo $row['Field']."<br>";
array_push($temp1,$rowi['Field']);}
$d="1";

$stmt = $conn->prepare("SELECT  id,app1,app2,app3,app4,app5,app6,app7,app8,app9,app10,app11,app12,app13,app14,app15,app16 FROM working where date like '$date' and id_doctor like '$did' ;");
$stmt->execute();
$stmt->bind_result($id,$app1,$app2,$app3,$app4,$app5,$app6,$app7,$app8,$app9,$app10,$app11,$app12,$app13,$app14,$app15,$app16);
$scheduler=array();
$stock=array($app1,$app2,$app3,$app4,$app5,$app6,$app7,$app8,$app9,$app10,$app11,$app12,$app13,$app14,$app15,$app16);

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
array_push($scheduler, $temp);}
$stock=array('app1','app2','app3','app4','app5','app6','app7','app8','app9','app10','app11','app12','app13','app14','app15','app16');
$ti=array("app1","app2","app3","app4","app5","app6","app7","app8","app9","app10","app11","app12","app13","app14","app15","app16");
for ($i = 0; $i <16; $i++){
	$t=$ti[$i];
	$gender=array("male"=>"Mr","female"=>"Ms");
	if($scheduler[0][$stock[$i]]=="reserved"){
		$sql2=" select id_patient,patient_firstname,patient_lastname from reservation where date like '$date' and time like '$t' and id_doctor like '$did';";
		$resultat=mysqli_query($conn,$sql2);
		 $rows = mysqli_fetch_array($resultat);
		 $sqlt="select gender from user where id_patient like '$rows[0]';";
		 $resultatt=mysqli_query($conn,$sqlt);
		 $rowst = mysqli_fetch_array($resultatt);
		 $scheduler[0][$stock[$i]]=$gender[$rowst[0]].' '.$rows[2].' '.$rows[1];		 
}}


}
	


echo json_encode($scheduler);

?>