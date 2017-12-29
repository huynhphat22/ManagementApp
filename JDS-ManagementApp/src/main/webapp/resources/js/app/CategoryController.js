'use strict';

angular.module('ManagementApp').controller('Categories', 
['CategoryService', '$scope', function (CategoryService, $scope) {

    var self = this;

    self.listCategories = [];
    self.category = {};

    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'categoryId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveCategory = false;

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


    findAllCategories();

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
        self.page = parseInt(self.page) < self.listCategories.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllCategories();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllCategories();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllCategories();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllCategories();
    }

    function search() {
        self.page = 1;
        $scope.searchCategoryForm.$setPristine();
        findAllCategories();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "categoryId";
        self.asc = true;
        self.searchText = null;
        findAllCategories();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.category = {};
        $scope.categoryForm.$setPristine(); //reset Form
    }

    function submit() {
        console.log('Submitting');
        if (self.category.categoryId === undefined || self.category.categoryId === null) {
            console.log('Saving New Category', self.category);
            save(self.category);
        } else {
            update(self.category);
            console.log('Category updated with id ', self.category.id);
        }
        $scope.categoryForm.$setPristine();
    }


    function findAllCategories() {
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        CategoryService.findAllByPagination(pageQuery)
            .then((response) => {
                self.listCategories = response;
            },
            (errors) => {
                console.log("Errors Find All Category :", errors);
            });
    }

    function save(category){
        CategoryService.save(category)
        .then((response)=>{
            self.successMessage = 'Insert Category Successfully!';
            self.errorMessage = '';
            findAllCategories();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Category!';
        });
    }

    function update(category){
        CategoryService.update(category)
        .then((response)=>{
            self.successMessage = 'Update Category Successfully!';
            self.errorMessage = '';
            findAllCategories();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Category!';
        });
    }

    function edit(id){
        CategoryService.findById(id)
        .then((response)=>{
            self.category =  response;
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Category!';
        });

<<<<<<< HEAD
        $("html , body").animate({
            scrollTop: 0
        }, 300);
=======
>>>>>>> refs/remotes/origin/1412163
        self.saveCategory = true;
    }
}]);