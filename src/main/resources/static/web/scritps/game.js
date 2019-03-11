var app = new Vue({
    el: '#app',
    data: {
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        letters: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        gamePlayerId: "",
        gameJsn: "",
        gamePlayers: [],
        meInfo: [],
        oponentInfo: [],
        ships: [],
        salvoes: [],
        cellMark: [],
        gamePlayerIdPosCero: "",

    },

    created: function () {
        this.gameViewFetch();

    },

    methods: {
        gameViewFetch() { // con este fetch cojo el gamePlayerId del back para usarlo en el front
            this.gamePlayerId = new URLSearchParams(window.location.search).get("gp");
            fetch("http://localhost:8080/api/game_view/" + this.gamePlayerId, {
                method: "GET",
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                }
            }).then(function (json) {
                app.gameJsn = json;
                console.log(app.gameJsn);
                app.gamePlayers = app.gameJsn.gamePlayers;
                app.extractInfo();


            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },


        extractInfo: function () {
            for (let i = 0; app.gamePlayers.length > i; i++) {
                if (app.gamePlayerId == app.gamePlayers[i].gamePlayerId) {
                    app.meInfo = app.gamePlayers[i];
                } else {
                    if(app.gamePlayers[i]==null){
                        app.oponentInfo = "";
                    }else{
                    app.oponentInfo = app.gamePlayers[i];
                    }
                }
            }
        },

        //printea las celdas que tienen barcos y los salvos que impactan en ship o en agua
        addClassToCell(id) {
            let classToReturnCarrier;
            let classToReturnBattleship;
            let classToReturnSubmarine;
            let classToReturnDestroyer;
            let classToReturnPatrolBoat;
            let classToReturnShots;
            let classToReturnFail;
            app.ships = app.meInfo.ships;
            app.salvoes = app.oponentInfo.salvoes;
            let cellShip = app.ships.flatMap(ship => ship.shipCell_Locations);
            let cellShot;
            let targetOk;
            let targetFail;
            if(app.gamePlayers.length!=1){
                cellShot= app.salvoes.flatMap(salvo => salvo.salvo_Locations);
                targetFail = cellShot.filter(disparo => !cellShip.includes(disparo));
                targetOk = cellShot.filter(disparo => cellShip.includes(disparo));
            } else {
                cellShot = [];
                targetFail= "";
                targetOk="";
            }

            for (let i = 0; app.ships.length > i; i++) {
                if (app.ships[i].shipType == "Carrier") {
                    if (app.ships[i].shipCell_Locations.includes(id)) {
                        classToReturnCarrier = "carrier " + app.ships[i].shipType;
                    }
                }
                if (app.ships[i].shipType == "Battleship") {
                    if (app.ships[i].shipCell_Locations.includes(id)) {
                        classToReturnBattleship = "battleship " + app.ships[i].shipType;
                    }
                }
                if (app.ships[i].shipType == "Submarine") {
                    if (app.ships[i].shipCell_Locations.includes(id)) {
                        classToReturnSubmarine = "submarine " + app.ships[i].shipType;
                    }
                }
                if (app.ships[i].shipType == "Destroyer") {
                    if (app.ships[i].shipCell_Locations.includes(id)) {
                        classToReturnDestroyer = "destroyer " + app.ships[i].shipType;
                    }
                }
                if (app.ships[i].shipType == "PatrolBoat") {
                    if (app.ships[i].shipCell_Locations.includes(id)) {
                        // console.log("hola PB")
                        classToReturnPatrolBoat = "patrolBoat " + app.ships[i].shipType;
                    }
                }

                for (let j = 0; app.salvoes.length > j; j++) {
                    if (app.salvoes[j].salvo_Locations.includes(id)) {
                        if (targetOk.includes(id)) {
                            classToReturnShots = "shotImpact";
                        }
                        if (targetFail.includes(id)) {
                            classToReturnFail = "shotFail";
                        }
                    }
                }
            }
            return [classToReturnPatrolBoat, classToReturnSubmarine, classToReturnDestroyer, classToReturnCarrier, classToReturnShots, classToReturnBattleship, classToReturnFail];
        }
    }
})

/*
//function to unify the cells have something (ship or shot)
convertArraysInOneArray: function () {
    // console.log(app.shipsLocation);
    // console.log(app.cellMark);
    // app.cellMark=app.shipsLocation.flatMap(ship => ship.shipCell_Locations);
    app.cellShot = app.salvoLocation.flatMap(shot => shot.salvo_Locations);

    console.log(app.shipsLocation);
    console.log(app.cellMark);
    console.log(app.cellShot);

}
*/

