<?php
	require "connect.php";
	$query = "SELECT * FROM album";
	$data = mysqli_query($con, $query);


	$arrayalbum = array();
	
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayalbum[] = $row;
	}
	
	print(json_encode($arrayalbum));
?>