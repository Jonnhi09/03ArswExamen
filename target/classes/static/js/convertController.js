function showConvertion(data) {
    var result = document.getElementById("result");
    var msj = "";
    for (var d in data) {
        msj += d + " " + data[d] + ", ";
    }
    result.innerHTML = msj;
    console.log(data);
}

function getConvertion(unidad, num) {
    axios.get('/temperature/' + String(unidad) + '/' + parseInt(num)).then(function (response) {
        showConvertion(response.data);
    }).catch(function (error) {
        console.log(error);
    })
}