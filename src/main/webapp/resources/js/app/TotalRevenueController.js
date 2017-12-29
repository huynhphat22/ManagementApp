(function(angular, app){
	angular.module("ManagementApp")
			.controller("TotalRevenue", ["$scope", "TotalRevenueService", function($scope, TotalRevenue){
				$scope.init = function(){
					$scope.periodType = "0";
					var now = moment.now();
					$scope.date = now.format("DD/MM/YYYY");
					$scope.week = 1;
					$scope.month = now.month();
					$scope.quarter = now.quarter();
					$scope.year = now.year();
				};
			}]);
})(angular, app);