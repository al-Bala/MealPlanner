const dietType = document.querySelector('.diet__name');
const leaf = document.querySelector('.fa-leaf');
const circle = document.querySelector('.fa-circle-check');
const drumstick = document.querySelector('.fa-drumstick-bite');

if (dietType.textContent === 'wegetariańska'|| dietType.textContent === 'wegańska') {
    circle.style.display = "none";
    drumstick.style.display = "none";
} else if (dietType.textContent === 'bezglutenowa') {
    leaf.style.display = "none";
    drumstick.style.display = "none";
} else if (dietType.textContent === 'mięsna') {
    leaf.style.display = "none";
    circle.style.display = "none";
}