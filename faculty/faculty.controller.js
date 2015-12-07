(function () {
    'use strict';

    angular
        .module('app')
        .controller('FacultyController', FacultyController);

    FacultyController.$inject = ['$location','$rootScope','FetchResultService','ActionService','FlashService'];
    function FacultyController($location,$rootScope,FetchResultService,ActionService,FlashService) {
         var vm = this;
        vm.allFaculty = [];
        vm.showEdit = true;
        vm.showNew = false;
        vm.editLabel = false;
        vm.isEditable = $rootScope.globals.currentUser.canEdit;
        
        initialize();
        vm.selectTab = selectTab;
        vm.makeVisible = makeVisible;
        vm.removeVisibility = removeVisibility;
        vm.newFaculty = newFaculty;
        
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
            
            FetchResultService.GetFaculty()
                .then(function (response) {
                    vm.allFaculty = response.data.allFaculty;
                });
        }
        
        function makeVisible(faculty) {
            vm.showDetail = true;
            vm.showNew = false;
            vm.currentFaculty = faculty;
        }

        function removeVisibility() {
            vm.showDetail = false;
            vm.showNew = false;
        }
        
        function newFaculty() {
            vm.showNew = true;
            vm.showDetail = false;
        }
        
        function edit() {
            vm.editLabel = true;
            vm.showEdit = false;
        }
        function update(faculty) {
            ActionService.UpdateFaculty(faculty)
                    .then(function (response) {
                        if(response.data.success){
                            vm.editLabel = false;
                            vm.showEdit = true;
                            FlashService.Success('Successfully updated Faculty', false);
                        }else{
                            FlashService.Error('Could not update Faculty ', false);
                        }
                    });
        }
        
        function deleteRec(faculty) {
            ActionService.DeleteFaculty(faculty)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showDetail = false;
                            FetchResultService.GetFaculty()
                            .then(function (response) {
                                vm.allFaculty = response.data.allFaculty;
                            });
                            FlashService.Success('Successfully deleted Faculty', false);
                        }else{
                            FlashService.Error('Could not delete Faculty ', false);
                        }
                    });
        }
        
        function create(newFaculty) {
            ActionService.AddFaculty(newFaculty)
                    .then(function (response) {
                        if(response.data.success){
                            vm.showNew = false;
                            vm.new = {};
                            FetchResultService.GetFaculty()
                            .then(function (response) {
                                vm.allFaculty = response.data.allFaculty;
                            });
                            FlashService.Success('Successfully added  Faculty', false);
                        }else{
                            FlashService.Error('FAcultyId already exists ', false);
                        }
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
