<?php
	require "init.php";
        $user_name=$_POST["user_name"];
        $password=$_POST["password"];

	/*$user_name="first_name";	$password="first_password";*/

	$sql="select name,email from user_info where user_name like '".$user_name."' and password like '".$password."';";
	$response=array();
	$result=mysqli_query($con,$sql);

	if(mysqli_num_rows($result)>0){
		
		$row=mysqli_fetch_row($result);
		$name=$row[0];$email=$row[1];
		$code="login success";
		array_push($response,array("code"=>$code,"name"=>$name,"email"=>$email));
		echo json_encode($response);
	}else{
		$code="login failed";$message="user not found..try again";
		array_push($response,array("code"=>$code,"message"=>$messasge));
		echo json_encode($response);
	
	}
	
	mysqli_close($con);

?>
