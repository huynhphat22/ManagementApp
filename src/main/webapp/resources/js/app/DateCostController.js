'use strict';

angular.module('ManagementApp').controller('DateCosts', 
['DateCostService', '$scope', function (DateCostService, $scope) {

    var self = this;

    self.listDateCosts = [];
    self.dateCost = {};
   
    
    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'id.departmentId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveDateCost = false;
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
    
    

    findAllDateCosts();

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
        self.page = parseInt(self.page) < self.listDateCosts.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllDateCosts();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllDateCosts();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllDateCosts();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllDateCosts();
    }

    function search() {
        self.page = 1;
        $scope.searchDateCostForm.$setPristine();
        findAllDateCosts();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "dateCostId";
        self.asc = true;
        self.searchText = null;
        findAllDateCosts();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.isUpdate = false;
        self.dateCost = {};
        self.dateCost.id.departmentId = document.getElementById("deptId").value;
        $scope.dateCostForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (!self.dateCost.id) {
            console.log('Saving New DateCost', self.dateCost);
            save(self.dateCost);
        } else {
            update(self.dateCost);
            console.log('DateCost updated with id ', self.dateCost.id);
        }
        $scope.dateCostForm.$setPristine();
    }


    function findAllDateCosts() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        
        DateCostService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listDateCosts = response;
            },
            (errors) => {
                console.log("Errors Find All DateCost :", errors);
            });
    }

    function save(dateCost){
        DateCostService.save(dateCost)
        .then((response)=>{
            self.successMessage = 'Insert DateCost Successfully!';
            self.errorMessage = '';
            findAllDateCosts();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert DateCost!';
        });
    }

    function update(dateCost){
        DateCostService.update(dateCost)
        .then((response)=>{
            self.successMessage = 'Update DateCost Successfully!';
            self.errorMessage = '';
            findAllDateCosts();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update DateCost!';
        });
    }

    function edit(departmentId, dateOfCost){
        console.log("zo");
        const id = {
        		dateOfCost : dateOfCost,
        		departmentId : departmentId
        };
        console.log("id" ,id);
        DateCostService.findById(id)
        .then((response)=>{
            self.dateCost =  response;
            console.log("date cost",self.dateCost);
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting DateCost!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveDateCost = true;
    }
}]);