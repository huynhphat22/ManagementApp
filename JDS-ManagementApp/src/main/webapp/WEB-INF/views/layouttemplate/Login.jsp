<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
    <div id="wrap-container">
       <div class="container-fluid">
            <div class="row" style="margin: 10% auto;">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2 class="text-center">Login </h2>
                        </div>
                        <div class="panel-body">
                            <form name="loginForm"
							action="<c:url value='/j_spring_security_check' />" method="POST">
                                <h4 ><span class="label label-success">${message}</span></h4>
                                <h4 ><span class="label label-danger">${error}</span></h4>
                                <div class="form-group">
                                    <label>Username</label>
                                    <input  type="text" name="username" required="true"  class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" required="true" name="password" class="form-control">
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-info btn-block" type="submit">Login</button>
                                </div>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
       </div>
    </div>

</body>
</html>