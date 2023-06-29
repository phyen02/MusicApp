<?php
	require "connect.php";
	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(" . date("Ymd"). ") LIMIT 3";
	$data = mysqli_query($con, $query);

	/*class PlaylistToday{
		function PlaylistToday($idplaylist, $ten, $hinh, $icon){
			$this -> IdPlaylist = $idplaylist;
			$this -> Ten = $ten;
			$this -> HinhPlaylist = $hinh;
			$this -> Icon = $icon;
		}
	}*/

	$arrayplaylistfortoday = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$arrayplaylistfortoday[] = $row;
		/*array_push($arrayplaylistfortoday, new PlaylistToday($row['idPlaylist']
															,$row['tenPlaylist']
															,$row['hinhPlaylist']
															,$row['iconPlaylist']));*/
	}
	/*print(json_encode($arrayplaylistfortoday));*/
	echo json_encode($arrayplaylistfortoday);
?>