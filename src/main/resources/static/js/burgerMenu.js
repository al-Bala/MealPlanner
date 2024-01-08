

// window.addEventListener("DOMContentLoaded", () => {
    const burgerBtn = document.querySelector('.burger-btn');
    const exitBtn = document.querySelector('.exit-btn');
    const menuItems = document.querySelector('.menu');
    const navbar = document.querySelector('nav');


    const showMenu = () => {
        menuItems.classList.toggle('active');
        navbar.classList.toggle('active');
    }
    burgerBtn.addEventListener('click', showMenu);
    exitBtn.addEventListener('click', showMenu);
// });