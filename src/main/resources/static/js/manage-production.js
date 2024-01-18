$(document).ready(function () {
    // Параметры из URL
    var urlParams = new URLSearchParams(window.location.search);
    var threadName = urlParams.get('threadName');

    $.ajax({
        url: "/production/state/" + encodeURIComponent(threadName),
        type: "GET",
        success: function (data) {

            $("#production-info").text("Процес: " + threadName + ", Стан: " + data);

        },
        error: function () {
            alert("Ошибка при приостановке производства");
        }
    });

    // Обработчик кнопки "Приостановить"
    $("#pause-production").on("click", function () {
        // Отправляем GET-запрос на сервер
        $.ajax({
            url: "/production/wait/" + encodeURIComponent(threadName),
            type: "GET",
            success: function (data) {
                // Создаем объект StoppageCreateDto
                var stoppageDto = {
                    productId: data.productId, // Подставьте реальные значения, полученные от сервера
                    machineId: data.machineId, // Подставьте реальные значения, полученные от сервера
                };

                // Отправляем POST-запрос на сервер для создания Stoppage
                $.ajax({
                    url: "/stoppage",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(stoppageDto),
                    success: function (stoppageResponseDto) {
                        window.location.href = "/create-stoppage.html?stoppageId=" + stoppageResponseDto.id;
                    },
                    error: function () {
                        alert("Ошибка при создании Stoppage");
                    }
                });
            },
            error: function () {
                alert("Ошибка при приостановке производства");
            }
        });
    });

    // Обработчик кнопки "Возобновить"
    $("#resume-production").on("click", function () {
        // Отправляем GET-запрос на сервер
        $.ajax({
            url: "/production/resume/" + encodeURIComponent(threadName),
            type: "GET",
            success: function () {
                location.reload();
            },
            error: function () {
                alert("Ошибка при возобновлении производства");
            }
        });
    });

    // Обработчик кнопки "Завершить"
    $("#finish-production").on("click", function () {
        // Отправляем GET-запрос на сервер
        $.ajax({
            url: "/production/finish/" + encodeURIComponent(threadName),
            type: "GET",
            success: function () {
                window.location.href = "/production.html";
            },
            error: function () {
                alert("Ошибка при завершении производства");
            }
        });
    });
});
