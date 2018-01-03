<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				
				<div class="row calender widget-shadow">
					<h1 style="font-weight:bold" class="text-danger">HELLO, HAVE A NICE DAY ${username}</h1>
					<hr/>
					<div class="cal1">
						<h1 style="font-weight:bold; margin-top : 20px"class="text-primary text-center">Management App Of Group 7</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8 map widget-shadow">
						<h4 class="title">Visitors Map </h4>
						<div class="map_container"><div id="vmap" style="width: 100%; height: 354px;"></div></div>
						<!--map js-->
						<link href="css/jqvmap.css" rel='stylesheet' type='text/css' />
						<script src="js/jquery.vmap.js"></script>
						<script src="js/jquery.vmap.sampledata.js" type="text/javascript"></script>
						<script src="js/jquery.vmap.world.js" type="text/javascript"></script>
						<script type="text/javascript">
							jQuery(document).ready(function() {
								jQuery('#vmap').vectorMap({
									map: 'world_en',
									backgroundColor: '#fff',
									color: '#696565',
									hoverOpacity: 0.8,
									selectedColor: '#696565',
									enableZoom: true,
									showTooltip: true,
									values: sample_data,
									scaleColors: ['#585858', '#696565'],
									normalizeFunction: 'polynomial'
								});
							});
						</script>
						<!-- //map js -->
					</div>
					<div class="col-md-4 social-media widget-shadow">
						<div class="wid-social twitter">
							<div class="social-icon">
								<i class="fa fa-twitter text-light icon-xlg "></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">3.1 K</h3>
								<h4 class="counttype text-light">Tweets</h4>
							</div>
						</div>
						<div class="wid-social google-plus">
							<div class="social-icon">
								<i class="fa fa-google-plus text-light icon-xlg "></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">523</h3>
								<h4 class="counttype text-light">Circles</h4>
							</div>
						</div>
						<div class="wid-social facebook">
							<div class="social-icon">
								<i class="fa fa-facebook text-light icon-xlg "></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">1.06K</h3>
								<h4 class="counttype text-light">Likes</h4>
							</div>
						</div>
						<div class="wid-social dribbble">
							<div class="social-icon">
								<i class="fa fa-dribbble text-light icon-xlg "></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">1.6 K</h3>
								<h4 class="counttype text-light">Subscribers</h4>
							</div>
						</div>
						<div class="wid-social vimeo">
							<div class="social-icon">
								<i class="fa fa-vimeo-square text-light icon-xlg"> </i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">2.1 m</h3>
								<h4 class="counttype text-light">Contacts</h4>
							</div>
						</div>
						<div class="wid-social xing">
							<div class="social-icon">
								<i class="fa fa-xing text-light icon-xlg "></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">2525</h3>
								<h4 class="counttype text-light">Connections</h4>
							</div>
						</div>
						<div class="wid-social flickr">
							<div class="social-icon">
								<i class="fa fa-android text-light icon-xlg"></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">1221</h3>
								<h4 class="counttype text-light">Media</h4>
							</div>
						</div>
						<div class="wid-social yahoo">
							<div class="social-icon">
								<i class="fa fa-yahoo text-light icon-xlg"> Y!</i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">2525</h3>
								<h4 class="counttype text-light">Connections</h4>
							</div>
						</div>
						<div class="wid-social rss">
							<div class="social-icon">
								<i class="fa fa-rss text-light icon-xlg"></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">1523</h3>
								<h4 class="counttype text-light">Subscribers</h4>
							</div>
						</div>
						<div class="wid-social youtube">
							<div class="social-icon">
								<i class="fa fa-youtube text-light icon-xlg"></i>
							</div>
							<div class="social-info">
								<h3 class="number_counter bold count text-light start_timer counted">1523</h3>
								<h4 class="counttype text-light">Subscribers</h4>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>
					<div class="clearfix"> </div>
				</div>
				
				<div class="clearfix"> </div>
			</div>
		</div>