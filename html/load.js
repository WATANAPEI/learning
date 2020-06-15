window.onload = function() {
    var load = document.getElementById('load');
    var elem = document.createElement('div');
    var text = document.createTextNode('This is a new div element.');
    load.appendChild(text);
    console.log(load);



}