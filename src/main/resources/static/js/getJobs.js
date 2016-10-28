$.get("findAllJobsAndYears", function (jsonObject) {
    var json = $.parseJSON(jsonObject);
    for (var index = 0; index < json.jobs.length; ++index) {
        var currentJob = json.jobs[index].job;
        $("<li>" +
            "<a href = '/getAll?beruf=" + currentJob + "'>" +
            currentJob +
            "</a>" +
            "</li>"
        ).appendTo(".job");
    }

    for (var index = 0; index < json.years.length; ++index) {
        var currentYear = json.years[index].year;
        $("<li>" +
            "<a href = '/getAll?year=" + currentYear + "'>" +
            currentYear + "" +
            "</a>" +
            "</li>"
        ).appendTo(".year");
    }


    for (var index = 0; index < json.vertragsarten.length; ++index) {
        var currentVertrag = json.vertragsarten[index].vertragsart;
        $("<li>" +
            "<a href = '/getAll?vertrag=" + currentVertrag + "'>" +
            currentVertrag + "" +
            "</a>" +
            "</li>"
        ).appendTo(".vertrag");
    }

});