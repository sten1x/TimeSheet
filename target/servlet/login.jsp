<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!doctype html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <link href="css/signin.css" rel="stylesheet">

    </head>

    <body class="text-center">
        <main class="form-signin">
            <form action="login" method="post">
                <img class="mb-2" src="photos/logo-color.png" alt="" width="300" height="75">

                <div class="form-floating">
                    <input type="text" class="form-control" id="username" name="username" placeholder="User">
                    <label for="username">User</label>
                </div>

                <div class="form-floating">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    <label for="password">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-dark mb-3" type="submit">Sign in
                    <c:set var="lang" value="en" scope="session" />
                </button>

                <c:if test="${requestScope.error_message != null}">
                    <div class="alert alert-danger" role="alert">
                        ${ requestScope.error_message }
                    </div>
                </c:if>

            </form>
        </main>


    </body>

    </html>