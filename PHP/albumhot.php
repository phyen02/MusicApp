<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM album ORDER BY rand(" . date("Ymd"). ") LIMIT 5";
	$data = mysqli_query($con, $query);

	$arrayalbumhot = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayalbumhot[] = $row;
	}
	print(json_encode($arrayalbumhot));
?>