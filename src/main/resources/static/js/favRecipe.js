const emptyHeart = document.querySelector('.fa-regular.fa-heart');
const solidHeart = document.querySelector('.fa-solid.fa-heart');
const favBtn = document.querySelector('.favourite__btn');
const trueFav = document.querySelector('.fav-bool');

const addFavourite = () => {
    if(trueFav.textContent === 'true') {
        solidHeart.style.display = "flex";
        emptyHeart.style.display = "none";
    } else if (trueFav.textContent === 'false') {
        solidHeart.style.display = "none";
        emptyHeart.style.display = "flex";
    }
    
}

window.addEventListener('load', addFavourite);
