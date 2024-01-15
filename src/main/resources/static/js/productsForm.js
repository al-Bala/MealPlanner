const productsCont = document.querySelector('.productsU');
const btnAddProduct = document.querySelector('.add-productU')

const displayScroll = () => {
    productsCont.classList.add('product-scroll-container');
}

btnAddProduct.addEventListener('click', displayScroll);