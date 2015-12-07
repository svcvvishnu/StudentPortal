(function () {
    'use strict';

    angular
        .module('app')
        .controller('CourseController', CourseController);

    CourseController.$inject = ['$location','$rootScope','FetchResultService','ActionService','FlashService'];
    function CourseController($location,$rootScope,FetchResultService,ActionService,FlashService) {
        var vm = this;
        vm.allCourses = [];
        vm.allMyCourses = [];
        vm.enrolledStudents = [];
        vm.showDetail = false;
        vm.showEdit = true;
        vm.showNew = false;
        vm.showEnrolled = false;
        vm.editLabel = false;
        vm.isStudent = false;

        vm.isEditable = $rootScope.globals.currentUser.canEdit;
        
        initialize();
        vm.selectTab = selectTab;
        vm.makeVisible = makeVisible;
        vm.removeVisibility = removeVisibility;
        vm.newCourse = newCourse;
        vm.enrollStudents = enrollStudents;
        vm.enroll = enroll;
        
        vm.edit = edit;
        vm.update = update;
        vm.deleteRec = deleteRec;
        vm.create = create;
        
        function initialize(){
            vm.tabs = [{
			id:1,
                        name:'STAFF',
			content:'This is a default tab on load'
		},{
			id:2,
                        name:'STUDENTS',
			content:'This is a default tab on load'
		},{
			id:3,
                        name:'FACULTY',
			content:'This is a default tab on load'
		},{
			id:3,
                        name:'COURSES',
			content:'This is a default tab on load'
		}];
            
            FetchResultService.GetCourses()
                .then(function (response) {
                    vm.allCourses = response.data.allCourses;
                });
            if($rootScope.globals.currentUser.userType === 'Student'){
               vm.isStudent = true;
               FetchResultService.GetMyEnCourses($rootScope.globals.currentUser.userId)
                .then(function (response) {
                    vm.allMyCourses = response.data.allMyCourses;
                });
            }else if($rootScope.globals.currentUser.userType === 'Faculty'){
                FetchResultService.GetMyCourses($rootScope.globals.currentUser.userId)
                .then(function (response) {
                    vm.allMyCourses = response.data.allMyCourses;
                });
            }
                
        }
        
        function makeVisible(course){
            vm.showDetail = true;
            vm.showEnrolled = false;
            vm.showNew = false;
            vm.cuurentCourse = course;
        }
        
        function removeVisibility() {
            vm.showDetail = false;
            vm.showNew = false;
            vm.showEnrolled = false;
        }
        
        function newCourse() {
            vm.showNew = true;
            vm.showEnrolled = false;
            vm.showDetail = false;
        }
        
        function edit() {
            vm.editLabel = true;
            vm.showEdit = false;
        }
        
        function enroll(course) {
            ActionService.EnrollCourse(course.cid,$rootScope.globals.currentUser.userId)
                    .then(function (response) {
                        if(response.data.success){
                            FlashService.Success('Successfully enrolled to  course', false);
                                FetchResultService.GetCourses()
                            .then(function (response) {
                                vm.allCourses = response.data.allCourses;
                            });
                        }else{
                            FlashService.Error('Could not enroll to  course ', false);
                        }
                    });
        }
        
        function update(course) {
            ActionService.UpdateCourse(course)
                    .then(function (response) {
                        if(response.data.success){
                            vm.editLabel = false;
                            vm.showEdit = true;
                            FlashService.Success('Successfully updated course', false);
                        }else{
                            FlashService.Error('Could not update course ', false);
                        }
                    });
        }
        
        function deleteRec(course) {
            ActionService.DeleteCourse(course)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showDetail = false;
                            FetchResultService.GetCourses()
                            .then(function (response) {
                                vm.allCourses = response.data.allCourses;
                            });
                            FlashService.Success('Successfully deleted course', false);
                        }else{
                            FlashService.Error('Could not delete course ', false);
                        }
                    });
        }
        
        function create(newCourse) {
            ActionService.AddCourse(newCourse)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showNew = false;
                            vm.new = {};
                            FetchResultService.GetCourses()
                            .then(function (response) {
                                vm.allCourses = response.data.allCourses;
                            });
                            FlashService.Success('Successfully added  Course', false);
                        }else{
                            FlashService.Error('CourseId already exists ', false);
                        }
                    });
        }
        
        function enrollStudents(course) {
            vm.showEnrolled = true;
            vm.enrolledStudents = [];
            FetchResultService.GetEnrolledStudents(course.cid)
                .then(function (response) {
                    vm.enrolledStudents = response.data.enrolledStudents;
                });
        }
        
        function  selectTab(index){
            if(index === 0){
                $location.path('/staffTab');
            }else if (index === 1){
                $location.path('/studentTab');
            }else if (index === 2){
                $location.path('/facultyTab');
            }else{
                $location.path('/courseTab');
            }
        }
    }
})();
