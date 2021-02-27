//import crypto from "crypto";
window.onload = function () {
    addModalAction();
};
function addModalAction() {
    var modal = document.getElementById("modal");
    var modalButton = document.getElementById("modalOpen");
    var modalConfirmButton = document.getElementById("modalConfirmButton");
    var modalCloseButtons = document.querySelectorAll(".js-modal-close");
    var ms = 400;
    modal.style.transition = "opacity" + ms + "ms";
    modalButton.addEventListener('click', function () {
        setTimeout(function () { modal.classList.add("is_open"); }, 1);
        setTimeout(function () { modal.classList.add("opacity-full"); }, 50);
    });
    modalCloseButtons.forEach(function (e) {
        e.addEventListener("click", function () {
            setTimeout(function () { modal.classList.remove("opacity-full"); }, 1);
            setTimeout(function () { modal.classList.remove("is_open"); }, ms);
        });
    });
    modalConfirmButton.addEventListener('click', function () {
        var url = buildAuthorizationEndPointUrl();
        var option = {
            method: "GET"
        };
        //const res = fetch(authUrl, option);
        location.href = url;
    });
}
function buildAuthorizationEndPointUrl() {
    //const lineUrl = 'http://localhost:8081/oauth/authorize?redirect_uri=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dja&state=sflajsfkjaksjfdsaf&client_id=test&scope=notify&response_type=code';
    //const lineUrl = 'https://notify-bot.line.me/oauth/authorize';
    var authorizationEndPointUrl = 'http://localhost:8081/oauth/authorize';
    //const clientUrl = 'http://localhost:8080/oauth/client';
    var clientUrl = 'https://www.google.com';
    var state = randomString(20);
    var client_id = randomString(10);
    var scope = 'notify';
    var response_type = 'code';
    return authorizationEndPointUrl + '?redirect_uri=' + encodeURI(clientUrl) + '&state=' + state + '&client_id=' + client_id + '&scope=' + scope + '&response_type=' + response_type;
}
function randomString(charLength) {
    //return  crypto.randomBytes(charLength / 2).toString('hex');
    return Math.random().toString(32).substring(2);
}
//# sourceMappingURL=client.js.map