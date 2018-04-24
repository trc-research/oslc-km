function refreshTable(tableId, data) {
    table = $(tableId).dataTable();
    oSettings = table.fnSettings();
    table.fnClearTable();

    for (var i = 0; i < data.length; i++) {
        table.oApi._fnAddData(oSettings, data[i]);
    }

    oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
    table.fnDraw();
}
