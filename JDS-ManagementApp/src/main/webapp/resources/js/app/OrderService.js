'use strict';

angular.module('ManagementApp').factory('OrderService', ['$http', '$q', 'urls', function($http, $q, urls) {

    var factory = {
        findAll: findAll,
        findAllByPagination : findAllByPagination,
        findById: findById,
        save: save,
        update: update,
        findAllByDepartmentPagination : findAllByDepartmentPagination,
        findAllOrderDetailByOrderId : findAllOrderDetailByOrderId,
        findAllByDepartmentPaginationSwitchBoard : findAllByDepartmentPaginationSwitchBoard
    };


    return factory;

   

    function findAllOrderDetailByOrderId(orderId){
    	var deferred = $q.defer();
        $http.get(urls.ORDER_SERVICE_API + 'orderDetail/' + orderId)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findAllByDepartmentPaginationSwitchBoard(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.ORDER_SERVICE_API + 'switchboard', pageQuery)
            .then((response) => {
            	console.log("dc roi ne");
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }
    function findAllByDepartmentPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.ORDER_SERVICE_API + 'department', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }
    
    function findAll() {
        var deferred = $q.defer();
        $http.get(urls.ORDER_SERVICE_API)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });

        return deferred.promise;
    }

    function findAllByPagination(pageQuery){
        var deferred = $q.defer();
        $http.post(urls.ORDER_SERVICE_API + 'pagination', pageQuery)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function findById(id) {
        var deferred = $q.defer();
        $http.get(urls.ORDER_SERVICE_API + id)
            .then((response) => {
                deferred.resolve(response.data);
            },
            (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function save(order) {
        var deferred = $q.defer();
        $http.post(urls.ORDER_SERVICE_API, order)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

    function update(order){
        var deferred = $q.defer();
        $http.put(urls.ORDER_SERVICE_API, order)
            .then((response) => {
                deferred.resolve(response.data);
            }, (errors) => {
                deferred.reject(errors);
            });
        return deferred.promise;
    }

}]);