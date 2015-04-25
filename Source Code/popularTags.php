<?php
$con=mysqli_connect("localhost","root","","flickr");
// Check connection
if (mysqli_connect_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
	
	if(isset($_GET['latstart'])&&isset($_GET['latstop'])&&isset($_GET['longstart'])&&isset($_GET['longstop'])) {

	$latstart = $_GET['latstart'];
	$latstop = $_GET['latstop'];
	$longstart = $_GET['longstart'];
	$longstop = $_GET['longstop'];

	mysqli_set_charset($con, "utf8");
	$sql = "SELECT id_tag.tag, COUNT(id_tag.tag)
			FROM Photos, id_tag
			WHERE latitude>".$latstart." AND latitude<".$latstop."AND longitude>".$longstart." AND longitude<".$longstop."AND photos.id = id_tag.id
			GROUP BY id_tag.tag 
			ORDER BY count(id_tag.tag) DESC";
			
	$result=mysqli_query($con,$sql);

	$r = array();
	while($row = mysqli_fetch_array($result)){
		
		$temp=array();
		$temp['tag'] = $row[0];
		$temp['tf'] = $row[1];
		$r[] = $temp;
	}

	echo json_encode( $r, JSON_UNESCAPED_UNICODE);
}

?>