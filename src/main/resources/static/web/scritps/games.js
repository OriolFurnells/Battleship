var app = new Vue({
    el: '#app',
    data: {
        games : [],
        gamePlayers:[],

    },

    created:function(){
        // console.log("hola");
        this.runStart();
    },

    methods: {

        runStart: function () {
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


})
