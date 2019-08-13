app.service('searchService', function ($http) {


    this.search = function (searchMap) {
        return $http.post('http://localhost:9104/itemSearch/search.do', searchMap);
    }

});