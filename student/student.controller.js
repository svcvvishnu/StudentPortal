(function () {
    'use strict';

    angular
            .module('app')
            .controller('StudentController', StudentController);

    StudentController.$inject = ['$location', '$rootScope', 'FetchResultService', 'ActionService', 'FlashService'];
    function StudentController($location, $rootScope, FetchResultService, ActionService, FlashService) {
        var vm = this;
        vm.showDetail = false;
        vm.allStudents = [];
        vm.allMyStudents= [];
        vm.showEdit = true;
        vm.showNew = false;
        vm.editLabel = false;
        vm.editGrades = false;
        vm.isFaculty = false;
        vm.isEditable = $rootScope.globals.currentUser.canEdit;

        initialize();
        vm.selectTab = selectTab;
        vm.makeVisible = makeVisible;
        vm.removeVisibility = removeVisibility;
        vm.newStudent = newStudent;
        vm.changeGrades = changeGrades;

        vm.edit = edit;
        vm.update = update;
        vm.deleteRec = deleteRec;

        vm.updateGrades = updateGrades;

        function initialize() {
            vm.tabs = [{
                    id: 1,
                    name: 'STAFF',
                    content: 'This is a default tab on load'
                }, {
                    id: 2,
                    name: 'STUDENTS',
                    content: 'This is a default tab on load'
                }, {
                    id: 3,
                    name: 'FACULTY',
                    content: 'This is a default tab on load'
                }, {
                    id: 3,
                    name: 'COURSES',
                    content: 'This is a default tab on load'
                }];

            FetchResultService.GetStudents()
                    .then(function (response) {
                        vm.allStudents = response.data.allStudents;
                    });
            if ($rootScope.globals.currentUser.userType === 'Faculty') {
                vm.isFaculty = true;
                FetchResultService.GetMyStudents($rootScope.globals.currentUser.userId)
                        .then(function (response) {
                            vm.allMyStudents = response.data.allMyStudents;
                        });
            }
        }

        function makeVisible(student) {
            vm.showDetail = true;
            vm.cuurentStudent = student;
        }
        
        function changeGrades(student) {
            vm.addGrades = true;
            vm.changeStudent = student;
        }

        function removeVisibility() {
            vm.showDetail = false;
            vm.showNew = false;
        }

        function newStudent() {
            vm.showNew = true;
            vm.showDetail = false;
        }

        function edit() {
            vm.editLabel = true;
            vm.showEdit = false;
        }
        function update(student) {
            ActionService.UpdateStudent(student)
                    .then(function (response) {
                        if (response.data.success) {
                            vm.editLabel = false;
                            vm.showEdit = true;
                            FlashService.Success('Successfully updated Student', false);
                        } else {
                            FlashService.Error('Could not update Student ', false);
                        }
                    });
        }

        function updateGrades(studentMarks) {
            ActionService.UpdateStudentMarks(studentMarks)
                    .then(function (response) {
                        if (response.data.success) {
                            vm.editGrades = false;
                            FlashService.Success('Successfully updated Student Grades', false);
                        } else {
                            FlashService.Error('Could not update Student Grades', false);
                        }
                    });
        }

        function deleteRec(student) {
            ActionService.DeleteStudent(student)
                    .then(function (response) {
                        if (response.data.success) {
                            vm.showDetail = false;
                            FetchResultService.GetStudents()
                                    .then(function (response) {
                                        vm.allStudents = response.data.allStudents;
                                    });
                            FlashService.Success('Successfully deleted Student', false);
                        } else {
                            FlashService.Error('Could not delete Student ', false);
                        }
                    });
        }

        function create(newStudent) {
            ActionService.AddStudent(newStudent)
                    .then(function (response) {
                        if (response.data.success) {
                            vm.showNew = false;
                            vm.new = {};
                            FetchResultService.GetStudents()
                                    .then(function (response) {
                                        vm.allStudents = response.data.allStudents;
                                    });
                            FlashService.Success('Successfully added  Student', false);
                        } else {
                            FlashService.Error('StudentID already exists ', false);
                        }
                    });
        }

        function  selectTab(index) {
            if (index === 0) {
                $location.path('/staffTab');
            } else if (index === 1) {
                $location.path('/studentTab');
            } else if (index === 2) {
                $location.path('/facultyTab');
            } else {
                $location.path('/courseTab');
            }
        }
    }
})();
