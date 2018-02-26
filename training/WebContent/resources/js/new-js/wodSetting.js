/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/11/26
 * Time: 17:03
 *
 */
$(function () {
    $.each($('.editorBtn'),function(){
        $(this).on('click',function () {
            $(this).hide();
            $(this).siblings('.backEditor').show();
            $(this).siblings('.choose').show();
            $(this).siblings('.showChoose').hide();
        })
    });
    $.each($('.cancel'),function(){
        $(this).on('click',function () {
            $(this).parents('.editor').children('.backEditor').hide();
            $(this).parents('.editor').children('.choose').hide();
            $(this).parents('.editor').children('.showChoose').show();
            $(this).parents('.editor').children('.editorBtn').show();
        })
    });
    $.each($('.confirm'),function(){
        $(this).on('click',function () {
            $(this).parents('.editor').children('.backEditor').hide();
            $(this).parents('.editor').children('.choose').hide();
            $(this).parents('.editor').children('.showChoose').show();
            $(this).parents('.editor').children('.editorBtn').show();
        })
    });
})