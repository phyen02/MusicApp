<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM theloai ORDER BY rand(" . date("Ymd"). ") LIMIT 5";
	$data = mysqli_query($con, $query);

	$arraygenrefortoday = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arraygenrefortoday[] = $row;
	}
	print(json_encode($arraygenrefortoday));
?>