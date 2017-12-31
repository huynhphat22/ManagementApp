
var app = angular.module("ManagementApp", []);

app.constant('urls',{
	BASE : "http://localhost:8080/JDS-ManagementApp/",
	DEPARTMENT_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/department/",
	CATEGORY_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/category/",
	FOOD_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/food/",
	TOTAL_REVENUE_SERVICE_API: window.location.protocol + "//" + window.location.host + "/JDS-ManagementApp" + "/api/report/totalrevenue",
});