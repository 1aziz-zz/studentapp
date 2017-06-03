<!DOCTYPE html>
<html lang="en">
<head>
    <title>StudentApp</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/simple-sidebar.css" rel="stylesheet">
<#if (featureService.isActive("${pod}.newStyle")) !false>
    <style>
        body {
            background-color: #FFCC00 !important;
        }

        .bs-example {
            background-color: #ecf180 !important;
        }

        .table td, .table th {
            border: none;
        }

        .table thead th {
            vertical-align: bottom;
            border-bottom: 2px solid #eceeef;
            border: none;
        }
    </style>
</#if>

</head>

<body>

<div id="wrapper">

    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    StudentApp
                </a>
            </li>
            <li>
                <b><a href="https://featuresservice.herokuapp.com/ff4j/features">Features Service</a></b>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>StudentApp</h1><br>
                    <p>This is a simple Spring Boot web application that connects with a database MySQL database.</p>
                </div>
            </div>
        <#if courses ??>
            <#if (featureService.isActive("${pod}.showCourses")) !true>
                <div class="row">
                    <div class="bs-example">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Course Name</th>
                                <th>Category</th>
                                <th>Location</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list courses as course>

                                <tr>
                                    <td>${course.name}</td>
                                    <td>${course.category}</td>
                                    <td>${course.location}</td>
                                    <td>${course.description}</td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>

                    </div>
                </div>
          <#else>
                <p style="color:gray"><b>Tables are not visible</b></p>
            </#if>
        <#else>
            <p style="color:gray"><b>${message}</b></p><br>
        </#if>
            <p style="color:red">App's pod group: <b>${pod}</b></p>
        </div>
        <br>
        <form class="col-md-12" action="/addNewCourse" method="post">
            <div class="form-group row col-md-7">
                <p></p><h4>Add a new course</h4>
            </div>
            <div class="form-group row col-md-7">
                <input type="text" class="form-control bs-example" name="input_title" placeholder="Enter title">
            </div>
            <div class="form-group row col-md-7">
                <select class="form-control bs-example" name="input_location">
                    <option selected="selected" disabled="disabled">Select a location</option>
                    <option>Utrecht</option>
                    <option>Amersfoort</option>
                </select></div>
            <div class="form-group row col-md-7">
                <input type="text" class="form-control bs-example" name="input_desc" placeholder="Enter description">
            </div>
            <div class="form-group row col-md-7">
                <select class="form-control bs-example" name="input_category">
                    <option selected="selected" disabled="disabled">Select a category</option>
                    <option>Java Programming</option>
                    <option>Requirements Engineering</option>
                    <option>Business Intelligence</option>
                </select>
            </div>

            <div class="form-group row col-md-7">
                <button type="submit" class="btn btn-warning">Submit</button>
            </div></form>
    </div>
</div>


<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>


</body>

</html>
