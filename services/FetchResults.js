(function () {
    'use strict';

    angular
            .module('app')
            .factory('FetchResultService', FetchResultService);

    FetchResultService.$inject = ['$http'];
    function FetchResultService($http) {
        var service = {};

        service.Authenticate = Authenticate;
        service.GetStudents = GetStudents;
        service.GetMyStudents = GetMyStudents;
        service.GetFaculty = GetFaculty;
        service.GetCourses = GetCourses;
        service.GetStaff = GetStaff;
        service.GetEnrolledStudents = GetEnrolledStudents;
        service.GetMyCourses = GetMyCourses;
        service.GetMyEnCourses = GetMyEnCourses;

        return service;

        function Authenticate(credentials) {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'Authenticate', credentials: credentials},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Authenticating'));
        }

        function GetStudents() {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetStudents'},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Students'));
        }

        function GetMyStudents(facultyId) {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetMyStudents', fid: facultyId},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Students'));
        }

        function GetStaff() {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetStaff'},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Students'));
        }

        function GetFaculty() {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetFaculty'},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Faculties'));
        }

        function GetCourses() {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetCourses'},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Courses'));
        }

        function GetEnrolledStudents(courseId) {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'enrolledStudents', cid: courseId},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Enrolled Students'));
        }

        function GetMyCourses(facultyId) {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetMyCourses', fid: facultyId},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Your Courses'));
        }

        function GetMyEnCourses(studentId) {
            return $http({
                url: 'ExecutionServiceServlet',
                method: "GET",
                params: {action: 'GetMyEnCourses', sid: studentId},
                headers: {'Content-Type': 'application/json'}
            }).success(handleSuccess)
                    .error(handleError('Error Fetching Your Courses'));
        }
        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            //FlashService.Error('Failure');
            return function () {
                return {success: false, message: error};
            };
        }
    }

})();
