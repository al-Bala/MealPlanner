const emptyHeart = document.querySelector('.fa-regular.fa-heart');
const solidHeart = document.querySelector('.solid.fa-heart');
const favBtn = document.querySelector('.favourite__btn');

const addFavourite = () => {
    if(emptyHeart.style.display === "flex") {
        solidHeart.style.display = "flex";
        emptyHeart.style.display = "none";
    } else {
        solidHeart.style.display = "flex";
        emptyHeart.style.display = "none";
    }
    
}

favBtn.addEventListener('click', addFavourite);
