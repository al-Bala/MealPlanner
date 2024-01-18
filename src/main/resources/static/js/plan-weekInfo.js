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
        createFirstPlanDiv(date);
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
            '<div id="days-' + x + '" class="day">' +
                '<label class="day__header">Dzień ' + (x + 1) + ': ' + date.toISOString().split("T")[0] + '</label>' +
                '<div class="cooking__wrapper">'+
                    '<select name="weekInfo.dayInfoList[' + x + '].eatingPlan.id" class="eating-plan" required>' +
                        '<option value="" disabled selected>Wybierz</option>' +
                        '<option value="C01">Gotuję</option>' +
                        '<option value="B02">Nie gotuję</option>' +
                        '<option value="E03">Gotowałem wcześniej</option>' +
                    '</select>' +
                    '<input class="cooking-time" type="number" min="15" name="weekInfo.dayInfoList[' + x + '].eatingPlan.timeMin" style="display:none;" placeholder="Czas">' +
                '</div>'+
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

    function createFirstPlanDiv(date) {
        var firstPlanDiv =
            '<div id="days-0" class="day">' +
                '<label class="day__header">Dzień 1: ' + date.toISOString().split("T")[0] + '</label>' +
                '<div class="cooking__wrapper">'+
                    '<select name="weekInfo.dayInfoList[0].eatingPlan.id" class="eating-plan">' +
                    '<option selected value="C01">Gotuję</option>' +
                    '</select>' +
                    '<input class="cooking-time" type="number" min="15" name="weekInfo.dayInfoList[0].eatingPlan.timeMin" placeholder="Czas">' +
                '</div>'+
            '</div>';

        $(wrapper).append(firstPlanDiv);
    }
});