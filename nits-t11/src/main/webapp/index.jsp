<!DOCTYPE html>
<html>
<head>
    <title>Welcome to NITS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            text-align: center;
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
        }
        .content {
            background-color: rgba(255, 255, 255, 0.9);
            margin: 100px auto;
            padding: 20px;
            border-radius: 10px;
            width: 50%;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }
        button {
        
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="content">
        <h1>Welcome to NITS Training Program</h1>
        <p>
            At NITS, we offer cutting-edge training programs to advance your career in technology.
            Our courses are designed to provide hands-on learning and industry-relevant skills.
        </p>
        <p>Choose your path to success with our expert guidance!</p>
        <form action="welcome.jsp">
            <button type="submit">Continue</button>
        </form>
    </div>
</body>
</html>
