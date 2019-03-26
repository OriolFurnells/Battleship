var app = new Vue({
    el: '#app',
    data: {
        gamesJsn: [],
        gamePlayers: [],
        rankedPlayersJsn: [],
        gamePlayerIdPositionCero: "",
        log: false,
        userNameLogin: "",
        userLog: "",
    },

    created: function () {
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
                console.log(app.gamesJsn);
                app.userNameLogin = app.gamesJsn.player;
                if(userNameLogin!=null){
                    app.log=true;
                }else{
                    app.log=false;
                }
                // app.gamePlayerPositionCero=app.gamesJsn.gamePlayer[0]
                console.log(app.userNameLogin);
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
                app.rankedPlayersJsn = json.sort(function (a, b) {
                    return b.totalPoints - a.totalPoints
                });


                // console.log(app.rankedPlayersJsn.sort(function(a, b){return b.totalPoints - a.totalPoints}));

            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

        logIn: function () {
            let password = document.getElementById(pwdLog.value);
            let user= document.getElementById(userLog.value);

            if (userLog.value == "" || pwdLog.value == "") {
                alert("You need email and password");
            } else {
                let emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
                if (emailRegex.test(userLog.value)) {
                    fetch("http://localhost:8080/api/players/", {
                        method: "POST",
                        body: JSON.stringify({ username: user , password: password})
                }).then(function (response) {
                        console.log(app.log+"then")
                        if (response.ok) {
                            console.log(app.log+"responseOK")
                            app.log=true;
                            return response.json();
                        }
                    }).catch(function (error) {
                        console.log("Request failed:" + error.message);
                    })
                }
                else {
                    alert("you need correct email")
                }

            }
        },


    },

})
