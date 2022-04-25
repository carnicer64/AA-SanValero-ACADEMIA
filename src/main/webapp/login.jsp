<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.domain.User" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
        html,
        body {
          height: 100%;
        }

        body {
          display: flex;
          align-items: center;
          padding-top: 40px;
          padding-bottom: 40px;
          background-color: #f5f5f5;
        }

        .form-signin {
          width: 100%;
          max-width: 330px;
          padding: 15px;
          margin: auto;
        }

        .form-signin .checkbox {
          font-weight: 400;
        }

        .form-signin .form-floating:focus-within {
          z-index: 2;
        }
        </style>
    </head>

    <body class="text-center">
        <div class="container">
            <h1 class="text-center"> ACADEMIA SERVLET </h1>
            <h2 class="text-center"> Inicio de sesión </h2>
            <div class="form-signin">
              <form class="px-4 py-3" method="post" action="login">
                <div class="mb-3">
                  <label for="username" class="form-label">Nombre de usuario</label>
                  <input type="text" name="username" class="form-control" id="username" placeholder="Nombre de usuario">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Contraseña</label>
                  <input type="password" name="password" class="form-control" id="password" placeholder="Contraseña">
                </div>
                <button type="submit" class="btn btn-primary">Sign in</button>
              </form>
            </div>
        </div>
    </body>
</html>