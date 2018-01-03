'use strict';

angular.module('ManagementApp').controller('Departments', 
['DepartmentService', '$scope', function (DepartmentService, $scope) {

    var self = this;

    self.listDepartments = [];
    self.department = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'departmentId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveDepartment = false;

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


    findAllDepartments();

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
        self.page = parseInt(self.page) < self.listDepartments.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllDepartments();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllDepartments();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllDepartments();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllDepartments();
    }

    function search() {
        self.page = 1;
        $scope.searchDepartmentForm.$setPristine();
        findAllDepartments();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "departmentId";
        self.asc = true;
        self.searchText = null;
        findAllDepartments();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.department = {};
        $scope.departmentForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.department.departmentId === undefined || self.department.departmentId === null) {
            console.log('Saving New Department', self.department);
            save(self.department);
        } else {
            update(self.department);
            console.log('Department updated with id ', self.department.id);
        }
        $scope.saveDepartmentForm.$setPristine();
    }


    function findAllDepartments() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        console.log("pageQuery", pageQuery);
        DepartmentService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listDepartments = response;
            },
            (errors) => {
                console.log("Errors Find All Department :", errors);
            });
    }

    function save(department){
        DepartmentService.save(department)
        .then((response)=>{
            self.successMessage = 'Insert Department Successfully!';
            self.errorMessage = '';
            findAllDepartments();
            self.department = {};
            self.saveDepartment = false;
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Department!';
        });
    }

    function update(department){
        DepartmentService.update(department)
        .then((response)=>{
            self.successMessage = 'Update Department Successfully!';
            self.errorMessage = '';
            findAllDepartments();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Department!';
        });
    }

    function edit(id){
        console.log("zo");
        DepartmentService.findById(id)
        .then((response)=>{
            self.department =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Department!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveDepartment = true;
    }
}]);