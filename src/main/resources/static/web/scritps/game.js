var app = new Vue({
    el: '#app',
    data: {
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        letters: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        gamePlayerId:"",
        gameJsn: "",
        gamePlayers:[],
        shipsLocation:"",
        cellMark:[],
        gamePlayerIdPosCero:"",

    },

    created: function () {
    this.gameViewFetch();
    },

    methods: {
        gameViewFetch: function () { // con este fetch cojo el gamePlayerId del back para usarlo en el front
            this.gamePlayerId= new URLSearchParams (window.location.search).get("gp");
            fetch("http://localhost:8080/api/game_view/"+this.gamePlayerId, {
                method: "GET",
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                }
            }).then(function (json) {
                app.gameJsn= json;
                app.shipsLocation= app.gameJsn.Ships;
                app.gamePlayers= app.gameJsn.gamePlayers;
                app.gamePlayerIdPosCero= app.gamePlayers[0].GamePlayerId;
                console.log(app.gameJsn);
                console.log(app.gamePlayers);
                app.shipLocationsCell();


            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            })
        },

        shipLocationsCell: function(){
            // let cell= [];
            // for(let i =0; i<app.shipsLocation.length; i++){
            //     cell.push(app.shipsLocation[i].ShipCell_Locations);
            // }
            app.cellMark=app.shipsLocation.flatMap(ship => ship.ShipCell_Locations);
            // console.log(app.cellMark)
        }


        }


    // },

    /*  runStart: function () {
          fetch("http://localhost:8080/api/games", {
              method: "GET",
          }).then(function (response) {
              if (response.ok) {
                  return response.json();
              }
          }).then(function (json) {
              app.games= json;
              console.log(app.games);


          }).catch(function (error) {
              console.log("Request failed:" + error.message);
          })
      },


      gamesList: function (){
          let liElement=document.createElement("li");
          let olElement=document.createElement("ol");
          let divElement=document.createElement("div");





          liElement.appendChild(olElement);
          olElement.appendChild(divElement);

      }


  },
*/

})
