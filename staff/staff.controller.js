(function () {
    'use strict';

    angular
            .module('app')
            .controller('StaffController', StaffController);

    StaffController.$inject = ['$location', '$rootScope', 'FetchResultService','FlashService','ActionService'];
    function StaffController($location, $rootScope, FetchResultService,FlashService,ActionService) {
        var vm = this; 
        vm.allStaff = [];
        vm.showDetail = false;
        vm.showEdit = true;
        vm.showNew = false;
        vm.editLabel = false;
        vm.isEditable = $rootScope.globals.currentUser.canEdit;
        
       
        vm.selectTab = selectTab;
        vm.makeVisible = makeVisible;
        vm.removeVisibility = removeVisibility;
        vm.newStaff = newStaff;
        
        vm.edit = edit;
        vm.update = update;
        vm.deleteRec = deleteRec;
        vm.create = create;

        initialize();        

        function initialize() {
            vm.tabs = [{
                    id: 1,
                    name: 'STAFF'
                }, {
                    id: 2,
                    name: 'STUDENTS' 
                }, {
                    id: 3,
                    name: 'FACULTY' 
                }, {
                    id: 3,
                    name: 'COURSES'
                }];

            FetchResultService.GetStaff()
                    .then(function (response) {
                        vm.allStaff = response.data.allStaff;
                    });
        }

        function makeVisible(staff) {
            vm.showDetail = true;
            vm.showNew = false;
            vm.cuurentStaff = staff;
        }

        function removeVisibility() {
            vm.showDetail = false;
            vm.showNew = false;
        }
        
        function newStaff() {
            vm.showNew = true;
            vm.showDetail = false;
        }
        
        function edit() {
            vm.editLabel = true;
            vm.showEdit = false;
        }
        function update(staff) {
            ActionService.UpdateStaff(staff)
                    .then(function (response) {
                        if(response.data.success){
                            vm.editLabel = false;
                            vm.showEdit = true;
                            FlashService.Success('Successfully updated Staff', false);
                        }else{
                            FlashService.Error('Could not update Staff ', false);
                        }
                    });
        }
        
        function deleteRec(staff) {
            ActionService.DeleteStaff(staff)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showDetail = false;
                            FetchResultService.GetStaff()
                            .then(function (response) {
                                vm.allStaff = response.data.allStaff;
                            });
                            FlashService.Success('Successfully deleted Staff', false);
                        }else{
                            FlashService.Error('Could not delete Staff ', false);
                        }
                    });
        }
        
        function create(newStaff) {
            ActionService.AddStaff(newStaff)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showNew = false;
                            vm.new = {};
                            FetchResultService.GetStaff()
                            .then(function (response) {
                                vm.allStaff = response.data.allStaff;
                            });
                            FlashService.Success('Successfully created Staff', false);
                        }else{
                            FlashService.Error('StaffId already exists ', false);
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
