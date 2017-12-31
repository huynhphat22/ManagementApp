(function(angular, app){
	var managementApp = angular.module("ManagementApp");
	managementApp.requires.push("angularMoment")
	managementApp.controller("TotalRevenueController", ["$scope", "TotalRevenueService", "moment", "urls", function($scope, TotalRevenueService, moment, urls){
				$scope.init = function(){
					$scope.periodType = "0";
					var now = moment();
					$scope.date = now.toDate();
					$scope.week = 1;
					$scope.month = now.month();
					$scope.quarter = now.quarters();
					$scope.year = now.year();
					$scope.showReport = false;
					$scope.hasData = false;
					$scope.dataList = null;
				};
				
				$scope.onGetReportClick = function(){
					var data = {
							date : $scope.date,
							week : $scope.week,
							month : $scope.month,
							quarter : $scope.quarter,
							year : $scope.year,
							showReport : $scope.showReport,
							hasData : $scope.hasData,
					}
					TotalRevenueService.post(urls.TOTAL_REVENUE_SERVICE_API, data, function(response){
						
					}, function(error){
						
					});
				};
			}]);
})(angular, app);