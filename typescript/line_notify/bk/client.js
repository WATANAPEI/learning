window.onload = function () {
    _addModalAction();
};
function confirm() {
}
function _addModalAction() {
    var modal = document.getElementById("modal");
    var modalButton = document.getElementById("modalOpen");
    var body = document.getElementById("body");
    var ms = 400;
    modal.style.transition = "opacity" + ms + "ms";
    modalButton.addEventListener('click', function () {
        setTimeout(function () { modal.classList.add("is_open"); }, 1);
        setTimeout(function () { modal.classList.add("opacity-full"); }, 50);
    });
    var modalClose = document.querySelectorAll(".js-modal-close");
    modalClose.forEach(function (e) {
        e.addEventListener("click", function () {
            setTimeout(function () { modal.classList.remove("opacity-full"); }, 1);
            setTimeout(function () { modal.classList.remove("is_open"); }, ms);
        });
    });
}
//# sourceMappingURL=client.js.map