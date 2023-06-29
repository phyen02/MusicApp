<?php
	require "connect.php";
	$query = "SELECT * FROM baihat ORDER BY LuotThich DESC LIMIT 5";
	$data = mysqli_query($con, $query);

	$arraycommonsong = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayalbumhot[] = $row;
	}
	print(json_encode($arrayalbumhot));
?>