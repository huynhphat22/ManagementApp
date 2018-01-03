'use strict';

angular.module('ManagementApp').factory('MonthCostService', ['$http', '$q', 'urls', function($http, $q, urls) {

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
         $http.get(urls.MONTHCOST_SERVICE_API + "department")
             .then((response) => {
                 deferred.resolve(response.data);
             }, (errors) => {
                 deferred.reject(errors);
             });

         return deferred.promise;
    }

    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.MONTHCOST_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.MONTHCOST_SERVICE_API + 'department/', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.get(urls.MONTHCOST_SERVICE_API + id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(monthCost) {
        var deferred = $q.defer();
        $http.post(urls.MONTHCOST_SERVICE_API, monthCost)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(monthCost){
        var deferred = $q.defer();
        $http.put(urls.MONTHCOST_SERVICE_API, monthCost)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

}]);