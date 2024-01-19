const numberInput = document.getElementById('numberInput');
const selectedNumber = document.getElementById('selectedNumber');
         
numberInput.addEventListener('input', function () {
    selectedNumber.textContent = numberInput.value;
});
