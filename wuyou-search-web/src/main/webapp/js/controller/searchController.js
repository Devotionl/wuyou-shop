app.controller('searchController', function ($scope, searchService) {
    //
    // //搜索
    // $scope.search=function(){
    // 	$http({
    // 		method:"POST",
    //        url:"http:localhost:8080/itemsearch/search.do",
    // 		data:$scope.searchMap
    // 	}).success(function (response) {
    // 			$scope.resultMap = response;
    //    })
    // }


    //搜索
    $scope.search = function () {
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response;
            }
        );
    }


});