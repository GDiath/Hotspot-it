<?php
$con=mysqli_connect("localhost","root","","flickr");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
mysqli_set_charset($con, "utf8");
$result;

if(isset($_GET["limit"])){

	$myLimit = $_GET['limit'];
	$sql="SELECT latitude, longitude FROM Photos LIMIT ".$myLimit;
	$result=mysqli_query($con,$sql);
	mysqli_free_result($result);
	
	$r = array();
	
	while($row = mysqli_fetch_array($result,MYSQLI_ASSOC)){
		$r[] = $row;
	}

	echo json_encode( $r );
}
else if(isset($_GET['latstart'])&&isset($_GET['latstop'])&&isset($_GET['longstart'])&&isset($_GET['longstop'])) {
	$latstart = $_GET['latstart'];
	$latstop = $_GET['latstop'];
	$longstart = $_GET['longstart'];
	$longstop = $_GET['longstop'];
	if(!isset($_GET['window'])){
		$sql = "SELECT latitude, longitude FROM Photos WHERE latitude>".$latstart." AND latitude<".$latstop."AND longitude>".$longstart." AND longitude<".$longstop;
		$result=mysqli_query($con,$sql);

		$r = array();
		while($row = mysqli_fetch_array($result,MYSQLI_ASSOC)){
			$r[] = $row;
		}
		echo json_encode( $r );
	}
	else {
		$window = $_GET['window'];
		$xstep = ($latstop-$latstart)/$window;
		$ystep = ($longstop-$longstart)/$window;
		$resultset = array();
		//initialization of the data structure (all counter set to 0)
		for($i=0;$i<$window;$i++){
			$resultset[$i]=array();
			for($j=0;$j<$window;$j++){
				$resultset[$i][$j]=array();
				$x1 = $latstart + $i*$xstep;
				$x2 = $latstart + ($i+1)*$xstep;
				$y1 = $longstart + $j*$ystep;
				$y2 = $longstart + ($j+1)*$ystep;
				$resultset[$i][$j]['latstart']=$x1;
				$resultset[$i][$j]['latstop']=$x2;
				$resultset[$i][$j]['longstart']=$y1;
				$resultset[$i][$j]['longstop']=$y2;
				$resultset[$i][$j]['count']=0;// ==0 , it will be populated later with N complexity
				//$resultset[$i][$j]['tags']= "";
			}
		}
		// Now the query
		$sql = "SELECT latitude, longitude, id FROM Photos WHERE latitude>".$latstart." AND latitude<".$latstop."AND longitude>".$longstart." AND longitude<".$longstop;//Query String
		$result=mysqli_query($con,$sql);//Executed query MYSQLI API
		while($row = mysqli_fetch_array($result,MYSQLI_NUM)){ //Get next Array (iterator next)
			$lat1 = $row[0]; //latitude of the pin
			$lon1 = $row[1]; //longitude of the pin
			$diffx = $lat1-$latstart;//difference from the top left lat
			$diffy = $lon1-$longstart;// difference from the top left lon
			$indexx=$diffx/$xstep;//divided by the width per latitude
			$indexy=$diffy/$ystep;//divided by the width per longitude
			$tempcount=$resultset[intval($indexx)][intval($indexy)]['count'] +1;//get the value of the resultset that corresponds to the rectangle area the pin exists and add 1
			$resultset[intval($indexx)][intval($indexy)]['count'] = $tempcount; //set the new value		
		}

		
		echo json_encode($resultset);	
	}
	
}

mysqli_close($con);

?>