

window.addEventListener("DOMContentLoaded", () => {
    const burgerBtn = document.querySelector('.burger-btn');
    const menuItems = document.querySelector('.menu');

    const showMenu = () => {
        menuItems.classList.toggle('active');
    }
    burgerBtn.addEventListener('click', showMenu);
});