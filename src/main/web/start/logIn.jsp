<html>
<head>
    <meta charset="UTF-8">
    <title>Log In</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1 class="w3-green">Log In</h1>
</div>

<div class="w3-container w3-center">
    <form method="post" action="/logIn">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <div class="form-group">
            <label for="login">Enter login:</label>
            <input id="login" name="login" type="text" class="w3-input w3-animate-input w3-border w3-round-large" placeholder="Login" style="width: 100%" required />
        </div>
        <div class="form-group">
            <label for="password">Enter password:</label>
            <input id="password" name ="password" type="password" class="w3-input w3-animate-input w3-border w3-round-large"  placeholder="Password" style="width: 100%"  required/>
        </div>
        <label></label>
        <button type="submit" name ="log in"  class="w3-btn w3-red w3-border w3-round-large"  style="width: 100%">log in</button>
    </div>
    </form>
</div>
</body>
</html>