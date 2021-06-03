$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var isRegistered = $('#isRegistered').val();
    var isAccountVerified = $('#isAccountVerified').val();
    if(isRegistered) {
        var verifiedMsg = isAccountVerified?"":"Email validation failed. Please validate the email in user profile".
        messageModal('Alert', 'Registration successful.'+ verifiedMsg);
    }

});
