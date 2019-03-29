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
        emailValidate : /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i

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
            }).then(function (json){
                app.gamesJsn = json;
                console.log(app.gamesJsn);
                app.userNameLogin = app.gamesJsn.player;
                if(app.userNameLogin!=null){
                    app.log = true;
                } else{
                    app.log=false;
                }

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

            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

        logIn() {
            console.log("hi logIn")
            let user= document.getElementById("userLog").value;
            let password = document.getElementById("pwdLog").value;
            if (user == "" || password == "") {
                alert("You need email and password");
            } else {
                if (app.emailValidate.test(user)) {
                    let userLog  = "userName="+user+"&password="+password;
                    console.log(userLog)
                    fetch("http://localhost:8080/api/login", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: userLog
                    }).then(response => {
                        if (response.ok) {
                            location.reload();
                        } else {
                            alert("Credentials not found");
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
        
        logOut(){
            fetch("http://localhost:8080/api/logout", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
            }).then(response => {
                if (response.ok) {
                    app.log=false;
                    location.reload();
                } else {
                    alert("Credentials not found");
                }

            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })

            
        },

        register() {
            let user = document.getElementById("userLog").value;
            let password = document.getElementById("pwdLog").value;
            if (user == "" || password == "") {
                alert("You need email and password");
            } else {
                if (app.emailValidate.test(user)) {
                    let userLog = JSON.stringify({userName :user, password:  password});
                    fetch("http://localhost:8080/api/players", {
                        method: "POST",
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: userLog
                    }).then(response => {
                        if (response.ok) {
                            app.logIn();
                        } else {
                            alert("Your Sig Up isn't OK");
                        }

                    }).catch(function (error) {
                        console.log("Request failed:" + error.message);
                    })
                }
                else {
                    alert("you need correct email")
                }

            }
        }


    },

})
