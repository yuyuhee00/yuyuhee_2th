<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="utf-8">
    function validate(param) {
        if (param  == "" || param.length == 0) {
            alert ("입력하세요.");
            return false;
        }
    }

    function validateQuestionCreateForm(form) {
        if (form.subject.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.content.value == "") {
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
    }

    function validateAnswerCreateForm(form) {
        if (form.content.value == "") {
            alert("내용을 입력하세요.");
            form.content.focus();
            return false;
        }
    }
</script>