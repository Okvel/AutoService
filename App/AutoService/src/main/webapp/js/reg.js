/**
 * Created by Just-drake on 30.04.16.
 */
jQuery(document).ready(function($) {
    var myForm = {
        init: function(form){
            this.form = $(form);
            this.setListeners();

        },
        inputs: $(form).find('input:not([type=submit])'),
        errorForm: false,
        setListeners: function(){
            this.form.on('submit', function(event) {
                event.preventDefault();
                myForm.errorForm = false;
                myForm.validateForm();
                myForm.passCheck();
                myForm.capchaCheck();
                myForm.submitForm();
            });
            this.inputs.on('keyup blur', function(event) {
                event.preventDefault();
                myForm.checkFields(this);
                myForm.passCheck();
                myForm.capchaCheck();
            });
        },
        validateForm: function(){
            this.inputs.each(function(index, el) {
                var showErrorField = $(el).siblings('span');
                if(el.value.length == 0){
                    showErrorField.addClass('not-check');
                    myForm.errorForm = true;
                }else{
                    showErrorField.removeClass('not-check').addClass('check');
                }
            });
        },
        submitForm: function(){
            if(this.errorForm === false){
                $('#message').html('Ваша форма отправлена');
                $(this.inputs).siblings('span').removeClass('check');
                $(this.form).trigger('reset');
            }else{
                $('#message').html('Заполните поля правильно');
            }
        },
        passCheck: function(){
            var pass = $(this.form).find('#pass').val(),
                rePass = $(this.form).find('#pass-repeat').val(),
                info = $('#pass-repeat').siblings('span');
            if(pass === rePass && rePass.length > 0){
                $(info).removeClass('not-check').addClass('check');
            }else if(pass !== rePass && rePass.length > 0){
                this.errorForm = true;
                $(info).removeClass('check').addClass('not-check');
            }
        },
        checkFields:function(input){
            var checkingInput = $(input).val(),
                info = $(input).siblings('span');
            if(checkingInput.length > 0){
                $(info).removeClass('not-check').addClass('check');
            }else{
                $(info).removeClass('check').addClass('not-check');
            }
        },
        capchaCheck: function(){
            var capcha = '55102',
                capchaCurVal = $(this.form).find('#capcha').val(),
                info = $(this.form).find('#capcha').siblings('span');

            if(capchaCurVal !== capcha && capchaCurVal.length > 0){
                this.errorForm = true;
                $(info).removeClass('check').addClass('not-check');
                if(capchaCurVal.length > 4){$(info).html('Неправильное число');}
                else{$(info).html('');}
            }else if(capchaCurVal === capcha){
                $(info).removeClass('not-check').addClass('check').html('');
            }
        }
    };

    myForm.init('#form');

});