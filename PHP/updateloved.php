<?php
	require "connect.php";

	$LuotThich = "1";
	$idBaiHat = $_POST['idBaiHat'];

	$query = "SELECT LuotThich FROM baihat WHERE idBaiHat = '$idBaiHat'";
	$dataLuotThich = mysqli_query($con,$query);

	$row = mysqli_fetch_assoc($dataLuotThich);
	$tongLuotThich = $row['LuotThich'];

	if(isset($LuotThich)){
		$tongLuotThich += $LuotThich;
		$querySum = "UPDATE baihat SET LuotThich = '$tongLuotThich' WHERE idBaiHat = '$idBaiHat'";
		$dataUpdate = mysqli_query($con, $querySum);
		if ($dataUpdate) {
			echo "Success";
		} else {
			echo "Fail";
		}
	}
?>