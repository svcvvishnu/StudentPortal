(function () {
    'use strict';
    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })
            
            .when('/staffTab', {
                controller: 'StaffController',
                templateUrl: 'staff/staff.view.html',
                controllerAs: 'vm'
            })
            
            .when('/studentTab', {
                controller: 'StudentController',
                templateUrl: 'student/student.view.html',
                controllerAs: 'vm'
            })
            
            .when('/facultyTab', {
                controller: 'FacultyController',
                templateUrl: 'faculty/faculty.view.html',
                controllerAs: 'vm'
            })
            
            .when('/courseTab', {
                controller: 'CourseController',
                templateUrl: 'course/course.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/staffTab','/studentTab','/facultyTab','/courseTab']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();