/**
 * Created by root on 18/7/18.
 */

 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function () {

    $( ".datepicker" ).datepicker({changeYear: true, yearRange: "-80:+0", dateFormat: 'dd/mm/yy' });

    $('[data-toggle="tooltip"]').tooltip();

    $('#payment-submit').on('click', function () {
        var allowSubmit = false;
        $('.feeCheckBox:checked').each(function (i) {
            allowSubmit = true;
            $('<input>').attr({
                type: 'hidden',
                name: 'feeList['+i+'].id',
                value: this.value
            }).appendTo('#payment-form');
        })
        if (allowSubmit)
            $('#payment-form').submit();
    })

    $('.eventRegistration-playing-category').on('change', function () {
        var totalFee=0.00;
        var isTeamEvent = 'false';
        $('.eventRegistration-playing-category:checked').each(function (i) {
            totalFee+=Number($(this).data('fee'))
            if (isTeamEvent!='true' && isTeamEvent!=true) {
                isTeamEvent=$(this).data('isteamevent')
            }
        })
        if (isTeamEvent == 'true' || isTeamEvent==true) {
            $('#teamName').show()
        }else {
            $('#teamName').hide()
        }
        $('#total-event-reg-fee').text('Total : ₹ '+totalFee.toFixed(2))
    })

})
function messageAlertModal(title, message) {
    $('#messageTitle').text(title)
    $('#alertMessage').text(message)
    $('#messageModal').modal('show')
}

function validateFormFields() {
    var valid=true;
    $('#registration-form input[required]').css("border-color", "#ccc")
    if(!requiredValidation()) return false;
    if(!validEmail()) return false;
    if(!verifyUserEmail()) return false;
    if(!validMobile()) return false;
    if(!passwordMatch()) return false;
    return valid;
}
function passwordMatch() {
    if(!($('#password').val() === $('#pwdConfirm').val())) {
        messageAlertModal('பிழை தகவல்', 'Please enter matching passwords');
        $('#password,#pwdConfirm').css("border-color", "red");
        return false;
    }
    return true;
}
function validMobile() {
    var mobile=$('#mobile').val();
    if (!/^\d{10}$/.test(mobile)) {
        messageAlertModal('பிழை தகவல்', 'Please enter only 10 digits of your mobile number');
        $('#mobile').css("border-color", "red");
        return false;
    }
    return true;
}
function validEmail () {
    var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
    if(!pattern.test($('#email').val())){
        messageAlertModal('பிழை தகவல்', 'Please enter valid email');
        $(this).css("border-color", "red");
        return false;
    }
    return true;
}
function requiredValidation() {
    var valid = true;
    $('#registration-form input[required]').each(function () {
        if(!$(this).val()) {
            $(this).css("border-color", "red");
            valid = false;
        }
    });
    if(!valid) {
        messageAlertModal('பிழை தகவல்', 'Please enter the required fields')
    }
    return valid;
}
function verifyUserEmail() {
    var email = $('#email').val();
    var valid = true;
    if(!validEmail()) return false;
    $.ajax({
        type: 'POST',
        url: GET_USER_BY_EMAIL,
        data: {"email" :email},
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function(resultData) {
        var title='பிழை தகவல்'
        if (resultData) {
            var message='இந்த மின்னஞ்சல் முன்பே பதிவு செய்யப்பட்டுள்ளது'
            messageAlertModal(title, message);
            valid = false;
        }
    }).fail(function(){
        var title='பிழை தகவல்';
        var message='Something went wrong';
        messageAlertModal(title, message);
        valid = false;
    });
    return valid;
}