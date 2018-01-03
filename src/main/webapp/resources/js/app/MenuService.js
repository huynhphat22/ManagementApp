'use strict';

angular.module('ManagementApp').factory('MenuService', ['$http', '$q', 'urls', function($http, $q, urls) {

    var factory = {
        findAll: findAll,
        findAllByPagination : findAllByPagination,
        findById: findById,
        save: save,
        update: update,
        findAllByDepartmentId : findAllByDepartmentId
    };


    return factory;

    function findAllByDepartmentId(){
    	 var deferred = $q.defer();
         $http.get(urls.MENU_SERVICE_API + "department")
             .then((response) => {
                 deferred.resolve(response.data);
             }, (errors) => {
                 deferred.reject(errors);
             });

         return deferred.promise;
    }

    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.MENU_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery, departmentId){
        var deferred = $q.defer();
        $http.post(urls.MENU_SERVICE_API + 'pagination/' + departmentId , pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.post(urls.MENU_SERVICE_API + 'id', id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(menu) {
        var deferred = $q.defer();
        $http.post(urls.MENU_SERVICE_API, menu)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(menu){
        var deferred = $q.defer();
        $http.put(urls.MENU_SERVICE_API, menu)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

}]);