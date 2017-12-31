'use strict';

angular.module('ManagementApp').controller('Staffs', 
['StaffService','DepartmentService', '$scope', function (StaffService, DepartmentService , $scope) {

    var self = this;

    self.listStaffs = [];
    self.listDepartments = [];
    self.staff = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'staffId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveStaff = false;

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


    findAllStaffs();
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
        self.page = parseInt(self.page) < self.listStaffs.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllStaffs();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllStaffs();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllStaffs();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllStaffs();
    }

    function search() {
        self.page = 1;
        $scope.searchStaffForm.$setPristine();
        findAllStaffs();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "staffId";
        self.asc = true;
        self.searchText = null;
        findAllStaffs();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.staff = {};
        $scope.staffForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.staff.staffId === undefined || self.staff.staffId === null) {
            console.log('Saving New Staff', self.staff);
            save(self.staff);
        } else {
            update(self.staff);
            console.log('Staff updated with id ', self.staff.id);
        }
        $scope.staffForm.$setPristine();
    }

    function findAllDepartments(){
    	DepartmentService.findAll()
        .then((response) => {
            self.listDepartments = response;
            console.log("listdepartment", self.listDepartments);
        },
        (errors) => {
            console.log("Errors Find All Staff :", errors);
        });
    }

    function findAllStaffs() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        StaffService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listStaffs = response;
            },
            (errors) => {
                console.log("Errors Find All Staff :", errors);
            });
    }
    
    

    function save(staff){
        StaffService.save(staff)
        .then((response)=>{
            self.successMessage = 'Insert Staff Successfully!';
            self.errorMessage = '';
            findAllStaffs();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Staff!';
        });
    }

    function update(staff){
        StaffService.update(staff)
        .then((response)=>{
            self.successMessage = 'Update Staff Successfully!';
            self.errorMessage = '';
            findAllStaffs();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Staff!';
        });
    }

    function edit(id){
        console.log("zo");
        StaffService.findById(id)
        .then((response)=>{
            self.staff =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Staff!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveStaff = true;
    }
    
    function resetPassword(id){
        StaffService.resetPassword(id)
        .then((response)=>{
        	self.successMessage = 'Reset Password successfully';
        	self.errorMessage = '';
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Staff!';
        });
    }
    
}]);