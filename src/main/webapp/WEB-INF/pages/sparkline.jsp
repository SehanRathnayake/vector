<%--
  Created by IntelliJ IDEA.
  User: Sehan Rathnayake
  Date: 16/08/07
  Time: 8:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script
            src="https://cdn.crunchify.com/wp-content/uploads/code/jquery.sparkline.js"></script>

    <script type="text/javascript">
        function crunchifySparkline() {
            $.ajax({
                url : 'http://localhost:8082/vector/sparklinetest',
                dataType : "json",
                cache : false,
                contentType : 'application/json; charset=utf-8',
                type : 'GET',
                success : function(result) {
                    var one = result.sparkData;
                    //alert(one);
                    consumeJSONData(one);
                }
            });
        }

        function consumeJSONData(sparkData) {
            console.log(sparkData);
            for ( var i = 0; i < sparkData.length; i++) {
                var obj = sparkData[i];
                var crunchifyName;
                var crunchifyValue;
                for ( var key in obj) {
                    crunchifyName = key;
                    crunchifyValue = obj[key].toString();
                    crunchifyValue = crunchifyValue.substr(1);
                    crunchifyValue = crunchifyValue.substring(0,
                            crunchifyValue.length - 1);
                    var n = crunchifyValue.split(",");
                    console.log(n);
                    $("#" + crunchifyName).sparkline(n, {
                        type : 'line',
                        width : '160',
                        fillColor : '#eeeeee'
                    });
                }
            }
        }
    </script>

    <script type="text/javascript">
        var intervalId = 0;
        intervalId = setInterval(crunchifySparkline, 500);
    </script>
</head>

<body>
<div align="center">

    <div id="result"></div>

    <br>
    <br>  One: &nbsp;&nbsp;&nbsp;&nbsp;<span id="one">.</span>
    <br>
    <br>  Two: &nbsp;&nbsp;&nbsp;&nbsp;<span id="two">.</span>
    <br>
    <br> Three: &nbsp;&nbsp;&nbsp;&nbsp;<span id="three">.</span>
    <br>
    <br> <br> <br>


</div>
</body>
</html>