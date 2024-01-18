$(document).ready(function () {
    // Обработчик клика по кнопке "Logout"
    $("#logout").on("click", function () {
        // Ваш код для выхода из системы
        alert("Выход из системы");
    });

    function getProducts() {
        $.ajax({
            url: "/product/allDto",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Очищаем список продуктов перед обновлением
                $("#product-list-container").empty();

                // Добавляем каждый продукт в список
                data.forEach(function (productDto) {
                    var listItem = $("<li>").addClass("product-item");

                    var productName = $("<div>").addClass("product-name").text(productDto.productName);
                    var numbersInPack = $("<div>").addClass("product-name").text(productDto.numbersInPack + " рул/уп");
                    var machineName = $("<div>").addClass("product-name").text(productDto.machineName);
                    var productivity = $("<div>").addClass("product-name").text(productDto.expectedProductivity + " уп/хв");

                    var editButton = $("<button class='edit-button'>").text("Редагувати").click(function () {
                        // Перенаправление на страницу редактирования с параметром id
                        window.location.href = "/product-update.html?id=" + productDto.id;
                    });

                    var deleteButton = $("<button class='delete-button'>").text("Видалити").click(function () {
                        // Отправка DELETE запроса для удаления продукта
                        $.ajax({
                            url: '/product/' + productDto.id,
                            type: 'DELETE',
                            success: function () {
                                // Перезагрузка страницы после успешного удаления
                                location.reload();
                            },
                            error: function (error) {
                                console.error('Ошибка при удалении продукта:', error);
                            }
                        });
                    });

                    listItem.append(productName, numbersInPack, machineName, productivity, editButton, deleteButton);
                    $("#product-list-container").append(listItem);
                });
            },
            error: function () {
                alert("Ошибка при получении списка товаров");
            }
        });
    }

    // Инициализация страницы
    getProducts();

    // Функция для получения списка машин и заполнения выпадающего списка
    function fillMachineDropdown() {
        $.ajax({
            url: "/machine/all",
            type: "GET",
            dataType: "json",
            success: function (data) {
                // Очищаем выпадающий список перед обновлением
                $("#machine-name").empty();

                $("#machine-name").append("<option value='' disabled selected>Выберите машину</option>");
                // Добавляем каждую машину в выпадающий список
                data.forEach(function (machine) {
                    $("#machine-name").append("<option value='" + machine.id + "'>" + machine.name + "</option>");
                });
            },
            error: function () {
                alert("Ошибка при получении списка машин");
            }
        });
    }

    // Инициализация страницы
    fillMachineDropdown();

    // Обработчик клика по кнопке "Добавить продукт"
    $("#add-product").on("click", function () {
        var name = $("#product-name").val();
        var machineId = $("#machine-name").val();
        var numbersInPack = $("#product-numbersInPack").val();
        var expectedProductivity = $("#product-expectedProductivity").val();

        // Ваш код для добавления продукта
        var productData = {
            name: name,
            machineId: machineId,
            numbersInPack: numbersInPack,
            expectedProductivity: expectedProductivity
        };

        // Отправляем POST-запрос на сервер
        $.ajax({
            url: "/product",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(productData),
            dataType: "json",
            success: function (productResponseDto) {
                alert("Добавлен новый продукт: " + productResponseDto.productName);

                $("#product-name").val('');
                $("#machine-name").val('');
                $("#product-numbersInPack").val('');
                $("#product-expectedProductivity").val('');

                // Обновляем список продуктов после добавления
                getProducts();
            },
            error: function () {
                alert("Ошибка при добавлении машины");
            }
        });
    })

    // Дополнительные обработчики для других страниц (Главная, Продукты, Графики, Отчеты)
    // Добавьте собственные обработчики событий по аналогии с вышеуказанными.

});
