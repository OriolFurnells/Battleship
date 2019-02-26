var app = new Vue({
    el: '#app',
    data: {
        numbers: [1,2,3,4,5,6,7,8,9,10],
        letters: ["A","B","C","D","E","F","G","H","I","J"]
    },

    created:function(){
    //     // console.log("hola");
    //     this.runStart();
    console.log("hola")
    },

    /*methods: {

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
*/

})
