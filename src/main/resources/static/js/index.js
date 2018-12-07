$(document).ready(function() {
    $('.logo-container').click(function(event) {
        $('.selected').removeClass("selected");
        $(this).addClass("selected");
    });

    $('.logo-container-result').click(function(event) {
        $('.selected').removeClass("selected");
        $(this).addClass("selected");
    });


    $('#searchbar').keypress(function(e){
        if(e.which == 13){ //Enter key pressed
            performSearch();
        }
    });

    $('#searchbar-result').keypress(function(e){
        if(e.which == 13){ //Enter key pressed
            performSearch();
        }
    });

    $('#search-button').on('click', performSearch);
    $('#search-button-result').on('click', performSearch);
    $('#back-to-index').on('click', backToFrontPage);
});

function backToFrontPage() {
    $(".body-container").css("display", "block");
    $(".result-container").css("display", "none");
    if ($("#google-search-button-result").hasClass("selected") || $("#google-search-button").hasClass("selected")) {
        $("#google-search-button").addClass("selected");
    } else if ($("#bing-search-button-result").hasClass("selected") || $("#bing-search-button").hasClass("selected")) {
        $("#bing-search-button").addClass("selected");
    } else if ($("#duckduckgo-search-button-result").hasClass("selected") || $("#duckduckgo-search-button").hasClass("selected")) {
        $("#duckduckgo-search-button").addClass("selected");
    }
}


function performSearch() {
    console.log("Performing Search:");
    const engine = getEngineType();
    let query = "";

    if ($(".body-container").css("display") != "none") {
        $("#searchbar-result").val($("#searchbar").val());
        query = document.getElementById('searchbar').value;
    }

    if ($(".result-container").css("display") != "none") {
        $("#searchbar").val($("#searchbar-result").val());
        query = document.getElementById('searchbar-result').value;
    }

    // Used https://www.mkyong.com/spring-boot/spring-boot-ajax-example/ for reference

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/search",
        data: (engine + ":" + query),
        dataType: "json",
        cache: false,
        timeout: 600000,
        success: function (data) {
            didSuccessfullyGetData(data);
        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}

const didSuccessfullyGetData = (data) => {

    $(".body-container").css("display", "none");
    $(".result-container").css("display", "block");
    $("#result-list").css("margin-left", $("#back-to-index").css("width"));
    if ($("#google-search-button-result").hasClass("selected") || $("#google-search-button").hasClass("selected")) {
        $("#google-search-button-result").addClass("selected");
    } else if ($("#bing-search-button-result").hasClass("selected") || $("#bing-search-button").hasClass("selected")) {
        $("#bing-search-button-result").addClass("selected");
    } else if ($("#duckduckgo-search-button-result").hasClass("selected") || $("#duckduckgo-search-button").hasClass("selected")) {
        $("#duckduckgo-search-button-result").addClass("selected");
    }
    // format data appropriately.
    console.log("emptying results container");

    $("#result-list").empty();
    for(let i = 0; i < data.length; i++) {
        data[i].title = data[i].title.replace(/["']/g, "");
        data[i].subtitle = data[i].subtitle.replace(/["']/g, "");
        data[i].url = data[i].url.replace(/["']/g, "");
        $("#result-list").append(
            '<div class="result"><a href="' +
            data[i].url +
            '"><h3>' +
            data[i].title +
            '</h3></a>' +
            '<p style="color:gray;">' +
            data[i].subtitle +
            '</p></div>');
    }

    console.log("appended data");

    console.log(data);
}

function getEngineType() {
    const engine = $('.selected').attr('id');

    if  (engine == "duckduckgo-search-button" || engine == "duckduckgo-search-button-result")
        return "DuckDuckGo";
    else if (engine == "google-search-button" || engine == "google-search-button-result")
        return "Google";
    else
        return "Bing";
}
