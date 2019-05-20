require('bootstrap');

const $ = require("jquery");
const appLoader = require("./appLoader");


const apps = {
    menu: require("./js/menu"),
    book: require("./js/book"),
    event: require("./js/event")
};

$(document).ready(function() {
    appLoader.load(apps, 'menu');
    const appId = $("html").data("appid");
    if (typeof appId === "string" && appId.length > 0) {
        appLoader.load(apps, appId);
    }
});