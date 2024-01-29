$(document).ready(function () {
    // Загрузка списка действующих производств
    $.ajax({
        url: "/production/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            // Очищаем список перед обновлением
            $("#production-list-container").empty();

            // Добавляем каждое производство в список
            data.forEach(function (productionThreadDto) {
                var listItem = $("<li>").addClass("production-item");
                var threadName = $("<span>").text(productionThreadDto.threadName);
                var threadState = $("<span>").text(productionThreadDto.state);
                var manageButton = $("<button>").text("Керувати").click(function () {
                    window.location.href = "/manage-production.html?threadName=" + productionThreadDto.threadName;
                });

                listItem.append(threadName, threadState, manageButton);
                $("#production-list-container").append(listItem);
            });
        },
        error: function () {
            alert("Ошибка при получении списка действующих производств");
        }
    });

    // Загрузка списка машин
    $.ajax({
        url: "/machine/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            // Очищаем выпадающий список перед обновлением
            $("#machine-select").empty();

            $("#machine-select").append("<option value='' disabled selected>Оберіть машину</option>");
            // Добавляем каждую машину в выпадающий список
            data.forEach(function (machine) {
                $("#machine-select").append("<option value='" + machine.id + "'>" + machine.name + "</option>");
            });
        },
        error: function () {
            alert("Ошибка при получении списка машин");
        }
    });

    $("#machine-select").on("change", function () {
        // Получение выбранного id машины
        var machineId = $(this).val();

        // Проверка, что машина выбрана
        if (machineId) {
            // Загрузка списка продуктов для выбранной машины
            $.ajax({
                url: "/product/all/byMachineId/" + machineId,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    // Очищаем выпадающий список продуктов перед обновлением
                    $("#product-select").empty();

                    $("#product-select").append("<option value='' disabled selected>Оберіть продукт</option>");
                    // Добавляем каждый продукт в выпадающий список
                    data.forEach(function (productDto) {
                        $("#product-select").append("<option value='" + productDto.id + "'>" + productDto.productName + " " + productDto.machineName + "</option>");
                    });
                },
                error: function () {
                    alert("Ошибка при получении списка продуктов для выбранной машины");
                }
            });
        }
    });

    // Обработчик клика по кнопке "Запустить"
    $("#start-production").on("click", function () {
        var machineId = $("#machine-select").val();
        var productId = $("#product-select").val();

        // Создаем объект с данными для запуска производства
        var productionData = {
            productId: productId,
            machineId: machineId
        };

        // Отправляем GET-запрос на сервер
        $.ajax({
            url: "/production/start",
            type: "POST",  // Изменили тип запроса на POST
            contentType: "application/json",  // Указываем, что отправляем JSON
            data: JSON.stringify(productionData),  // Преобразуем объект в JSON и отправляем в теле запроса
            success: function () {
                // Перезагрузка страницы после успешного запуска
                location.reload();
            },
            error: function () {
                alert("Ошибка при запуске производства");
            }
        });
    });
});
