/**
 * Created by nmuelle2 on 29.09.16.
 */
$(document).ready(function () {

    $(".createSubmit").click(function () {

        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var vorname = document.getElementById("vorname").value;
        var beruf = document.getElementById("beruf").value;
        var strasse = document.getElementById("strasse").value;
        var plz = document.getElementById("plz").value;
        var gebDatum = document.getElementById("gebDatum").value;
        var gebOrt = document.getElementById("gebOrt").value;
        var ausbildungsstart = document.getElementById("ausbildungsstart").value;

        $.post("create",
            {
                email: email,
                name: name,
                vorname: vorname,
                beruf: beruf,
                strasse: strasse,
                plz: plz,
                gebDatum: gebDatum,
                gebOrt: gebOrt,
                ausbildungsstart: ausbildungsstart
            },
            function (data, status) {
                alert("Data: " + data + "\nStatus: " + status);
            });
    });
});


