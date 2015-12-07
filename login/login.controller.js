(function () {
    'use strict';

    angular
            .module('app')
            .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;
        vm.login = login;
        vm.loginType = 'Staff';

        initialize();
        function initialize() {
            AuthenticationService.ClearCredentials();
            vm.userTypes = {
                infoText: "Which type of user are you ?",
                choices: [{
                        id: 1,
                        text: "Staff",
                        isSelected: "true"
                    }, {
                        id: 2,
                        text: "Student",
                        isSelected: "false"
                    }, {
                        id: 3,
                        text: "Faculty",
                        isSelected: "false"
                    }]
            };
        }

        function login() {
            if (vm.loginType === 'Student') {
                vm.user = {userId: vm.username, userType: 'Student', canEdit: false};
                AuthenticationService.Login(vm.user)
                        .then(function (response) {
                            if (response.data.isValid) {
                                AuthenticationService.SetCredentials(vm.user);
                                $location.path('/studentTab');
                            } else {
                                FlashService.Error('Student ID not valid', true);
                                $location.path('/login');
                            }
                        });
            } else if (vm.loginType === 'Faculty') {
                vm.user = {userId: vm.username, userType: 'Faculty', canEdit: false};
                AuthenticationService.Login(vm.user)
                        .then(function (response) {
                            if (response.data.isValid) {
                                AuthenticationService.SetCredentials(vm.user);
                                $location.path('/facultyTab');
                            } else {
                                FlashService.Error('Faculty ID not valid', true);
                                $location.path('/login');
                            }
                        });
            } else {
                vm.user = {userId: vm.username, userType: 'Staff', canEdit: true};
                 AuthenticationService.Login(vm.user)
                        .then(function (response) {
                            if (response.data.isValid) {
                                AuthenticationService.SetCredentials(vm.user);
                                $location.path('/staffTab');
                            } else {
                                FlashService.Error('Staff ID not valid', true);
                                $location.path('/login');
                            }
                        });
            }
        }
    }
})();
