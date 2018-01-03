'use strict';

angular.module('ManagementApp').factory('DateCostService', ['$http', '$q', 'urls', function($http, $q, urls) {

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
         $http.get(urls.DATECOST_SERVICE_API + "department")
             .then((response) => {
                 deferred.resolve(response.data);
             }, (errors) => {
                 deferred.reject(errors);
             });

         return deferred.promise;
    }

    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.DATECOST_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.DATECOST_SERVICE_API + 'department/', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.post(urls.DATECOST_SERVICE_API + 'id', id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(dateCost) {
        var deferred = $q.defer();
        $http.post(urls.DATECOST_SERVICE_API, dateCost)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(dateCost){
        var deferred = $q.defer();
        $http.put(urls.DATECOST_SERVICE_API, dateCost)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

}]);