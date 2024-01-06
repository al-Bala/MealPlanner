$(function() {
    var wrapper = $('.days');
    var startDate = $('#startDate');
    var maxDays = 7;
    var x = 1;

    // Data nie może być wcześniejsza niż data dzisiejsza
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    startDate.attr('min', today);

    startDate.on('change', function() {
        addDay();
    });

    $('#addNextDay').on('click', function() {
        x++;
        if (wrapper.children().length < maxDays) {
            addNextDay();
        } else {
            alert('Możesz dodać maksymalnie ' + maxDays + ' dni.');
        }
    });

    $('#removeLastDay').on('click', function() {
        removeLastDay();
    });

    function addDay() {
        var date = new Date(startDate.val());
        wrapper.empty();  // Clear previous fields
        createPlanDiv(0, date);
    }

    function addNextDay() {
        var date = new Date(startDate.val());
        date.setDate(date.getDate() + wrapper.children().length);

        createPlanDiv(wrapper.children().length, date);
    }

    function removeLastDay() {
        var lastDay = wrapper.children().last();

        if (lastDay && wrapper.children().length > 1) {
            lastDay.remove();
        }
    }

    function createPlanDiv(x, date) {
        var planDiv =
            '<div class="days" id="days-'+ x +'">' +
            '<label>Dzień ' + (x + 1) + ': ' + date.toISOString().split("T")[0] + '</label>' +
            '<select name="weekInfo.dayInfoList[' + x + '].eatingPlan.id" class="eating-plan" required>' +
            '<option disabled selected >Wybierz</option>' +
            '<option value="C01">Gotuję</option>' +
            '<option value="B02">Nie gotuję</option>' +
            '<option value="E03">Gotowałem/am wcześniej</option>' +
            '</select>' +
            '<input type="number" min="15" name="weekInfo.dayInfoList[' + x + '].eatingPlan.timeMin" class="cooking-time" style="display:none;" placeholder="Podaj czas gotowania">' +
            '</div>';

        $(wrapper).append(planDiv);

        // Obsługa zmiany dla select
        $('.eating-plan').on('change', function() {
            var selectedOption = $(this).val();
            var cookingTimeInput = $(this).siblings('.cooking-time');

            if (selectedOption === 'C01') {
                cookingTimeInput.show();
            } else {
                cookingTimeInput.hide();
            }
        });
    }
});