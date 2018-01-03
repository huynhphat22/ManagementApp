(function(angular, app){
	angular.module("ManagementApp")
			.factory("TotalRevenueService", ["$http", "$q", function($http, $q){
				var factory = {
						get: get,
						post: post,
				};
				
				return factory;
				
				function get(url, param, successFunction , failedFunction = null, config = null){
					var defered = $q.defer();
					if (config !== null){
						$http.get(url, config).then(successFunction(response), failedFunction(error));
					}else {
						$http.get(url, {
							data: param
						}).then(successFunction, failedFunction);
					}
				}
				
				function post(url, param, successFunction, failedFunction = null, config = null){
					if (config !== null){
						$http.post(url, param, config).then(successFunction, failedFunction);
					}
					$http.post(url, param).then(successFunction, failedFunction);
				}
			}]);
})(angular, app);