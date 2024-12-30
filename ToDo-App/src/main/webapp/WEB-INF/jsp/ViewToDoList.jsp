<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View ToDo Item List</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a {
            color: white;
        }

        a:hover {
            color: white;
            text-decoration: none;
        }

        .btn-custom {
            display: inline-block;
            margin: 5px 0;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1 class="p-3">ToDo Item List</h1>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Mark Completed</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="toDo" items="${list}">
                    <tr>
                        <td>${toDo.id}</td>
                        <td>${toDo.title}</td>
                        <td>${toDo.date}</td>
                        <td>${toDo.status}</td>
                        <td>
                            <a href="/updateToDoStatus/${toDo.id}" class="btn btn-success btn-custom">Mark Complete</a>
                        </td>
                        <td>
                            <a href="/editToDoItem/${toDo.id}" class="btn btn-primary btn-custom">Edit</a>
                        </td>
                        <td>
                            <a href="/deleteToDoItem/${toDo.id}" class="btn btn-danger btn-custom">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="/addToDoItem" class="btn btn-primary btn-block">Add New ToDo Item</a>
    </div>

    <script>
        window.onload = function() {
            var msg = "${message}";
            
            if (msg == "Save Success") {
                Command: toastr["success"]("Item added successfully!!")
            } else if (msg == "Delete Success") {
                Command: toastr["success"]("Item deleted successfully!!")
            } else if (msg == "Delete Failure") {
                Command: toastr["error"]("Some error occurred, couldn't delete item")
            } else if (msg == "Edit Success") {
                Command: toastr["success"]("Item updated successfully!!")
            }

            toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        };
    </script>

</body>
</html>
