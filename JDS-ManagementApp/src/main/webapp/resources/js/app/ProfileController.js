'use strict';

angular.module('ManagementApp').controller('Profile', 
['StaffService', '$scope', function (StaffService, $scope) {

    var self = this;

    self.passwordChange = {};
    
    self.successMessage = '';
    self.errorMessage = '';
    
    self.submit = submit;
    
    
    function submit() {
        changePassword(self.passwordChange);
        $scope.changePasswordForm.$setPristine();
    }

    
    function changePassword(passwordChange){
    	StaffService.changePassword(passwordChange)
    	.then((response)=>{
    		console.log(response);
    		 self.successMessage = 'CHANGE PASSWORD SUCCESSFULLY';
    		 self.errorMessage = '';
    		 self.passwordChange = {};
    	}, (error)=>{
    		console.log(error);
    		 self.successMessage = '';
    		 self.errorMessage = 'PASSWORD OR NEW PASSWORD IS INVALID';
    	});
    }

   
    
}]);