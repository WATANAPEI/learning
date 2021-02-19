window.onload = () => {
    addModalAction();
}
function confirm() {


}

function addModalAction() {
    const modal = document.getElementById("modal");
    const modalButton = document.getElementById("modalOpen");
    const body = document.getElementById("body");

    const ms = 400;
    modal.style.transition = "opacity" + ms + "ms";

    modalButton.addEventListener('click', () => {
        setTimeout(()=> { modal.classList.add("is_open")}, 1);
        setTimeout(()=> { modal.classList.add("opacity-full")}, 50);
    });

    let modalClose = document.querySelectorAll(".js-modal-close");
    modalClose.forEach(e => {
        e.addEventListener("click", () => {
            setTimeout(()=>{modal.classList.remove("opacity-full")}, 1);
            setTimeout(()=>{modal.classList.remove("is_open")}, ms);
        })
    })
}