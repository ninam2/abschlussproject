var urlArguments = $(location).attr('search');
var url = "findAll";

if (urlArguments.length > 0) {


    if (urlArguments.startsWith('?beruf')) {
        url = url + "ByJob" + urlArguments;
    }
    else if (urlArguments.startsWith('?year')) {
        url = url + "ByYear" + urlArguments;
    }
    else {
        url = url + "ByVertrag" + urlArguments;
    }
}


function createPlaceholderValues(index, json) {
    console.log("testindex: " + index);
    //console.log("json:" +json.azubiinfos[index]);
    console.log("start:" + json.azubiinfos[index].ausbildungsstart);
    var id = json.azubiinfos[index].id;
    document.getElementById("name").value = json.name;
    document.getElementById("vorname").value = json.azubiinfos[index].vorname;
    document.getElementById("email").value = json.azubiinfos[index].email;
    document.getElementById("beruf").value = json.azubiinfos[index].beruf;
    document.getElementById("strasse").value = json.azubiinfos[index].strasse;
    document.getElementById("plz").value = json.azubiinfos[index].plz;
    document.getElementById("gebDatum").value = json.azubiinfos[index].gebDatum;
    document.getElementById("gebOrt").value = json.azubiinfos[index].gebOrt;
    document.getElementById("ausbildungsstart").value = json.azubiinfos[index].ausbildungsstart;


    $(".delete").click(function () {
        $.post("delete",
            {
                id: id
            })
    });


    $(".submit").click(function () {
        console.log(json.azubiinfos[0].name);
        console.log('id:' + id);

        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var vorname = document.getElementById("vorname").value;
        var beruf = document.getElementById("beruf").value;
        var strasse = document.getElementById("strasse").value;
        var plz = document.getElementById("plz").value;
        var gebDatum = document.getElementById("gebDatum").value;
        var gebOrt = document.getElementById("gebOrt").value;
        var ausbildungsstart = document.getElementById("ausbildungsstart").value;

        $.post("update",
            {
                id: id,
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
}

$.get(url, function (jsonObject) {
    json = "bla";
    json = $.parseJSON(jsonObject);
    for (index = 0; index < json.azubiinfos.length; ++index) {
        ausbildungsstart = json.azubiinfos[index].ausbildungsstart;
        $("year").append(json.azubiinfos[index].ausbildungsstart);
        index2 = index;
        $("tbody").append(
            "<tr>" +
            "<td>" +
            "<a class='icon' data-toggle='modal' data-target='#myModal'>" +
            "<img src='../img/icons/edit-icon.svg'  id='" + index + "'onclick='createPlaceholderValues(this.id, json )' >" +
            "</a>" +
            "</td>" +
            "<td>" + json.azubiinfos[index].name + "</td>" +
            "<td>" + json.azubiinfos[index].vorname + "</td>" +
            "<td>" + json.azubiinfos[index].email + "</td>" +
            "<td>" + json.azubiinfos[index].beruf + "</td>" +
            "<td>" + json.azubiinfos[index].strasse + "</td>" +
            "<td>" + json.azubiinfos[index].plz + "</td>" +
            "<td>" + json.azubiinfos[index].gebDatum + "</td>" +
            "<td>" + json.azubiinfos[index].gebOrt + "</td>" +
            "<td>" + json.azubiinfos[index].ausbildungsstart + "</td>" +
            "<td>" +
            "<a class='icon' data-toggle='modal' data-target='#myContract'>" +
            "<img src='../img/vertraege.svg'  id='" + index + "'onclick='insertValues(this.id, json )' >" +
            "</a>" +
            "</td>" +
            "</tr>"
        );
    }

});