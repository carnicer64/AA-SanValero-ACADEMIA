<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="com.svalero.academia.domain.User" %>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
            $(document).ready(function() {
                $("form").on("submit", function(event) {
                    event.preventDefault();
                    var formValue = $(this).serialize();
                    $.post("search-subjects", formValue, function(data) {
                        $("#result").html(data);
                    });
                });
            });
        </script>

        <div class="container">
            <h1>ACADEMIA SERVLET</h1>
            <form>
                <div class="mb-3">
                    <label for="searchText" class="form-label">Buscador de cursos</label>
                    <input name="searchText" type="text" class="form-control" id="searchText" aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">Introduce el identificador o el nombre del curso</div>
                </div>
                <button type="submit" class="btn btn-success">Buscar</button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>