const LogIn = document.querySelector('.menu__login');
const loginForm = document.querySelector('.login-form__wrapper');
const menuBurger = document.querySelector('.menu');
const backgroundForm = document.querySelector('.login-form__background');
const navigation = document.querySelector('nav');
const exitFormBtn = document.querySelector('.exit-form-btn');

const displayPopUp = () => {
    navbar.classList.toggle('active');
    menuBurger.classList.toggle('active');
}

const closePopUp = () => {
    loginForm.classList.remove('pop-up');
}

LogIn.addEventListener('click', displayPopUp);
exitFormBtn.addEventListener('click', closePopUp);