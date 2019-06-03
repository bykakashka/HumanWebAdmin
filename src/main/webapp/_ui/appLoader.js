function loadApp(apps, appId) {
    if (typeof appId === "undefined") {
        return false;
    }

    if (
        appId.length > 0 &&
        typeof apps[appId] === "object" &&
        typeof apps[appId].init === "function"
    ) {
        try {
            apps[appId].init();
            console.log('init ' + appId);
        } catch (e) {
            console.error("error while initialising app", appId, e);
        }
    } else {
        throw new Error("AppInitialisationError");
    }
}

exports.load = loadApp;