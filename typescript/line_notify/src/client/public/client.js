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
        var authUrl = 'http://localhost:8081/oauth/authorize?redirect_uri=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dja&state=sflajsfkjaksjfdsaf&client_id=test&scope=notify&response_type=code';
        var option = {
            method: "GET"
        };
        //const res = fetch(authUrl, option);
        location.href = authUrl;
    });
}
//# sourceMappingURL=client.js.map