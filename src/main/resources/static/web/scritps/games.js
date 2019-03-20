var app = new Vue({
    el: '#app',
    data: {
        gamesJsn: [],
        gamePlayers:[],
        rankedPlayersJsn:[],
        gamePlayerIdPositionCero:"",
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
                app.gamePlayerPositionCero=app.gamesJsn.gamePlayer[0];
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
                app.rankedPlayersJsn = json.sort(function(a, b){return b.totalPoints - a.totalPoints});


                // console.log(app.rankedPlayersJsn.sort(function(a, b){return b.totalPoints - a.totalPoints}));

            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

    },

})
