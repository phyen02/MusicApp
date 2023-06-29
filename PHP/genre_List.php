<?php
	require "connect.php";
	$query = "SELECT * FROM theloai";
	$data = mysqli_query($con, $query);


	$arraygenre = array();
	
	while ($row = mysqli_fetch_assoc($data)) {
		$arraygenre[] = $row;
	}
	print(json_encode($arraygenre));
?>