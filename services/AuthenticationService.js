(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', AuthenticationService);

    AuthenticationService.$inject = ['$http','$cookieStore', '$rootScope', '$timeout', 'FlashService'];
    function AuthenticationService($http, $cookieStore, $rootScope, $timeout, FlashService) {
        var service = {};
        
        service.Login = Login;
        service.SetCredentials = SetCredentials;
        service.ClearCredentials = ClearCredentials;
        
        return service;
        
        function Login(user) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "GET",
                    params: {action:'Authenticate',user:user},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Authenticating'));
        }

        function SetCredentials(user) {
            $rootScope.globals = {
                currentUser: {
                    userId: user.userId,
                    userType: user.userType,
                    canEdit: user.canEdit
                }
            };
            $cookieStore.put('globals', $rootScope.globals);
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
        }
        
        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();