$("#home").on("click", function () {
    window.location.href = "index.html";
});

$("#production").on("click", function () {
    window.location.href = "production.html";
});

$("#stoppage").on("click", function () {
    window.location.href = "stoppage.html";
});

$("#machines").on("click", function () {
    window.location.href = "machine.html";
});

$("#products").on("click", function () {
    window.location.href = "product.html";
});

$("#charts").on("click", function () {
    window.location.href = "chart.html";
});

$("#reports").on("click", function () {
    window.location.href = "report.html";
});

$("#logout").on("click", function () {
    $.ajax({
        url: "/logout",
        type: "POST",
        beforeSend: function(xhr) {
            var jwtToken = localStorage.getItem("jwtToken");
            if (jwtToken) {
                xhr.setRequestHeader("Authorization", "Bearer " + jwtToken);
            }
        },
        success: function(response) {
            // Успешный logout
            // Очистка localStorage и перенаправление на другую страницу или выполнение других действий
            localStorage.clear();
            location.reload();
            },
        error: function(xhr) {
            // Ошибка при logout
            // Обработка ошибки, если необходимо
        }
    });
});
