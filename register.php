<?php
	require "init.php";
	$name=$_POST["name"];
	$email=$_POST["email"];
	$user_name=$_POST["user_name"];
	$password=$_POST["password"];
	
	/* $name="first_user";
        $email="first@email.com";
        $user_name="first_name";
        $password="first_password";*/


	
	$sql="select * from user_info where email like'".$email."';";
	
	$result=mysqli_query($con,$sql);
	$response=array();
	
	if(mysqli_num_rows($result)>0){
		
		$code="reg failed";$message="user already exits";
		array_push($response,array("code"=>$code,"message"=>$message));
		header('Content-type:application/json');
		echo json_encode($response);	
	}
	else{
		$sql="insert into user_info values('".$name."','".$email."','".$user_name."','".$password."');";
		  $result=mysqli_query($con,$sql);
		$code="reg success";$message="now you can login";
                array_push($response,array("code"=>$code,"message"=>$message));
		header('Content-type:application/json');
                echo json_encode($response);
        }


	mysqli_close($con);

	
?>



