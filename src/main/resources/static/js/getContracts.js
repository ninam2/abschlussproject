function insertValues(index, curId, json) {
    console.log("testindex: " + curId);
    var vertragsart;
    var vertraege = ["test"];
    console.log(vertraege);
    console.log(index);
    console.log(json.azubiinfos[index].vertraege.vertraginfos[0].vertragsattribut);
    for (var j = 0; j < json.azubiinfos[index].vertraege.vertraginfos.length; j++) {
        vertraege.push(json.azubiinfos[index].vertraege.vertraginfos[j].vertragsattribut);
        console.log(vertraege);
        console.log(json.azubiinfos[index]);
        vertragsart = json.azubiinfos[index].vertraege.vertraginfos[j].vertragsattribut;
        var vertragsvalue = json.azubiinfos[index].vertraege.vertraginfos[j].vertragsvalue;
        document.getElementById("name").value = vertragsvalue;

        var value1 = 'first' + vertragsart;
        var value2 = 'second' + vertragsart;
        var value3 = 'third' + vertragsart;
        var value4 = 'forth' + vertragsart;
        var value5 = 'fith' + vertragsart;

        $(
            "<label for = 'contract-content-value' class = 'col-sm-2 control-label' id=" + j + ">" + vertragsart + "</label>" +
            "<div class = 'col-sm-10 input form-group'>" +
            "<select class='form-control' id='contract-content-value " + vertragsart + "' value=" + vertragsvalue + ">" +
            "<option id='" + value1 + "'>erhalten und unterzeichnet</option>" +
            "<option id='" + value2 + "'>erhalten</option>" +
            "<option id='" + value3 + "'>verschickt</option>" +
            "<option id='" + value4 + "'>unbearbeitet</option>" +
            "<option id='" + value5 + "'>nicht notwendig</option>" +
            "</select>" +
            "</div>"
        ).appendTo("#contract-content");

        switch (vertragsvalue) {
            case "erhalten und unterzeichnet":
                $('#' + value1).replaceWith("<option id='" + value1 + "' selected>erhalten und unterzeichnet</option>");

                break;
            case "erhalten":
                $('#' + value2).replaceWith("<option id='" + value2 + "' selected>erhalten</option>");
                break;
            case "verschickt":
                $('#' + value3).replaceWith("<option id='" + value3 + "' selected>verschickt</option>");
                break;

            case "nicht notwendig":
                $('#' + value5).replaceWith("<option id='" + value5 + "' selected>nicht notwendig</option>");
                break;

            default :
                $('#' + value4).replaceWith("<option id='" + value4 + "' selected>unbearbeitet</option>");
                break;
        }

        $(".submitContract").click(function () {

            console.log("in submit:" + vertraege);
            for (var i = 1; i < vertraege.length; i++) {
                var id = "contract-content-value " + vertraege[i];
                console.log(id);
                vertragsvalue = document.getElementById(id).value;
                console.log(vertraege[i], curId, vertragsvalue);
                $(".submitContract").css("opacity", "0.2");
                $.post("vertraegeUpdate",
                    {
                        vertragsart: vertraege[i],
                        azubi_id: curId,
                        vertragsvalue: vertragsvalue
                    });
            }
        });

        $(".addContract").click(function () {
            vertragsart = document.getElementById("newContract").value;
            vertragsvalue = document.getElementById("contract-content-value").value;
            console.log(curId, vertragsvalue, vertragsart);
            $(".addContract").css("opacity", "0.2");
            $.post("createVertrag",
                {
                    vertragsart: vertragsart,
                    azubi_id: curId,
                    vertragsvalue: vertragsvalue
                });
            //alert("Data: " + data + "\nStatus: " + status);
        });
    }

}
