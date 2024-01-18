$(document).ready(function () {
    // Получение параметра id из URL
    const urlParams = new URLSearchParams(window.location.search);
    const machineId = urlParams.get('id');

    // Запрос на получение данных о машине
    $.ajax({
        url: '/machine/' + machineId,
        type: 'GET',
        success: function (machineDto) {
            // Заполнение формы данными о машине
            $('#machine-name').val(machineDto.name);

            // Обработчик события для кнопки "Обновить машину"
            $('#update-machine').on('click', function () {
                const updatedMachineData = {
                    name: $('#machine-name').val(),
                };

                // Отправка PUT запроса с обновленными данными
                $.ajax({
                    url: '/machine/' + machineId,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(updatedMachineData),
                    success: function () {
                        alert("Машина успішно відредагована")
                        window.location.href = '/machine.html';
                    },
                    error: function (error) {
                        console.error('Помилка при оновленні машини:', error);
                    }
                });
            });
        },
        error: function (error) {
            console.error('Помилка при отриманні данних машини:', error);
        }
    });
});
