const LogIn = document.querySelector('.menu__login');
const loginForm = document.querySelector('.login-form__wrapper');
const menuBurger = document.querySelector('.menu');
const backgroundForm = document.querySelector('.login-form__background');
const navigation = document.querySelector('nav');
const exitFormBtn = document.querySelector('.exit-form-btn');

const displayPopUp = () => {
    loginForm.classList.add('pop-up');
    navbar.classList.toggle('active');
    menuBurger.classList.toggle('active');
    backgroundForm.classList.add('display');
}

const closePopUp = () => {
    loginForm.classList.remove('pop-up');
    backgroundForm.classList.remove('display');
}

LogIn.addEventListener('click', displayPopUp);
exitFormBtn.addEventListener('click', closePopUp);