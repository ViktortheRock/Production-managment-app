$(document).ready(function () {
    // Обработчик клика по кнопке "Logout"
    $("#logout").on("click", function () {
        // Ваш код для выхода из системы
        alert("Выход из системы");
    });

    // Функция для получения списка машин
    function getBaseTypes() {
        $.ajax({
            url: "/base_type_stoppage/all",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Очищаем список машин перед обновлением
                $("#base-type-list-container").empty();

                // Добавляем каждую машину в список
                data.forEach(function (stoppageDto) {
                    var listItem = $("<li>").addClass("base-stoppage-item");

                    var baseTypeName = $("<div>").text(stoppageDto.name);

                    var editButton = $("<button class='edit-button'>").text("Редагувати").click(function () {
                        window.location.href = "/base-stoppage-update.html?id=" + stoppageDto.id;
                    });

                    var deleteButton = $("<button class='delete-button'>").text("Видалити").click(function (message) {
                        $.ajax({
                            url: '/base_type_stoppage/' + stoppageDto.id,
                            type: 'DELETE',
                            success: function () {
                                location.reload();
                            },
                            error: function (error) {
                                alert(error.responseText);
                            }
                        });
                    });

                    listItem.append(baseTypeName, editButton, deleteButton);
                    $("#base-type-list-container").append(listItem);
                });
            },
            error: function () {
                alert("Помилка при отриманні списку машин");
            }
        });
    }

    // Инициализация страницы
    getBaseTypes();

    // Обработчик клика по кнопке "Добавить машину"
    $("#add-base-type").on("click", function () {
        var baseTypeName = $("#base-type-name").val();

        // Создаем объект с данными машины
        var typeStoppageData = {
            name: baseTypeName
        };

        // Отправляем POST-запрос на сервер
        $.ajax({
            url: "/base_type_stoppage",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(typeStoppageData),
            dataType: "json",
            success: function (data) {
                alert("Добавлен новий базовий тип простою: " + data.name);

                $("#base-type-name").val('');

                getBaseTypes();
            },
            error: function () {
                alert("Помилка при видаленні машини");
            }
        });
    })

    // Дополнительные обработчики для других страниц (Главная, Продукты, Графики, Отчеты)
    // Добавьте собственные обработчики событий по аналогии с вышеуказанными.
});
