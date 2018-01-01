(function(angular, app){
	var managementApp = angular.module("ManagementApp");
	managementApp.requires.push("angularMoment")
	managementApp.controller("TotalRevenueController", ["$scope", "TotalRevenueService", "moment", "urls", function($scope, TotalRevenueService, moment, urls){
				$scope.init = function(){
					$scope.periodType = "0";
					var now = moment();
					$scope.data = {
							date : now.toDate(),
							month : now.month(),
							quarter : now.quarters(),
							year : now.year(),
							hasData : false,
							dataList : null,
					}
					
				};
				
				$scope.onGetReportClick = function(){
					var data = {
							date : $scope.data.date,
							quarter : $scope.data.quarter,
							year : $scope.data.year,
							hasData : $scope.data.hasData,
							periodType: $scope.periodType
					}
					$scope.data.error = null;
					TotalRevenueService.post(urls.TOTAL_REVENUE_SERVICE_API, data, function(response){
						if (response.data != null){
							$scope.data.hasData = true;
							$scope.data.dataList = response.data;
						}
					}, function(error){
						$scope.data.hasData = false;
						$scope.data.error = "No data found";
					});
				};
				
				$scope.onPeriodTypeChanged = function(){
					$scope.data.hasData = false;
				}
			}]);
})(angular, app);