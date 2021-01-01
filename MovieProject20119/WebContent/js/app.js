window.onload = function() {
    $(".content > img").css({"left" : "100%"});
    $(".content > img").eq(0).css({"left" : 0});

    let idx = 0;

    setInterval(function() {
        let next = (idx + 1) % 3;
        $(".content > img").eq(next).css({"left" : "100%"}).animate({"left" : "0"}, 800);
        $(".content > img").eq(idx).animate({"left" : "-100%"}, 800);
        idx = next;
    }, 3000);

    let month = $("#month").val();

    $("#month").on("change", function() {
        month = $("#month").val();
        console.log(month);
        let day = 31;

        if(month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;    
        } else if(month == 2) {
            day = 29;
        } else {
            day = 31;
        }

        $("#day").html(`<option value="1">1</option>`);
        console.log(day);
        for(let i = 2; i <= day; i++) {
            $("#day").append(`<option value="${i}">${i}</option>`);
        }
    });
}