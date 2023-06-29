<?php
	require "connect.php";

	$arraysong = array();

	// Playlist
	if(isset($_POST['idPlaylist'])){
		$idPlaylist = $_POST['idPlaylist'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idPlaylist', idPlaylist)";
	}

	// Genre
	if(isset($_POST['idTheLoai'])){
		$idTheLoai = $_POST['idTheLoai'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idTheLoai', idTheLoai)";
	}

	// Album
	if(isset($_POST['idAlbum'])){
		$idTheLoai = $_POST['idAlbum'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idAlbum', idAlbum)";
	}

	$data = mysqli_query($con, $query);
	
	while ($row = mysqli_fetch_assoc($data)) {
		$arraysong[] = $row;
	}
	print(json_encode($arraysong));
?>