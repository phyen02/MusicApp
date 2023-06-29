<?php
	$hostname = "localhost";
	$username = "id20832040_phyen02";
	$password = "01032002aK@";
	$dtbname = "id20832040_musicapp";

	$con = mysqli_connect($hostname, $username, $password, $dtbname);

	// Trả về tiếng việt
	mysqli_query($con, "SET NAMES 'utf8'");
?>