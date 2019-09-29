<?php
require "com.php";
$sql="SELECT speciality FROM doctors";
		$result=mysqli_query($conn,$sql);
		while($e=mysqli_fetch_assoc($result)){
		$output[]=$e; 
		}
$name='chi';		

		echo json_encode($output); 
		mysqli_close();
?>