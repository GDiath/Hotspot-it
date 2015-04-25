<?php
$con=mysqli_connect("localhost","root","","flickr");
// Check connection
if (mysqli_connect_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
mysqli_set_charset($con, "utf8");

if(isset($_GET['tags'])){

	//With geo criteria search
	if(isset($_GET['latstart'])&&isset($_GET['latstop'])&&isset($_GET['longstart'])&&isset($_GET['longstop'])){
		$latstart = $_GET['latstart'];
		$latstop = $_GET['latstop'];
		$longstart = $_GET['longstart'];
		$longstop = $_GET['longstop'];
		$tags = $_GET['tags'];

		//the "Match-Against" approach
		$sql = "SELECT id FROM id_tag WHERE MATCH(tag) AGAINST ('".$tags."')";

		//the "Like" approach (too slow performance)
		/*$splitResults = explode(" ", $tags);
		
		$sql = "SELECT DISTINCT id FROM id_tag WHERE ";

		for($i=0;$i<count($splitResults)-1;$i++){
			$sql= $sql."tag LIKE '".$splitResults[$i]."%' OR ";
		}
		$sql = $sql."tag LIKE '".$splitResults[count($splitResults)-1]."%'";*/
		$result=mysqli_query($con,$sql);
		
		$r = array();
		// Numeric array
		if ($result){
			while($row = mysqli_fetch_array($result,MYSQLI_NUM)){
				$sql = "SELECT id,latitude,longitude,farm_id,server_id,secret FROM photos WHERE id=".$row[0];
				$result2 = mysqli_query($con, $sql);
				$row2 = mysqli_fetch_array($result2,MYSQLI_ASSOC);
				if($row2['latitude']>$latstart && $row2['latitude']<$latstop && $row2['longitude']> $longstart && $row2['longitude'] <$longstop ){
					$r[]= $row2;
				}
				mysqli_free_result($result2);
			}
		
			mysqli_free_result($result);
			echo json_encode( $r );
		}

	//Without geo criteria search
	}else{
		
		$tags = $_GET['tags'];

		//the "Match-Against" approach
		$sql = "SELECT id FROM id_tag WHERE MATCH(tag) AGAINST ('".$tags."')";

		//the "Like" approach for 1 word
		/*$splitResults = explode(" ", $tags);
		
		$sql = "SELECT DISTINCT id FROM id_tag WHERE ";

		for($i=0;$i<count($splitResults)-1;$i++){
			$sql= $sql."tag LIKE '".$splitResults[$i]."%' OR ";
		}
		$sql = $sql."tag LIKE '".$splitResults[count($splitResults)-1]."%'";*/
		$result=mysqli_query($con,$sql);
		
		$r = array();
		// Numeric array
		if ($result){
			$num_rows = mysqli_num_rows($result);
			$counter = 0;

			while($row = mysqli_fetch_array($result,MYSQLI_NUM)){
				$sql = "SELECT id,latitude,longitude,farm_id,server_id,secret FROM photos WHERE id=".$row[0];
			
				$result2 = mysqli_query($con, $sql);
			
	//		$time_start = microtime(true);	
				$row2 = mysqli_fetch_array($result2,MYSQLI_ASSOC);
	//		$time_end = microtime(true);
				$r[]= $row2;
				//mysqli_free_result($result2);
	//	$execution_time = ($time_end - $time_start);

	//	echo("\n <b>Total Execution Time:</b>" .$execution_time.' seconds');
			
				}
			}
			
			//mysqli_free_result($result);
			echo json_encode( $r );

		}
	}

mysqli_close($con);

?>