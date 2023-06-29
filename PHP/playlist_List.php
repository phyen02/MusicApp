<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM playlist";
	$data = mysqli_query($con, $query);


	$arrayplaylistfortoday = array();
	
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayplaylistfortoday[] = $row;
	}
	print(json_encode($arrayplaylistfortoday));
?>