$(document).ready(function () {
    // Обработчик клика по кнопке "Logout"
    $("#logout").on("click", function () {
        // Ваш код для выхода из системы
        alert("Выход из системы");
    });

    // Функция для получения списка машин
    function getSubTypes() {
        $.ajax({
            url: "/sub_type_stoppage/all",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Очищаем список машин перед обновлением
                $("#sub-type-list-container").empty();

                // Добавляем каждую машину в список
                data.forEach(function (stoppageDto) {
                    var listItem = $("<li>").addClass("sub-stoppage-item");

                    var subTypeName = $("<div>").text(stoppageDto.name);
                    var baseTypeName = $("<div>").text(stoppageDto.baseTypeStoppageName);

                    var editButton = $("<button class='edit-button'>").text("Редагувати").click(function () {
                        window.location.href = "/sub-stoppage-update.html?id=" + stoppageDto.id;
                    });

                    var deleteButton = $("<button class='delete-button'>").text("Видалити").click(function () {
                        $.ajax({
                            url: '/sub_type_stoppage/' + stoppageDto.id,
                            type: 'DELETE',
                            success: function () {
                                location.reload();
                            },
                            error: function (error) {
                                console.error('Помилка при видаленні простою:', error);
                            }
                        });
                    });

                    listItem.append(subTypeName, baseTypeName, editButton, deleteButton);
                    $("#sub-type-list-container").append(listItem);
                });
            },
            error: function () {
                alert("Помилка при отриманні списку машин");
            }
        });
    }

    // Инициализация страницы
    getSubTypes();

    function fillBaseTypesDropdown() {
        $.ajax({
            url: "/base_type_stoppage/all",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Очищаем список машин перед обновлением
                $("#base-type-name").empty();

                $("#base-type-name").append("<option value='' disabled selected>Оберіть базовий тип</option>");
                // Добавляем каждую машину в выпадающий список
                data.forEach(function (baseStoppageDto) {
                    $("#base-type-name").append("<option value='" + baseStoppageDto.id + "'>" + baseStoppageDto.name + "</option>");
                });
            },
            error: function () {
                alert("Помилка при отриманні списку машин");
            }
        });
    }

    fillBaseTypesDropdown();

    // Обработчик клика по кнопке "Добавить машину"
    $("#add-sub-type").on("click", function () {
        var baseTypeId = $("#base-type-name").val();
        var subTypeName = $("#sub-type-name").val();

        // Создаем объект с данными машины
        var typeStoppageData = {
            name: subTypeName,
            baseTypeStoppageId: baseTypeId
        };

        // Отправляем POST-запрос на сервер
        $.ajax({
            url: "/sub_type_stoppage",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(typeStoppageData),
            dataType: "json",
            success: function (data) {
                alert("Добавлен новий базовий тип простою: " + data.name);

                $("#base-type-name").val('');
                $("#sub-type-name").val('');

                getSubTypes();
            },
            error: function () {
                alert("Помилка при видаленні машини");
            }
        });
    })

    // Дополнительные обработчики для других страниц (Главная, Продукты, Графики, Отчеты)
    // Добавьте собственные обработчики событий по аналогии с вышеуказанными.
});
