(function () {
    'use strict';

    angular
        .module('app')
        .factory('ActionService', ActionService);

    ActionService.$inject = ['$http'];
    function ActionService($http) {
        var service = {};

        service.AddStaff = AddStaff;
        service.UpdateStaff = UpdateStaff;
        service.DeleteStaff = DeleteStaff;
        service.AddStudent = AddStudent;
        service.UpdateStudent = UpdateStudent;
        service.DeleteStudent = DeleteStudent;
        service.AddFaculty = AddFaculty;
        service.UpdateFaculty = UpdateFaculty;
        service.DeleteFaculty = DeleteFaculty;
        service.AddCourse = AddCourse;
        service.UpdateCourse = UpdateCourse;
        service.DeleteCourse = DeleteCourse;
        service.UpdateStudentMarks = UpdateStudentMarks;
        service.EnrollCourse = EnrollCourse;
        
        return service;
        
        function AddStaff(staff) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'addStaff', object:staff},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Adding Student'));
        }
        
        function UpdateStaff(staff) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'updateStaff', object:staff},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Updating Student'));
        }
        
        function DeleteStaff(staff) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'deleteStaff', object:staff},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error deleting Student'));
        }
        
        function AddStudent(student) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'addStudent', object:student},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Adding Student'));
        }
        
        function UpdateStudent(student) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'updateStudent', object:student},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Updating Student'));
        }
        
        function DeleteStudent(student) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'deleteStudent', object:student},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error deleting Student'));
        }
        
        function AddFaculty(faculty) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'addFaculty', object:faculty},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Adding Faculty'));
        }
        
        function UpdateFaculty(faculty) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'updateFaculty', object:faculty},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Updating Faculty'));
        }
        
        function DeleteFaculty(faculty) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'deleteFaculty', object:faculty},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Deleting Faculty'));
        }
        
        function AddCourse(course) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'addCourse', object:course},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Adding Course'));
        }
        
        function UpdateCourse(course) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'updateCourse', object:course},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Updating Course'));
        }
        
        function DeleteCourse(course) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'deleteCourse', object:course},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Deleting Course'));
        }
        
        function EnrollCourse(cid,sid) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'enroll', object:{sid: sid, cid: cid}},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Adding Student'));
        }
        
        function UpdateStudentMarks(studentMarks) {
            return $http({
                    url: 'ExecutionServiceServlet',
                    method: "POST",
                    params: {action: 'updateStudentGrades', object:studentMarks},
                    headers: {'Content-Type': 'application/json'}
              }).success(handleSuccess)
              .error(handleError('Error Updating Student'));
        }
        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            //FlashService.Error('Failure');
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
