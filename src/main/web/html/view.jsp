<%@ page import="java.util.List" %>
<%@ page import="db.model.Announcement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>View</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<body>
<div class="container">
    <form class="my-form">
        <h1> Ваши объявления: </h1>
        <table class="table-view">
            <tr>
                <th colspan="1" width="100">Id</th>
                <th colspan="2" width="200">Название</th>
                <th colspan="3" width="400">Текст объявления</th>
                <th colspan="4" width="250">Изображение</th>
            </tr>
            <%
            List<Announcement> names = (List<Announcement>) request.getAttribute("listAnnounce");
                    if (names != null && !names.isEmpty()) {
                        for (int i=0; i<names.size(); i++) {
                            if(names.get(i).getName()==null) continue;
                            out.println("<tr>");
                            out.println("<th colspan=\"1\">" + names.get(i).getId()+ "</th> ");
                            out.println("<th colspan=\"2\">" + names.get(i).getName() + "</th> ");
                            out.println("<th colspan=\"3\">" + names.get(i).getTextAnnounce() + "</th> ");
                            out.println("<th colspan=\"4\">"+"<img width = \"200\" height = \"160\" src="+ names.get(i).getImageURL()+">" +"</th> ");
                            out.println("</tr> ");
                        }
                    } else out.println("<p>Объявлений еще нет!</p>");
            %>
        </table>
    </form>
</div>

</body>
</html>