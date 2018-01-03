'use strict';

angular.module('ManagementApp').controller('MonthCosts', 
['MonthCostService', '$scope', function (MonthCostService, $scope) {

    var self = this;

    self.listMonthCosts = [];
    self.monthCost = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'monthCostId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveMonthCost = false;

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


    findAllMonthCosts();

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
        self.page = parseInt(self.page) < self.listMonthCosts.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllMonthCosts();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllMonthCosts();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllMonthCosts();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllMonthCosts();
    }

    function search() {
        self.page = 1;
        $scope.searchMonthCostForm.$setPristine();
        findAllMonthCosts();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "monthCostId";
        self.asc = true;
        self.searchText = null;
        findAllMonthCosts();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.monthCost = {};
        $scope.monthCostForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.monthCost.monthCostId === undefined || self.monthCost.monthCostId === null) {
            console.log('Saving New MonthCost', self.monthCost);
            save(self.monthCost);
        } else {
            update(self.monthCost);
            console.log('MonthCost updated with id ', self.monthCost.monthCostId);
        }
        $scope.monthCostForm.$setPristine();
    }


    function findAllMonthCosts() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        console.log("pageQuery", pageQuery);
        MonthCostService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listMonthCosts = response;
            },
            (errors) => {
                console.log("Errors Find All MonthCost :", errors);
            });
    }

    function save(monthCost){
        MonthCostService.save(monthCost)
        .then((response)=>{
            self.successMessage = 'Insert MonthCost Successfully!';
            self.errorMessage = '';
            findAllMonthCosts();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert MonthCost!';
        });
    }

    function update(monthCost){
        MonthCostService.update(monthCost)
        .then((response)=>{
            self.successMessage = 'Update MonthCost Successfully!';
            self.errorMessage = '';
            findAllMonthCosts();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update MonthCost!';
        });
    }

    function edit(id){
        console.log("zo");
        MonthCostService.findById(id)
        .then((response)=>{
            self.monthCost =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting MonthCost!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveMonthCost = true;
    }
}]);