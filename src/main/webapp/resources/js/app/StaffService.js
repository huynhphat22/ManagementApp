'use strict';

angular.module('ManagementApp').factory('StaffService', ['$http', '$q', 'urls', function($http, $q, urls) {

    var factory = {
        findAll: findAll,
        findAllByPagination : findAllByPagination,
        findById: findById,
        save: save,
        update: update,
        resetPassword : resetPassword,
        changePassword : changePassword
    };


    return factory;

    

    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.STAFF_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.STAFF_SERVICE_API + 'pagination', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.get(urls.STAFF_SERVICE_API + id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(staff) {
        var deferred = $q.defer();
        $http.post(urls.STAFF_SERVICE_API, staff)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(staff){
        var deferred = $q.defer();
        $http.put(urls.STAFF_SERVICE_API, staff)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }
    
    function resetPassword(id){
    	var deferred = $q.defer();
    	 $http.get(urls.STAFF_SERVICE_API + 'reset-password/' + id)
         .then((response) => {
             deferred.resolve(response.data);
         }, (errors) => {
             deferred.reject(errors);
         });
    	 return deferred.promise;
    }
    
    function changePassword(passwordChange){
    	var deferred = $q.defer();
    	$http.post(urls.STAFF_SERVICE_API + 'change-password', passwordChange)
        .then((response) => {
            deferred.resolve(response.data);
        }, (errors) => {
            deferred.reject(errors);
        });
   	 	return deferred.promise;
    }

}]);