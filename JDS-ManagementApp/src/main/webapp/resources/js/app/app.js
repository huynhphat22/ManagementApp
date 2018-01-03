
var app = angular.module("ManagementApp", []);

app.constant('urls',{
	BASE : "http://localhost:8080/JDS-ManagementApp/",
	DEPARTMENT_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/department/",
	CATEGORY_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/category/",
	FOOD_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/food/",
	MENU_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/menuDepartment/",
	TABLE_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/restaurantTable/",
	STAFF_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/staff/",
	ORDER_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/orderFood/",
	CUSTOMER_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/customer/",
	DATECOST_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/dateCost/",
	MONTHCOST_SERVICE_API : "http://localhost:8080/JDS-ManagementApp/api/monthCost/"
	
});