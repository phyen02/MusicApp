<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM casi";
	$data = mysqli_query($con, $query);

	$arraycasi = array();
	
	while ($row = mysqli_fetch_assoc($data)) {
		$arraycasi[] = $row;
	}
	print(json_encode($arraycasi));
?>