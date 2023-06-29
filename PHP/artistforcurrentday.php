<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM casi ORDER BY rand(" . date("Ymd"). ") LIMIT 5";
	$data = mysqli_query($con, $query);

	$arrayartist = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayartist[] = $row;
	}
	print(json_encode($arrayartist));
?>