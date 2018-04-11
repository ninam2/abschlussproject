var urlArguments = $(location).attr('search');
var url = "findAll";
var CurVertragsart = getUrlVars()['vertragsart'];
var curVertragsValue = getUrlVars()['vertragsvalue'];
console.log("curVertragsart: " + CurVertragsart);
console.log("curVertragsValue: " + curVertragsValue);
function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}


if (urlArguments.length > 0) {


    if (urlArguments.startsWith('?beruf')) {
        url = url + "ByJob" + urlArguments;
    }
    else if (urlArguments.startsWith('?year')) {
        url = url + "ByYear" + urlArguments;
    }
    else if (urlArguments.startsWith('?vertragsart')) {
        if (typeof curVertragsValue === 'undefined') {
            console.log("its undefined");
            url = url + "ByVertrag" + urlArguments;
        }
        else {
            url = url + "ByVertragAndValue" + urlArguments;
            console.log("its defined");
        }

    }

}

function createPlaceholderValues(index, json) {
    console.log("testindex: " + index);
    //console.log("json:" +json.azubiinfos[index]);
    console.log("start:" + json.azubiinfos[index].ausbildungsstart);
    var id = json.azubiinfos[index].id;
    document.getElementById("name").value = json.azubiinfos[index].name;
    document.getElementById("vorname").value = json.azubiinfos[index].vorname;
    document.getElementById("email").value = json.azubiinfos[index].email;
    document.getElementById("beruf").value = json.azubiinfos[index].beruf;
    document.getElementById("strasse").value = json.azubiinfos[index].strasse;
    document.getElementById("plz").value = json.azubiinfos[index].plz;
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

    if (urlArguments.startsWith('?vertragsart')) {
        CurVertragsart = getUrlVars()['vertragsart'];
        $("<p>Ausgewählte Vertragsart: " + CurVertragsart + "</p>").appendTo(".url-values");
        $("<ul class='nav nav-pills'>" +
            "<li id='erhaltenUndUnterzeichnet'>" +
                "<a href='getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=erhalten%20und%20unterzeichnet'>erhalten und unterzeichnet</a>" +
            "</li>" +
            "<li id='erhalten' >" +
            "<a href = 'getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=erhalten'>erhalten</a>" +
            "</li>" +
            "<li id='verschickt' >" +
            "<a href='getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=verschickt'>verschickt</a>" +
            "</li>" +
            "<li id='unbearbeitet' >" +
            "<a href='getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=unbearbeitet'>unbearbeitet</a>" +
            "</li>" +
            "<li id='nichtVerschickt' >" +
                "<a href='getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=nicht%20verschickt'>nicht verschickt</a>" +
            "</li>" +
                "<li id='nichtNotwendig' >" +
                "<a href='getAll?vertragsart=" + CurVertragsart + "&vertragsvalue=nicht%20notwendig'>nicht notwendig</a>" +
            "</li>" +
            "</ul>").appendTo(".url-values");
        switch (curVertragsValue) {
            case "erhalten":
                $('#erhalten').addClass('active');
                break;
            case "verschickt":
                $('#verschickt').addClass('active');
                break;

            case "unbearbeitet":
                $('#unbearbeitet').addClass('active');
                break;

            case "nicht%20verschickt":
                $('#nichtVerschickt').addClass('active');
                break;

            case "nicht%20notwendig":
                $('#nichtNotwendig').addClass('active');
                break;
            case "erhaltenUndUnterzeichnet":
                $('#erhaltenUndUnterzeichnet').addClass('active');
                break;

            default :
                break;
        }
    }

    for (index = 0; index < json.azubiinfos.length; ++index) {
        ausbildungsstart = json.azubiinfos[index].ausbildungsstart;
        $("year").append(json.azubiinfos[index].ausbildungsstart);
        var curId = json.azubiinfos[index].id;
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
            "<img src='../img/vertraege.svg'  id='" + index + "'onclick='insertValues(this.id, " + curId + ", json )' >" +
            "</a>" +
            "</td>" +
            "</tr>"
        );
    }

});