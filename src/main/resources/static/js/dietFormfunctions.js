let chosenDiet = null;
        function selectDiet(diet) {
            const allDiets = document.querySelectorAll('.diet-type');
            allDiets.forEach(div => {
                div.style.backgroundColor = '#FFFFFF';
                div.style.color = '#FF7F37';
            });
            chosenDiet = diet;
            chosenDietId = document.getElementById(diet)
            chosenDietId.style.color = '#FFFFFF';
            chosenDietId.style.transition = "0.4s";
            chosenDietId.style.backgroundColor = '#FF7F37';
        }
        
        function submitForm() {
            if (chosenDiet) {
                //może tutaj się uda wstawić backend? skoro wiemy którą diete wybrał użytkownik?
                const allertPlace =  document.querySelector('.diet-types__wrapper');
                const dietAllert = document.querySelector('.diet-allert');
                allertPlace.removeChild(dietAllert);
            } else {
                const allertPlace =  document.querySelector('.diet-types__wrapper');
                const dietAllert = document.createElement('p');
                dietAllert.classList.add('diet-allert')
                dietAllert.style.color = "#FF7F37";
                dietAllert.textContent = 'Wybierz jedną z opcji, aby kontnuować';
                allertPlace.appendChild(dietAllert)
            }
        }