var app = new Vue({
    el: '#app',
    data: {
        gamesJsn: [],
        gamePlayers:[],
        rankedPlayersJsn:[],
    },

    created:function(){
        this.listGames();
        this.infoPlayerRanked();
    },

    methods: {
        listGames: function () {
            fetch("http://localhost:8080/api/games", {
                method: "GET",
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                }
            }).then(function (json) {
                app.gamesJsn = json;
                console.log(app.gamesJsn)
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

        infoPlayerRanked: function () {
            fetch("http://localhost:8080/api/leaderBoard", {
                method: "GET",
            }).then(response => {
                if (response.ok) {
                    return response.json();
                }
            }).then(function (json) {
                app.rankedPlayersJsn = json;
                console.log(app.rankedPlayersJsn)

            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

    },

})
