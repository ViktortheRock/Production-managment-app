$(document).ready(function () {
    // Получение параметра id из URL
    const urlParams = new URLSearchParams(window.location.search);
    const baseStoppageId = urlParams.get('id');

    // Запрос на получение данных о машине
    $.ajax({
        url: '/base_type_stoppage/' + baseStoppageId,
        type: 'GET',
        success: function (baseStoppageDto) {
            // Заполнение формы данными о машине
            $('#base-stoppage-name').val(baseStoppageDto.name);

            // Обработчик события для кнопки "Обновить машину"
            $('#update-base-stoppage').on('click', function () {
                const updatedStoppageData = {
                    name: $('#base-stoppage-name').val(),
                };

                // Отправка PUT запроса с обновленными данными
                $.ajax({
                    url: '/base_type_stoppage/' + baseStoppageId,
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(updatedStoppageData),
                    success: function () {
                        alert("Базовий тип простою успішно відредагована")
                        window.location.href = '/base-stoppage.html';
                    },
                    error: function (error) {
                        console.error('Помилка при оновленні базового типу:', error);
                    }
                });
            });
        },
        error: function (error) {
            console.error('Помилка при отриманні данних базового типу:', error);
        }
    });
});
