'use strict';

angular.module('ManagementApp').controller('Foods', 
['FoodService', '$scope', function (FoodService, $scope) {

    var self = this;

    self.listFoods = [];
    self.food = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'foodId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveFood = false;

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
    self.changeRecordsPerPage = changeRecordsPerPage;


    findAllFoods();

    function range(min, max, step) {
        step = step || 1;
        max = max || 1;
        var input = [];
        for (var i = min; i <= max; i += step) {
            input.push(i);
        }
        return input;
    };


    function indexOrder(index) {
        return (parseInt(index) + 1) + ((parseInt(self.page) - 1) * parseInt(self.size))
    }

    function increasePage() {
        self.page = parseInt(self.page) < self.listFoods.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllFoods();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllFoods();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllFoods();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllFoods();
    }

    function search() {
        self.page = 1;
        $scope.searchFoodForm.$setPristine();
        findAllFoods();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "foodId";
        self.asc = true;
        self.searchText = null;
        findAllFoods();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.food = {};
        $scope.foodForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.food.foodId === undefined || self.food.foodId === null) {
            console.log('Saving New Food', self.food);
            save(self.food);
        } else {
            update(self.food);
            console.log('Food updated with id ', self.food.id);
        }
        $scope.foodForm.$setPristine();
    }


    function findAllFoods() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        FoodService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listFoods = response;
            },
            (errors) => {
                console.log("Errors Find All Food :", errors);
            });
    }

    function save(food){
        FoodService.save(food)
        .then((response)=>{
            self.successMessage = 'Insert Food Successfully!';
            self.errorMessage = '';
            findAllFoods();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Food!';
        });
    }

    function update(food){
        FoodService.update(food)
        .then((response)=>{
            self.successMessage = 'Update Food Successfully!';
            self.errorMessage = '';
            findAllFoods();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Food!';
        });
    }

    function edit(id){
        console.log("zo");
        FoodService.findById(id)
        .then((response)=>{
            self.food =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Food!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveFood = true;
    }
}]);