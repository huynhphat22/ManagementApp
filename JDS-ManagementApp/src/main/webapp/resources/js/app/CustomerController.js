'use strict';

angular.module('ManagementApp').controller('Customers', 
['CustomerService', '$scope', function (CustomerService, $scope) {

    var self = this;

    self.listCustomers = [];
    self.customer = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'customerId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveCustomer = false;
    
    self.resetPassword = resetPassword;

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


    findAllCustomers();

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
        self.page = parseInt(self.page) < self.listCustomers.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllCustomers();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllCustomers();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllCustomers();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllCustomers();
    }

    function search() {
        self.page = 1;
        $scope.searchCustomerForm.$setPristine();
        findAllCustomers();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "customerId";
        self.asc = true;
        self.searchText = null;
        findAllCustomers();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.customer = {};
        $scope.customerForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.customer.customerId === undefined || self.customer.customerId === null) {
            console.log('Saving New Customer', self.customer);
            save(self.customer);
        } else {
            update(self.customer);
            console.log('Customer updated with id ', self.customer.id);
        }
        $scope.customerForm.$setPristine();
    }


    function findAllCustomers() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        console.log("pageQuery", pageQuery);
        CustomerService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listCustomers = response;
            },
            (errors) => {
                console.log("Errors Find All Customer :", errors);
            });
    }

    function save(customer){
        CustomerService.save(customer)
        .then((response)=>{
            self.successMessage = 'Insert Customer Successfully!';
            self.errorMessage = '';
            findAllCustomers();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Customer!';
        });
    }

    function update(customer){
        CustomerService.update(customer)
        .then((response)=>{
            self.successMessage = 'Update Customer Successfully!';
            self.errorMessage = '';
            findAllCustomers();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Customer!';
        });
    }
    
    function resetPassword(id){
        CustomerService.resetPassword(id)
        .then((response)=>{
            self.successMessage = 'Reset Password For Customer With Id ' +id + ' Successfully!';
            self.errorMessage = '';
            findAllCustomers();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Reset Password For Customer With Id ' + id;
        });
    }

    function edit(id){
        console.log("zo");
        CustomerService.findById(id)
        .then((response)=>{
            self.customer =  response;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Customer!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveCustomer = true;
    }
}]);