'use strict';

angular.module('ManagementApp').factory('DepartmentService', ['$http', '$q', 'urls', function($http, $q, urls) {

    var factory = {
        findAll: findAll,
        findAllByPagination : findAllByPagination,
        findById: findById,
        save: save,
        update: update,
    };


    return factory;

    

    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.DEPARTMENT_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.DEPARTMENT_SERVICE_API + 'pagination', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.get(urls.DEPARTMENT_SERVICE_API + id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(department) {
        var deferred = $q.defer();
        $http.post(urls.DEPARTMENT_SERVICE_API, department)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(department){
        var deferred = $q.defer();
        $http.put(urls.DEPARTMENT_SERVICE_API, department)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

}]);