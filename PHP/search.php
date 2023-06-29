<?php
	require "connect.php";

	$songarray = array();

	if (isset($_POST['key'])) {
		$key = $_POST['key'];
		$query = "SELECT * FROM baihat WHERE lower(tenBaiHat) LIKE '%key%'";
		$data = mysqli_query($con, $query);
		
		while ($row = mysqli_fetch_assoc($data)) {
			$songarray[] = $row;
		}
		print(json_encode($songarray));
	}
?>