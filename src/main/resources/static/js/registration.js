$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $('#registration-form').on('submit', function(e) {e.preventDefault();})
    $('#email').on('change', verifyUserEmail);
    $('#createOtp').on('click', function () {
        var isValid = validateFormFields();
        var email = $('#email').val();
        if(isValid && email) {
            $('#userFormDiv').hide();
            $.ajax({
                type: 'POST',
                url: GENERATE_OTP,
                data: {"email" :email},
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                }
            }).done( function(isOtpGenerated) {
                $('#otpFormDiv').show();
                $('#registration-form').unbind('submit');
                var title='தகவல்'
                if (isOtpGenerated) {
                    var message= email+' மினஞ்சுளுக்கு OTP அனுப்பப்பட்டுள்ளது'
                    messageAlertModal(title, message);
                }
            }).fail(function (){
                var title='பிழை தகவல்'
                var message='Error generating otp try later'
                messageAlertModal(title, message);
                $('#userFormDiv').show();
            });
        }
    });
});