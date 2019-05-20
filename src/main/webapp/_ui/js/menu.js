const $ = require("jquery");

function init() {
    $('#openMenu').click(function() {
        document.getElementById("mySidenav").style.width = "250px";
    });

    $('#closeMenu').click(function() {
        document.getElementById("mySidenav").style.width = "0";
    });

}

exports.init=init;