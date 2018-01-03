'use strict';

angular.module('ManagementApp').controller('Menus', 
['MenuService', '$scope', function (MenuService, $scope) {

    var self = this;

    self.listMenus = [];
    self.menu = {};
   
    
    self.page = 1;
    self.asc = true;
    self.searchText = null;
    self.searchBy = null;
    self.size = 10;
    self.sortBy = 'id.departmentId'

    self.successMessage = '';
    self.errorMessage = '';

    self.saveMenu = false;
    self.isUpdate = false;
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

    findAllMenus();

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
        self.page = parseInt(self.page) < self.listMenus.totalPages ? parseInt(self.page) + 1 : parseInt(self.page);
        console.log(self.page);
        findAllMenus();
    }

    function decreasePage() {
        self.page = parseInt(self.page) > 1 ? parseInt(self.page) - 1 : parseInt(self.page);
        findAllMenus();
    }

    function changeRecordsPerPage() {
        self.page = 1;
        console.log(self.size);
        findAllMenus();
    }

    function changePage(x) {
        if (typeof x != "undefined") {
            self.asc = (self.sortBy === x) ? !self.asc : false;
            self.sortBy = x;
        }
        console.log("self when change" + self.page);
        findAllMenus();
    }

    function search() {
        self.page = 1;
        $scope.searchMenuForm.$setPristine();
        findAllMenus();
    }

    function reload() {
        self.page = 1;
        self.size = 10;
        self.sortBy = "menuId";
        self.asc = true;
        self.searchText = null;
        findAllMenus();
    }

    function reset() {
        self.successMessage = '';
        self.errorMessage = '';
        self.isUpdate = false;
        self.menu = {};
        self.menu.id.departmentId = self.departmentId;
        $scope.menuForm.$setPristine(); //reset Form
    }

    function submit() {
    	self.menu.id.departmentId = self.departmentId;
        console.log('Submitting');
        if (!self.isUpdate) {
            console.log('Saving New Menu', self.menu);
            save(self.menu);
        } else {
            update(self.menu);
            console.log('Menu updated with id ', self.menu.id);
        }
        $scope.menuForm.$setPristine();
    }


    function findAllMenus() {
    	if(!self.departmentId){
    		return;
    	}
        var pageQuery = {
            sortBy : self.sortBy,
            page : self.page,
            asc : self.asc,
            searchBy : self.searchBy,
            searchText : self.searchText,
            size : self.size
        }
        
        MenuService.findAllByPagination(pageQuery, self.departmentId)
            .then((response) => {
                self.listMenus = response;
            },
            (errors) => {
                console.log("Errors Find All Menu :", errors);
            });
    }

    function save(menu){
        MenuService.save(menu)
        .then((response)=>{
            self.successMessage = 'Insert Menu Successfully!';
            self.errorMessage = '';
            findAllMenus();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Insert Menu!';
        });
    }

    function update(menu){
        MenuService.update(menu)
        .then((response)=>{
            self.successMessage = 'Update Menu Successfully!';
            self.errorMessage = '';
            findAllMenus();
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Update Menu!';
        });
    }

    function edit(foodId, departmentId){
        console.log("zo");
        const id = {
        		foodId : foodId,
        		departmentId : departmentId
        };
        console.log("id" ,id);
        MenuService.findById(id)
        .then((response)=>{
            self.menu =  response;
            console.log("meni",self.menu);
            self.isUpdate = true;
            
        },(errors)=>{
            self.successMessage = '';
            self.errorMessage = 'Error When Getting Menu!';
        });
        
        $("html , body").animate({
            scrollTop: 0
        }, 300);
        
        self.saveMenu = true;
    }
}]);