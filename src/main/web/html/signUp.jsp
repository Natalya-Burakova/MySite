<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script>
        $( document ).ready(function() {
            $('#s-h-pass').click(function(){
                var type = $('#password').attr('type') == "text" ? "password" : 'text',
                    c = $(this).html() == "<span class=\"glyphicon glyphicon-eye-close\" title=\"Скрыть пароль\"></span>" ? "<span class=\"glyphicon glyphicon-eye-open\" title=\"Показать пароль\"></span>" : "<span class=\"glyphicon glyphicon-eye-close\" title=\"Скрыть пароль\"></span>";
                $(this).html(c);
                $('#password').prop('type', type);
            });
        });
    </script>
    <script>
    </script>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<div class="container">
    <h1>Регистрация</h1>
    <form method="post" action="/signUp" class="my-form">
        <div class="form-group">
            <label><%=request.getAttribute("error")%></label>
        </div>
        <div class="form-group">
            <label for="name">Введите имя:</label>
            <input id="name" name = "name" type="text" placeholder="Имя" class="form-control" required/>
        </div>
        <div class="form-group">
            <label for="email">Введите e-mail:</label>
            <input id="email" name = "mail" type="email" placeholder="E-mail" class="form-control" required/>
        </div>
        <div class="form-group">
            <label for="login">Введите логин (с ограничением 2-20 символов, которыми могут быть латинские буквы и цифры, первый символ обязательно буква):</label>
            <input id="login" name = "login" type="text" placeholder="Логин" class="form-control" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" required/>
            <span id="check_login"></span>
        </div>
        <div class="form-group">
            <label for="password">Введите пароль (более 8 букв латинского алфавита обоих регистров, цифр и спецсимволов):</label>
            <div class="input-group">
                <input id="password" name="password" type="password" placeholder="Пароль" class="form-control" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required />
                <div class="input-group-addon" id="s-h-pass"><span class="glyphicon glyphicon-eye-open" title="Показать пароль"></span></div>
            </div>
        </div>
        <div class="form-group">
            <input id="sign up" type="submit" value="Зарегистрироваться" class="btn btn-success pull-right" />
        </div>
    </form>
</div>
</body>
</html>