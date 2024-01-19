document.addEventListener('DOMContentLoaded', function() {
    const productsCont = document.querySelector('.productsU');
    const btnAddProduct = document.querySelector('.add-productU');
    const prContWithScroll = document.querySelector('.product-scroll-container');


   
    if(productsCont) {
        if(!productsCont.querySelector('.products__container')) {
            productsCont.classList.remove('product-scroll-container');
            prContWithScroll.style.height = "auto";
        }
    }

    const displayScroll = () => {
            productsCont.classList.add('product-scroll-container');
    }

    btnAddProduct.addEventListener('click', displayScroll);
});
