<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM chude ORDER BY rand(" . date("Ymd"). ") LIMIT 5";
	$data = mysqli_query($con, $query);

	$arraythemefortoday = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arraythemefortoday[] = $row;
	}
	print(json_encode($arraythemefortoday));
?>