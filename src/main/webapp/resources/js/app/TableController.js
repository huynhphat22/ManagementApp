'use strict';

angular.module('ManagementApp').controller('Tables', 
['TableService', '$scope', function (TableService, $scope) {

    var self = this;

    self.listTables = [];
    self.table = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'tableId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveTable = false;

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
    const url = new URL(window.location.href);
    
    self.departmentId = url.searchParams.get("deptId");


    findAllTables();

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
        self.page = parseInt(self.page) < self.listTables.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllTables();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllTables();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllTables();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllTables();
    }

    function search() {
        self.page = 1;
        $scope.searchTableForm.$setPristine();
        findAllTables();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "tableId";
        self.asc = true;
        self.searchText = null;
        findAllTables();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.table = {};
        $scope.saveTableForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.table.tableId === undefined || self.table.tableId === null) {
            console.log('Saving New Table', self.table);
            save(self.table);
        } else {
            update(self.table);
            console.log('Table updated with id ', self.table.id);
        }
        $scope.saveTableForm.$setPristine();
    }


    function findAllTables() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        TableService.findAllByPagination(pageQuery, self.departmentId)
            .then((response) => {
                self.listTables = response;
            },
            (errors) => {
                console.log("Errors Find All Table :", errors);
            });
    }

    function save(table){
        TableService.save(table)
        .then((response)=>{
            self.successMessage = 'Insert Table Successfully!';
            self.errorMessage = '';
            findAllTables();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Table!';
        });
    }

    function update(table){
        TableService.update(table)
        .then((response)=>{
            self.successMessage = 'Update Table Successfully!';
            self.errorMessage = '';
            findAllTables();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Table!';
        });
    }

    function edit(id){
        console.log("zo : " + id);
        TableService.findById(id)
        .then((response)=>{
            self.table =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Table!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveTable = true;
    }
}]);