'use strict';

angular.module('ManagementApp').controller('SwitchBoardOrders', 
['OrderService', 'MenuService', '$scope', function (OrderService, MenuService, $scope) {

    var self = this;

    self.listOrders = [];
    self.listOrderDetails = [];
    self.listFoods = [];
    self.order = {};
    
    self.orderDetail = {};
    self.cart = {
    		listFoodInfo : [],
    		orderInfo : {}
    }

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'orderId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveOrder = true;
    self.updateOrder = false;
    self.showOrderDetail = false;

    self.edit = edit;

    self.range = range;
    self.indexOrder = indexOrder;
    self.increasePage = increasePage;
    self.decreasePage = decreasePage;
    self.changePage = changePage;
    self.search = search;
    self.reload = reload;
    self.reset = reset;
    self.submit = submit;
    self.editOrder = editOrder;
    self.addToCart = addToCart;
    self.calCartTotal = calCartTotal;
    self.removeOrderDetail = removeOrderDetail;
    self.changeRecordsPerPage = changeRecordsPerPage;
    self.parseJsonDate = parseJsonDate;


    findAllFoodInDepartment();
    findAllOrders();

    function removeOrderDetail(foodId){
    	for(var i=0; i < self.cart.listFoodInfo.length; i++){
    		if(foodId === self.cart.listFoodInfo[i].foodId ){
    			console.log("co zo ne");
    			self.cart.listFoodInfo.splice(i, 1);
    			return;
    		}
    	}
    }
    function calCartTotal(){
    	var total = 0;
    	for(var i=0; i < self.cart.listFoodInfo.length; i++){
    		total = parseInt(total) + parseInt(self.cart.listFoodInfo[i].quantity) * parseInt(self.cart.listFoodInfo[i].price);
    	}
    	return total;
    }
    function addToCart(){
    	if(self.orderDetail.foodId === undefined ||self.orderDetail.foodId === null || self.orderDetail.quantity < 1 ){
    		return;
    	}
    	console.log("zo zo");
    	for(var i=0; i < self.cart.listFoodInfo.length; i++){
    		if(self.orderDetail.foodId === self.cart.listFoodInfo[i].foodId ){
    			self.cart.listFoodInfo[i].quantity = parseInt(self.orderDetail.quantity) + parseInt(self.cart.listFoodInfo[i].quantity);
    			return;
    		}
    	}
    	
    	for(var i=0 ; i < self.listFoods.length;i++){
    		if(self.orderDetail.foodId == self.listFoods[i].id.foodId){
    			self.cart.listFoodInfo.push({
    				foodId : self.listFoods[i].id.foodId,
    				foodName : self.listFoods[i].food.foodName,
    				price : self.listFoods[i].price,
    				quantity : self.orderDetail.quantity,
    				image : self.listFoods[i].image
    			});
    			console.log("day la list food", self.cart.listFoodInfo);
    			self.orderDetail = {};
    			return;
    		}
    	}
    }
    
    function range(min, max, step) {
        step = step || 1;
        max = max || 1;
        var input = [];
        for (var i = min; i <= max; i += step) {
            input.push(i);
        }
        return input;
    };


    function parseJsonDate(jsonDateString){
        return new Date(parseInt(jsonDateString));
    }
    
    function indexOrder(index) {
        return (parseInt(index) + 1) + ((parseInt(self.page) - 1) * parseInt(self.size))
    }

    function increasePage() {
        self.page = parseInt(self.page) < self.listOrders.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllOrders();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllOrders();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllOrders();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllOrders();
    }

    function search() {
        self.page = 1;
        $scope.searchOrderForm.$setPristine();
        findAllOrders();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "orderId";
        self.asc = true;
        self.searchText = null;
        findAllOrders();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.order = {};
        $scope.orderForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        self.cart.orderInfo.totalPrice = calCartTotal();
        self.cart.orderInfo.orderType = 'SwitchBoard';
        console.log("Day la cart", self.cart);
        
        if(self.order.orderId !== undefined && self.order.orderId !== null){
        	update(self.order);
        }
        else if (self.cart.listFoodInfo.length === 0) {
            self.successMessage = '';
            self.errorMessage = 'The Cart Is Empty!';
            return;
            
        } else {
        	save(self.cart);
            console.log('Save cart ');
        }
        $scope.addOrderForm.$setPristine();
        $scope.updateOrderForm.$setPristine();
    }


    function findAllOrders() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        console.log("pageQuery", pageQuery);
        OrderService.findAllByDepartmentPaginationSwitchBoard(pageQuery)
            .then((response) => {
                self.listOrders = response;
            },
            (errors) => {
                console.log("Errors Find All Order :", errors);
            });
    }
    
    function findAllFoodInDepartment(){
    	MenuService.findAllByDepartmentId()
        .then((response) => {
            self.listFoods = response;
        },
        (errors) => {
            console.log("Errors Find All Order :", errors);
        });
    }

    function save(order){
        OrderService.save(order)
        .then((response)=>{
            self.successMessage = 'Save Cart Successfully!';
            self.errorMessage = '';
            self.cart = {
            		listFoodInfo : [],
            		orderInfo : {}
            }
            findAllOrders();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Order!';
        });
    }

    function update(order){
        OrderService.update(order)
        .then((response)=>{
            self.successMessage = 'Update Order Successfully!';
            self.errorMessage = '';
            findAllOrders();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Order!';
        });
    }

    function edit(id){
        console.log("zo" + id);
        OrderService.findAllOrderDetailByOrderId(id)
        .then((response)=>{
            self.listOrderDetails =  response;
            console.log("response day ne", response);
            self.saveOrder = false;
            self.showOrderDetail = true;
            self.updateOrder = false;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Order!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveOrder = true;
    }
    
    function editOrder(id){
    	OrderService.findById(id)
        .then((response)=>{
            self.order =  response;
            self.order.dateDelivery = parseJsonDate(self.order.dateDelivery);
            console.log("response day ne", response);
            self.saveOrder = false;
            self.showOrderDetail = false;
            self.updateOrder = true;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Order!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveOrder = true;
    }
}]);